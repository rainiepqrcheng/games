package lightingfight;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class lecturecontroller {
@FXML
private ImageView Background;
@FXML
private ImageView conTinue;
@FXML
private ImageView candy;
@FXML
private ImageView ham;
@FXML
private Label move;
@FXML
private Label shoot;
@FXML
private Label upload;
@FXML
private Label heal;
@FXML
private Label kill;
@FXML
private ImageView back;
@FXML
private ImageView example;

@FXML
private void initialize()
{
    Background.setImage(Unit.getbackground02());
    ham.setImage(Unit.getupgrade1());
    candy.setImage(Unit.gethealing());
    conTinue.setImage(Unit.getcontinue());
    example.setImage(Unit.getenemy01());
}

@FXML
private void click01()throws IOException{
    App.setRoot("gamelevel1");
   }
@FXML
private void click02()throws IOException{
    App.setRoot("startmenu");
   }
}
