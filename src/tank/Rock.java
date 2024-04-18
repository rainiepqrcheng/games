package tank;

import javafx.scene.image.Image;

public class Rock extends Sprite {

    public Rock(double x, double y) {
        super(new Image(Rock.class.getResourceAsStream("/config/image/Rock.png")), x, y, 71, 61);
    }
}
