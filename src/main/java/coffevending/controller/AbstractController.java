package coffevending.controller;

import coffevending.service.GoodsService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractController {

    protected GoodsService goodsService = new GoodsService();

    public void showNextPage(Button button, String pageUrl) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pageUrl));
        loadPage(loader);
    }

    public void showNextPage(String pageUrl, Map<String, String> params) {
        FXMLLoader loader = new FXMLLoader();
        loader.getNamespace().putAll(params);
        loader.setLocation(getClass().getResource(pageUrl));
        loadPage(loader);
    }

    public void loadPage(FXMLLoader loader) {
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.show();
    }
}