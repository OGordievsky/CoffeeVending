package coffevending.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import coffevending.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController extends AbstractController {

    public static final String PAGE_URL = "/application/main.fxml";

    private List<Product> productList;
    private final HashMap<Product, BasketLine> basketList = new HashMap<>();

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
    private TableView<BasketLine> main_table_basket;

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
    void initialize() {
        this.productList = goodsService.getAll();
        initTable(productList);

        main_filter_field.textProperty().addListener((observable, oldValue, newValue) -> {
            initTable(goodsService.getFilter(productList, newValue));
        });

        main_filter_button_clear.setOnAction(event -> {
            main_filter_field.setText("");
        });

        main_table_button_add.setOnAction(event -> {
            Product product = main_table_products.getFocusModel().getFocusedItem();
            BasketLine basketLine;
            if (basketList.containsKey(product)) {
                basketLine = basketList.get(product);
                basketLine.addIncrease(product);
            } else {
                basketLine = new BasketLine(product);
            }
            basketList.put(product,basketLine);
            initBasket(basketList);
            main_basket_field_totalSum.setText(String.format("%1$,.2f", basketList.values().stream().mapToDouble(bl -> bl.total.doubleValue()).sum()));
            main_table_basket.refresh();
        });

        main_basket_button_pay.setOnAction(event -> {
            Map<String, String> params = new HashMap<>();
            params.put("totalSum", main_basket_field_totalSum.getText() + " руб");
            showNextPage(PayController.PAGE_URL, params);
        });
    }

    private void initTable(List<Product> productList) {
        ObservableList<Product> productsData = FXCollections.observableArrayList();
        productsData.addAll(productList);
        main_table_products_ean.setCellValueFactory(new PropertyValueFactory<>("ean"));
        main_table_products_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        main_table_products_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        main_table_products.setItems(productsData);
    }

    private void initBasket(Map<Product, BasketLine> basketList) {
        ObservableList<BasketLine> basketData = FXCollections.observableArrayList();
        basketData.addAll(new ArrayList<>(basketList.values()));
        main_table_basket_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        main_table_basket_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        main_table_basket_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        main_table_basket.setItems(basketData);
    }


    public class BasketLine {
        private String name;
        private int count;
        private BigDecimal total;

        private BasketLine(Product product) {
            this.name = product.getName();
            this.count = 1;
            this.total = new BigDecimal(String.valueOf(product.getPrice()));
        }

        private void addIncrease(Product product) {
            this.count++;
            this.total = this.total.add(product.getPrice());
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public BigDecimal getTotal() {
            return total;
        }
    }
}
