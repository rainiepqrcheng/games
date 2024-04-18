package eatingsnake;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.Random;

public class Boom extends ImageView {
    // 炸弹图片的路径
    private static final String BOOM_IMAGE_PATH = "/config/boom.png";
    // 游戏的根面板，用于添加炸弹
    private AnchorPane rootPane;
    // 记录炸弹之前的位置
    private double pastx, pasty;
    // 随机数生成器，用于生成炸弹的随机位置
    private Random random = new Random();

    // 构造函数，初始化炸弹
    public Boom(AnchorPane rootPane) {
        this.rootPane = rootPane;
        // 设置炸弹的图像
        setImage(new Image(Boom.class.getResourceAsStream(BOOM_IMAGE_PATH)));
        // 设置炸弹的尺寸
        setFitHeight(20);
        setFitWidth(20);
        // 随机生成炸弹的位置
        double x = random.nextInt(830);
        double y = random.nextInt(410);
        // 记录炸弹的初始位置
        pastx = x;
        pasty = y;
        // 设置炸弹的布局位置
        setLayoutX(x);
        setLayoutY(y);
        // 将炸弹添加到根面板中
        rootPane.getChildren().add(this);
    }

    public void resetPosition(Snake head) {
        double x, y;
        do {
            // 随机生成新的炸弹位置
            x = random.nextInt(830);
            y = random.nextInt(410);
            // 循环直到找到一个与蛇头和之前炸弹位置不太接近的新位置
        } while (isTooClose(x, y, pastx, pasty) || isTooCloseToSnakeHead(x, y, head));
    
        // 更新炸弹的新位置
        pastx = x;
        pasty = y;
        setLayoutX(x);
        setLayoutY(y);
    }
    
    // 检查新位置是否与前一个炸弹位置过于接近
    private boolean isTooClose(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) < 40 && Math.abs(y1 - y2) < 40;
    }
    
    // 检查新位置是否与蛇头过于接近
    private boolean isTooCloseToSnakeHead(double x, double y, Snake head) {
        return Math.abs(x - head.getLayoutX()) < 40 && Math.abs(y - head.getLayoutY()) < 40;
    }
}
