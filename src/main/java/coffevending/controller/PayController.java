package coffevending.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PayController {

    public static final String PAGE_URL = "/application/pay_page.fxml";
    protected MainController mainController;

    @FXML
    VBox globParent;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pay_button_cancel;

    @FXML
    private Button pay_button_confirm;

    @FXML
    private TextField pay_inputSum_field;

    @FXML
    private Label pay_totalSum_text;

    @FXML
    void initialize() {

        pay_button_confirm.setOnAction(event -> {
                String inputString = pay_inputSum_field.getText();
                BigDecimal bigDecimal = null;
                try {
                    bigDecimal = new BigDecimal(inputString);
                } catch (NumberFormatException e){
                    pay_inputSum_field.setText(pay_totalSum_text.getText());
                }

                if(bigDecimal != null && bigDecimal.compareTo(mainController.getTotalBasketSum())==0) {
                    mainController.saveBasket();
                    mainController.clearBasket();
                    pay_button_confirm.getScene().getWindow().hide();
                } else {
                    pay_inputSum_field.setText(pay_totalSum_text.getText());
                }
        });

        pay_button_cancel.setOnAction(event -> {
            pay_button_cancel.getScene().getWindow().hide();
        });
    }

}