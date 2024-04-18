package tank;

import javafx.scene.image.Image;

public class Tree extends Sprite {

    public Tree( double x, double y) {
        super(new Image(Tree.class.getResourceAsStream("/config/image/TreeBig.png")), x, y, 86, 86);
    }
}
