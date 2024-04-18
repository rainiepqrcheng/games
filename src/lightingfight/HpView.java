package lightingfight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Stack;

public class HpView {
    private ArrayList<ImageView> hpViews;
    private AnchorPane pane;
    private int maxHp;
    private Unit unit;
    private StackPane pane01;
    private final double width = 60; 
    private final double height = 60; 
    private final double margin = 10; 

    public HpView(AnchorPane pane, int hp,int maxHP,StackPane pane01) {
        this.pane = pane;
        this.pane01 =pane01;
        this.maxHp =maxHP;
        this.maxHp = hp;
        this.hpViews = new ArrayList<>();
        unit =new Unit();

        for (int i = 0; i < hp; i++) {
            ImageView imageView = new ImageView(unit.gethp()); 
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
        
            double posX = pane01.getWidth() - (width + margin) * (i + 1);
            double posY = pane01.getHeight() - height- margin;
            imageView.setLayoutX(posX);
            imageView.setLayoutY(posY);
            hpViews.add(imageView);
            pane.getChildren().add(imageView);
        }
    }

    public void decreaseHp() {
        if (!hpViews.isEmpty()) {
            ImageView imageView = hpViews.remove(hpViews.size() - 1);
            pane.getChildren().remove(imageView);
        }
    }

    public void increaseHp() {
        
            ImageView imageView = new ImageView(unit.gethp()); 
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            
            double posX = pane01.getWidth() - (width + margin) * (hpViews.size() + 1);
            double posY = pane01.getHeight() - height - margin;
            imageView.setLayoutX(posX);
            imageView.setLayoutY(posY);

            hpViews.add(imageView);
            pane.getChildren().add(imageView);
        
    }
}
