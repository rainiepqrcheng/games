package eatingsnake;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * 贪吃蛇游戏中的蛇头类，继承自Snake基类。
 */
public class snakehead extends Snake {

    /**
     * 蛇头的构造函数。
     * @param pane 游戏的主画布，蛇头将在这个画布上显示。
     */
    public snakehead(AnchorPane pane) {
        super(pane); // 调用父类的构造函数进行初始化
        // 设置蛇头的图像
        setImage(new Image(App.class.getResourceAsStream("/config/body.png")));
        // 设置蛇头的初始位置
        this.setLayoutX(200);
        this.setLayoutY(200);
    }

    /**
     * 根据当前的移动方向更新蛇头的位置。
     */
    @Override
    public void move() {
        // 保存当前位置，以备后续使用
        double tempX = this.getLayoutX();
        double tempY = this.getLayoutY();

        // 根据移动方向更新位置
        if (goup) {
            this.setLayoutY(this.getLayoutY() - speed); // 向上移动
        }
        if (godown) {
            this.setLayoutY(this.getLayoutY() + speed); // 向下移动
        }
        if (goleft) {
            this.setLayoutX(this.getLayoutX() - speed); // 向左移动
        }
        if (goright) {
            this.setLayoutX(this.getLayoutX() + speed); // 向右移动
        }

        // 更新上一次的位置记录
        pastX = tempX;
        pastY = tempY;
    }
}
