package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../view/prim.fxml"));
		primaryStage.setTitle("Добро пожаловать!");
		primaryStage.setScene(new Scene(root, 650, 400));
		primaryStage.setMinWidth(650);
		primaryStage.setMinHeight(450);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}