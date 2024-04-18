package eatingsnake;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Snake extends ImageView {
    // 表示蛇是否在移动的标志
    protected boolean isMoving = false;
    // 蛇的类型，例如"head"或"body"
    protected String type;
    // 控制蛇移动方向的标志
    protected boolean goleft = false, goright = false, goup = false, godown = false;
    // 游戏的主画布，在这个画布上蛇将被绘制
    protected AnchorPane pane;
    // 蛇的移动速度
    protected int speed;
    // 蛇身体部分的尺寸
    protected static final int SIZE = 20;
    // 指向蛇的前一个身体部分的引用，用于链接蛇的各个部分
    protected Snake previousSnake;
    // 记录蛇的前一个位置，用于移动和碰撞检测
    protected double pastX, pastY;
    
    // 构造函数，初始化蛇的基本属性
    public Snake(AnchorPane pane) {
        this.pane = pane; // 设置游戏的主画布
        speed = 20; // 设置默认的移动速度
        this.setFitHeight(SIZE); // 设置蛇身体部分的高度
        this.setFitWidth(SIZE); // 设置蛇身体部分的宽度
        pastX = this.getLayoutX(); // 初始化蛇的X坐标
        pastY = this.getLayoutY(); // 初始化蛇的Y坐标
    }
    
    // 抽象方法，所有继承自Snake的类都必须实现这个方法以定义蛇的移动逻辑
    protected abstract void move();
}
