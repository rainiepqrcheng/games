package eatingsnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX应用主类
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primestage; // 主舞台

    @Override
    public void start(Stage stage) throws IOException {
        primestage = stage; // 初始化主舞台
        // 设置初始场景，从FXML文件加载
        scene = new Scene(loadFXML("eatinggame"), 860, 430);
        stage.setScene(scene);
        stage.show(); // 显示舞台
    }

    // 更换场景的根节点到新的FXML文件
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // 辅助方法，用于从FXML文件加载布局
    private static Parent loadFXML(String fxml) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("/config/" + fxml + ".fxml"));
        return root;
    }

    // 程序入口
    public static void startSnake() {
        launch(); // 启动JavaFX应用
    }

    // 程序入口
    public static void main(String[] args) {
        launch(); // 启动JavaFX应用
    }
    
    // 获取主舞台的方法
    public static Stage getprimeStage() {
        return primestage;
    }
}
