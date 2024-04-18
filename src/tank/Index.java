package tank;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Index {

    public static void load(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Index.class.getResource("/config/fxml/index.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
