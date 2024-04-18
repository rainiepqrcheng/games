package tetris;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL location = getClass().getResource("/config/gameLayout.fxml");
		
		ResourceBundle resource = null;
		FXMLLoader fxmlLoader = new FXMLLoader(location, resource);
		Parent root = fxmlLoader.load();
		UIController c = fxmlLoader.getController();
		
		primaryStage.setTitle("Tetris");
		Scene scene = new Scene(root, 410, 525);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new GameController(c);
	}
	public static void createNewWindow() {
		Platform.runLater(() -> {
			Stage stage = new Stage();
			// 配置并显示新舞台
			stage.show();
		});
	}
}
