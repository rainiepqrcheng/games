package tank;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class IndexController {

    @FXML
    private ImageView startGame;

    @FXML
    void mouseClickedStartGame(MouseEvent event) {
        SoundEffect.play("/config/sound/done.wav");
        Director.getInstance().gameStart();
    }

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
        startGame.setOpacity(0.8);
        SoundEffect.play("/config/sound/button.wav");
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

}
