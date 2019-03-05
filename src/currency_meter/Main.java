package currency_meter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int APP_WIDTH = 240;
        final int APP_HEIGHT = 80;
        final int TOP_MARGIN = 20;

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);
        primaryStage.setHeight(0);
        primaryStage.setWidth(0);
        primaryStage.show();

        Stage mainStage = new Stage();
        mainStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - APP_WIDTH);
        mainStage.setY(primaryScreenBounds.getMinY() + TOP_MARGIN);
        mainStage.initOwner(primaryStage);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
