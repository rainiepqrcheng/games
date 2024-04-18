package eatingsnake;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class snakebody extends Snake{
    // 静态变量，用于示例，实际中可能未使用
    private static int a = 1;

    // 构造函数，初始化蛇身
    public snakebody(AnchorPane pane) {
        super(pane); // 调用父类（Snake类）的构造函数
        type = "body"; // 设置蛇身类型为“body”
        // 设置蛇身的图片
        setImage(new Image(App.class.getResourceAsStream("/config/body.png")));
    }

    // 根据头部的方向设置蛇身的初始位置
    private void setbodyLayout(Snake head) {
        // 如果蛇头向下移动
        if (head.godown == true) {
            // 蛇身应出现在蛇头的上方
            this.setLayoutY(previousSnake.getLayoutY() - 20);
            this.setLayoutX(previousSnake.getLayoutX());
        }
        // 如果蛇头向上移动
        if (head.goup == true) {
            // 蛇身应出现在蛇头的下方
            this.setLayoutY(previousSnake.getLayoutY() + 20);
            this.setLayoutX(previousSnake.getLayoutX());
        }
        // 如果蛇头向左移动
        if (head.goleft == true) {
            // 蛇身应出现在蛇头的右侧
            this.setLayoutY(previousSnake.getLayoutY());
            this.setLayoutX(previousSnake.getLayoutX() + 20);
        }
        // 如果蛇头向右移动
        if (head.goright == true) {
            // 蛇身应出现在蛇头的左侧
            this.setLayoutY(previousSnake.getLayoutY());
            this.setLayoutX(previousSnake.getLayoutX() - 20);
        }
    }

    // 设置此蛇身的前一个蛇身和蛇头，用于确定位置
    public void setpreviousSnake(Snake snake, Snake head) {
        this.previousSnake = snake; // 设置前一个蛇身
        setbodyLayout(head); // 根据蛇头的方向设置蛇身的初始位置
    }

    // 移动蛇身到前一个蛇身的前一个位置
    public void move() {
        pastX = this.getLayoutX(); // 保存当前的X位置
        pastY = this.getLayoutY(); // 保存当前的Y位置
        // 将蛇身移动到前一个蛇身的位置
        this.setLayoutX(previousSnake.pastX);
        this.setLayoutY(previousSnake.pastY);
    }
}
