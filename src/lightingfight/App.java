package lightingfight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primestage;

    @Override
    public void start(Stage stage) throws IOException {
        primestage =stage;
        scene = new Scene(loadFXML("startmenu"), 860, 860);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("/config/fight/" +fxml+".fxml"));
     return root;
    }

    public static void main(String[] args) {
   
        launch();
       
    }
    
    
    
    public static Stage getprimeStage()
    {
     return primestage;
    }

    

}