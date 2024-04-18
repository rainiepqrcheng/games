package tank;

import javafx.scene.image.Image;

public class Background extends Sprite {
    public Background() {
        super(new Image(Background.class.getResourceAsStream("/config/image/LevelsBackground.jpg")), 0, 0, 960, 640);
    }
}
