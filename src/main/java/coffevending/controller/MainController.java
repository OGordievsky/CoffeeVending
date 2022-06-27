package coffevending.controller;

import coffevending.Application;
import coffevending.model.CheckLines;
import coffevending.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class MainController extends AbstractController {

    private List<Product> productList;
    private final HashMap<String, CheckLines> basketList = new HashMap<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button main_basket_button_clear;

    @FXML
    private Button main_basket_button_pay;

    @FXML
    private TextField main_basket_field_totalSum;

    @FXML
    private Button main_filter_button_clear;

    @FXML
    private Button main_table_button_add;

    @FXML
    private TextField main_filter_field;

    @FXML
    private TableView<CheckLines> main_table_basket;

    @FXML
    private TableColumn<?, ?> main_table_basket_count;

    @FXML
    private TableColumn<Product, Integer> main_table_basket_name;

    @FXML
    private TableColumn<?, ?> main_table_basket_total;

    @FXML
    private TableView<Product> main_table_products;

    @FXML
    private TableColumn<?, ?> main_table_products_ean;

    @FXML
    private TableColumn<?, ?> main_table_products_name;

    @FXML
    private TableColumn<?, ?> main_table_products_price;

    @FXML
    private Button main_basket_button_delete;


    @FXML
    void initialize() {
        this.productList = goodsService.getAll();
        initTable(productList);

        main_filter_field.textProperty().addListener((observable, oldValue, newValue) -> {
            initTable(goodsService.getFilter(productList, newValue));
        });

        main_filter_button_clear.setOnAction(event -> {
            main_filter_field.setText("");
        });

        main_table_products.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->{
                    addProductToBasket(newValue);
        });

        main_table_button_add.setOnAction(event -> {
            Product product = main_table_products.getFocusModel().getFocusedItem();
            addProductToBasket(product);
        });

        main_basket_button_clear.setOnAction(event -> {
            clearBasket();
        });

        main_basket_button_delete.setOnAction(event -> {
            CheckLines product = main_table_basket.getFocusModel().getFocusedItem();
            deletePositionBasket(product);
        });

        main_basket_button_pay.setOnAction(event -> {
            Stage newWindow = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/application/pay_page.fxml"));
            Map<String, String> params = new HashMap<>();
            params.put("totalSum", String.valueOf(getTotalBasketSum()));
            fxmlLoader.getNamespace().putAll(params);

            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 210, 320);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PayController lc = fxmlLoader.getController();
            lc.mainController = this;
            newWindow.setTitle("Pay");
            newWindow.setScene(scene);
            newWindow.setResizable(false);
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(main_basket_button_pay.getScene().getWindow());
            newWindow.show();
        });
    }

    private void addProductToBasket(Product product) {
        CheckLines checkLine;
        if (basketList.containsKey(product.getName())) {
            checkLine = basketList.get(product.getName());
            checkLine.addIncrease(product);
        } else {
            checkLine = new CheckLines(product);
        }
        basketList.put(product.getName(), checkLine);
        initBasket(basketList);
        main_basket_field_totalSum.setText(String.format("%1$,.2f", getTotalBasketSum()));
        main_table_basket.refresh();
    }

    private void initTable(List<Product> productList) {
        ObservableList<Product> productsData = FXCollections.observableArrayList();
        productsData.addAll(productList);
        main_table_products_ean.setCellValueFactory(new PropertyValueFactory<>("ean"));
        main_table_products_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        main_table_products_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        main_table_products.setItems(productsData);
    }

    private void initBasket(Map<String, CheckLines> basketList) {
        ObservableList<CheckLines> basketData = FXCollections.observableArrayList();
        basketData.addAll(new ArrayList<>(basketList.values()));
        main_table_basket_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        main_table_basket_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        main_table_basket_total.setCellValueFactory(new PropertyValueFactory<>("total"));


        main_table_basket.setItems(basketData);
    }

    public void clearBasket() {
        main_basket_field_totalSum.setText("");
        basketList.clear();
        initBasket(basketList);
        main_table_basket.refresh();
    }
    public void deletePositionBasket(CheckLines checkLines) {
        basketList.remove(checkLines.getName());
        initBasket(basketList);
        main_table_basket.refresh();
    }

    public BigDecimal getTotalBasketSum() {
        return this.basketList.values().stream()
                .map(checkLines -> checkLines.getTotal())
                .reduce((bD1, bD2) -> bD1.add(bD2)).orElse(new BigDecimal("0.0"));
    }

    public void saveBasket() {
        cartService.newCheck(new ArrayList<>(basketList.values()), getTotalBasketSum());
    }
}
