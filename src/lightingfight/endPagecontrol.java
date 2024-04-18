package lightingfight;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class endPagecontrol {
    @FXML
    private AnchorPane endpane;
    @FXML
    private Label Score_showing;
    @FXML
    private Button back;
    @FXML
    private TextField name_entre;
    private int finalscore, number;
    private ImageView background;
    private String url = "jdbc:sqlite:data/ranking.db";
    @FXML
    private void initialize()
    { 
        background =new ImageView();
        background.setLayoutX(0);
        background.setLayoutY(0);
        background.setFitHeight(860);
        background.setFitWidth(860);
        background.setImage(Unit.getbackground03());
        endpane.getChildren().add(background);
        background.toBack();

        back.setLayoutX(App.getprimeStage().getWidth()/2-back.getPrefWidth()/2-50);
        back.setLayoutY(App.getprimeStage().getHeight()/2-back.getPrefHeight()+300);       
        Score_showing.setLayoutX(App.getprimeStage().getWidth()/2-Score_showing.getPrefWidth()/2-100);
        Score_showing.setLayoutY(back.getLayoutY()-100);
        name_entre.setLayoutX(App.getprimeStage().getWidth()/2-name_entre.getWidth()/2-100);
        name_entre.setLayoutY(back.getLayoutY()-50);
        finalscore = scoretransfer.getunit().getscore();
        Score_showing.setText("Final Score: " + finalscore);


     
        
    }
    
    
    @FXML
    private  void click()throws IOException
    {
      String insert = "INSERT INTO ranking(score, player_name) VALUES(?, ?)";
      try {
      
        //used to insert the 
       try (Connection conn = DriverManager.getConnection(url);
       PreparedStatement ptmt = conn.prepareStatement(insert)) {
        ptmt.setInt(1, finalscore);
        ptmt.setString(2, name_entre.getText());
        ptmt.executeUpdate();
        //get the number of record
        try (Statement stmt = conn.createStatement()) 
        {
           String sql = "SELECT COUNT(*) AS count FROM ranking;";
           ResultSet rs = stmt.executeQuery(sql);
           if (rs.next()) 
           { 
               number = rs.getInt("count");
               System.out.println(number);
           }

        }

        //delete the
        if (number >10) {
            String deleteSql = "DELETE FROM ranking WHERE id = (SELECT id FROM ranking ORDER BY score ASC LIMIT 1)";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(deleteSql);
            }
        
    
    }
       }catch (Exception e) {
       e.printStackTrace(); 
       }

       
        
        App.setRoot("startmenu");
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }

}
