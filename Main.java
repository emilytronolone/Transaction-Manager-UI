/**
@author Devin Gulati, Emily Tronolone
*/

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TransactionManager.fxml"));
        primaryStage.setTitle("primaryStage");
        primaryStage.setScene(new Scene(root, 730, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}