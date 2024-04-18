package tank;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Director.getInstance().init(primaryStage);
    }

    public static void createNewWindow() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            // ���ò���ʾ����̨
            stage.show();
        });
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
