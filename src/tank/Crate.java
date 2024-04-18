package tank;

import javafx.scene.image.Image;

public class Crate extends Sprite {
    public Crate(double x, double y) {
        super(new Image(Crate.class.getResourceAsStream("/config/image/Crate.png")), x, y, 31, 32);
    }
}
