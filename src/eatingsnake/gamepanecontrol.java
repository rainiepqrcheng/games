package eatingsnake;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class gamepanecontrol { 
    @FXML
    private AnchorPane rootPane; // 根面板
    @FXML
    private ImageView background; // 背景图
    private Label scoreLabel; // 分数标签
    private ArrayList<Snake> snakeParts = new ArrayList<>(); // 存储蛇的所有部分
    private ArrayList<Boom> Booms = new ArrayList<>(); // 存储炸弹
    private Snake snakeHead; // 蛇头
    private food food; // 食物
    private Timeline generatefood, snakemove, generateBoom; // 时间线，用于食物生成、蛇移动、炸弹生成
    private IntegerProperty score = new SimpleIntegerProperty(0); // 分数
    
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            background.setFitHeight(1030); // 设置背景高度
            background.setFitWidth(1560); // 设置背景宽度
            background.setImage(new Image(App.class.getResourceAsStream("/config/grass.jpg")));
            snakeHead = new snakehead(rootPane); // 初始化蛇头
            rootPane.getChildren().add(snakeHead); // 将蛇头添加到根面板
            snakeParts.add(snakeHead); // 将蛇头添加到蛇的部分列表中
            food = new food(snakeHead, snakeParts, rootPane); // 初始化食物
            scoreLabel = new Label(); // 创建分数标签
            scoreLabel.setText("Score: 0"); // 设置分数标签的初始文本
            scoreLabel.textProperty().bind(score.asString("Use W,S,A,D to control the direction. Score: %d")); // 绑定分数属性到标签的文本
            scoreLabel.setLayoutX(10); // 设置分数标签的X位置
            scoreLabel.setLayoutY(10); // 设置分数标签的Y位置
            scoreLabel.toFront(); // 将分数标签置于顶层
            Button pauseButton = new Button("Stop"); // 创建暂停按钮
            pauseButton.setLayoutX(800); // 设置暂停按钮的X位置
            pauseButton.setLayoutY(10); // 设置暂停按钮的Y位置
            pauseButton.setOnAction(event -> press()); // 为暂停按钮设置事件处理器
            rootPane.getChildren().add(pauseButton); // 将暂停按钮添加到根面板
            rootPane.getChildren().add(scoreLabel); // 将分数标签添加到根面板
            for (int i = 0; i < 6; i++) {
                Booms.add(new Boom(rootPane)); // 初始化炸弹并添加到列表
            }
            createfood(); // 创建食物
            setsnakemove(); // 设置蛇移动
        });
    }

    @FXML
    private void click01(KeyEvent event) { 
        // 键盘事件处理，根据按键改变蛇头的方向
        switch (event.getCode()) {
            case W:
                if (snakeHead.godown == true) { return; }
                snakeHead.goup = true; snakeHead.godown = false; snakeHead.goleft = false; snakeHead.goright = false;
                break;
            case S:
                if (snakeHead.goup == true) { return; }
                snakeHead.godown = true; snakeHead.goup = false; snakeHead.goleft = false; snakeHead.goright = false;
                break;
            case A:
                if (snakeHead.goright == true) { return; }
                snakeHead.goleft = true; snakeHead.goright = false; snakeHead.goup = false; snakeHead.godown = false;
                break;
            case D:
                if (snakeHead.goleft == true) { return; }
                snakeHead.goright = true; snakeHead.goleft = false; snakeHead.goup = false; snakeHead.godown = false;
                break;
        }
    }

    // 创建食物的方法
    private void createfood() {
        generatefood = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            for (Snake snake : snakeParts) {
                if (food.getstate() == true) { 
                	score.set(score.get() + 1); 
                	scoretransfer.getunit().setscore(scoretransfer.getunit().getscore() + 1);
                } // 如果食物状态为true，增加分数
                if (food.getstate() == true || food.getBoundsInParent().intersects(snake.getBoundsInParent())) {
                    food.resetPosition(); // 重置食物位置
                    for (Boom boom : Booms) {
                        boom.resetPosition(snakeHead); // 重置炸弹位置
                    }
                }
            }
            for (Boom boom : Booms) {
                if (food.getBoundsInParent().intersects(boom.getBoundsInParent())) {
                    food.resetPosition(); // 如果食物与炸弹位置重叠，重置食物位置
                }
            }
        }));
        generatefood.setCycleCount(Timeline.INDEFINITE);
        generatefood.play();
    }

    // 设置蛇移动的方法
    private void setsnakemove() {
        snakemove = new Timeline(new KeyFrame(Duration.millis(96), event -> {
            // 计算蛇头下一步的位置，检查碰撞
            double nextX = snakeHead.getLayoutX();
            double nextY = snakeHead.getLayoutY();

            // 检查是否与墙壁碰撞
            if (nextX < 0 || nextX > 860 - snakeHead.getFitWidth() || nextY < 0 || nextY > 430 - snakeHead.getFitHeight()) {
                stopGame(); // 停止游戏
                return;
            }

            // 检查是否与炸弹碰撞
            for (Boom boom : Booms) {
                double deltaX = (snakeHead.getLayoutX() + snakeHead.getFitWidth() / 2) - (boom.getLayoutX() + boom.getFitWidth() / 2);
                double deltaY = (boom.getLayoutY() + boom.getFitHeight() / 2) - (snakeHead.getLayoutY() + snakeHead.getFitHeight() / 2);
                double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                if (length < 10) {
                    stopGame(); // 停止游戏
                    return;
                }
            }

            // 检查是否与蛇身相撞
            for (int i = 1; i < snakeParts.size() - 1; i++) {
                Snake snake = snakeParts.get(i);
                double deltaX = (snakeHead.getLayoutX() + snakeHead.getFitWidth() / 2) - (snake.getLayoutX() + snake.getFitWidth() / 2);
                double deltaY = (snake.getLayoutY() + snake.getFitHeight() / 2) - (snakeHead.getLayoutY() + snakeHead.getFitHeight() / 2);
                double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                if (length < 10) {
                    stopGame(); // 停止游戏
                    return;
                }
            }

            // 移动蛇头和蛇身
            for (Snake snake : snakeParts) {
                snake.move();
            }
        }));
        snakemove.setCycleCount(Timeline.INDEFINITE);
        snakemove.play();
    }

    // 停止游戏的方法
    private void stopGame() {
        Platform.runLater(() -> {
            // 停止所有动画或时间线
            if (snakemove != null) {
                snakemove.stop();
            }
            if (generatefood != null) {
                generatefood.stop();
            }
            // 显示游戏结束的信息
            try {
                App.setRoot("endpage"); // 切换到游戏结束页面
            } catch (IOException e) {
                e.printStackTrace(); // 打印异常信息
            }
        });
    }

    // 暂停和继续游戏的方法
    private void press() {
        if (snakemove != null) {
            if (snakemove.getStatus() == Timeline.Status.RUNNING) {
                snakemove.pause(); // 如果时间线正在运行，就暂停它
            } else {
                snakemove.play(); // 否则，继续运行时间线
            }
        }
    }

}
