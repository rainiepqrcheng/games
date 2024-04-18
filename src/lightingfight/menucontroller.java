package lightingfight;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class menucontroller {
   private Label highestScore=new Label();
   private Unit unit =new Unit();
   private ImageView background;
   @FXML
   private ImageView start01;
   @FXML
   private AnchorPane pane00;
   @FXML
   private ImageView del;
   private String url;
   @FXML
   private void initialize(){
      
      background = new ImageView(unit.getstratb());
      background.setLayoutX(0);
      background.setLayoutY(0);
      background.fitHeightProperty().bind(pane00.heightProperty());
      background.fitWidthProperty().bind(pane00.widthProperty());
      pane00.getChildren().add(background);
      start01.setFitWidth(200);
      start01.setFitHeight(90);
     
      del.setFitHeight(60);
      del.setFitHeight(180);
      del.setLayoutX((860 - del.getFitWidth()) / 2-90);
      del.setImage(Unit.getdel());

      
      start01.setLayoutX((860 - start01.getFitWidth()) / 2);
      start01.setLayoutY(660);
      background.toBack();
     
      highestScore.setLayoutX(200);
     highestScore.setLayoutY(600);
     highestScore.setFont(new Font("Arial", 24));
     url = "jdbc:sqlite:data/ranking.db";
     String sql = "CREATE TABLE IF NOT EXISTS ranking (\n"
         + " id integer PRIMARY KEY,\n"
         + " score integer NOT NULL,\n"
         + " player_name text NOT NULL\n"
         + ");";
     String findbiggest = "SELECT player_name, score FROM ranking ORDER BY score DESC LIMIT 1";  
    
     try(Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement())
     {
      stmt.execute(sql);
     
      ResultSet rs1 = stmt.executeQuery(findbiggest);
      if (rs1.next()) 
      {
        String highestScoringPlayer = rs1.getString("player_name");
        int highestScore1 = rs1.getInt("score");
        highestScore.setText("The highest score is:"+highestScore1+"created by"+highestScoringPlayer);
      }else
      {
        highestScore.setText("No one has played yet, Have a try!");
      }
      
     } catch (Exception e) 
     {
      e.printStackTrace();
     }
     pane00.getChildren().add(highestScore);
     highestScore.toFront();
   }
   @FXML
   private void click01()throws IOException{
    App.setRoot("lecture");
   }
  @FXML
   private void click02() throws IOException
   {
    String deleteAll = "DELETE FROM ranking"; 

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
        // Execute the delete statement
         stmt.executeUpdate(deleteAll);
        highestScore.setText("No one has played yet, Have a try");
    } catch (SQLException e) {
        e.printStackTrace();
  
    }
   }
}
