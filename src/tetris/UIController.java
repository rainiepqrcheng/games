package tetris;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;



public class UIController implements Initializable{
	//每块砖块的大小（以像素为单位）
	private static final int Brick_size = 20;
	// 游戏时间轴和事件监听器
	Timeline timeLine;
	public InputEventListener eventListener;
	// 游戏板的显示矩阵和砖块矩阵
	private Rectangle[][] displayMatrix;
	private Rectangle[][] rectangles;
	// 游戏状态属性，用于控制暂停和游戏结束
	private BooleanProperty paused = new SimpleBooleanProperty();
	private BooleanProperty GameOver = new SimpleBooleanProperty();
	
	
	@FXML
	private Button newGameButton;
	@FXML
	private ToggleButton pauseButton;
	@FXML
	private GridPane gamePanel;
	@FXML
	private GridPane nextBrick;
	@FXML
	private GridPane brickPanel;
	@FXML
	private Text scoreValue;
    @FXML
    private Text lineText;
    @FXML
    public Text levelText;
	@FXML
	private Group groupNotice;
	@FXML
	private OverPanel gameOverPanel;
	
    @FXML
    public void handleNewGame(ActionEvent event) {
    	restartGame();
    }
    
	//根据提供的棋盘矩阵和视图数据初始化游戏视图。 
	//boardMatrix 代表游戏板的矩阵。
	//viewData 包含当前砖块信息的 ViewData 对象。
	public void initGameView(int[] [] boardMatrix, ViewData viewData) {
		 // 创建用于显示游戏板的矩形数组
		displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
		
		//迭代游戏矩阵并将矩形添加到游戏面板
		for(int i = 2; i < boardMatrix.length; i++) {
			for(int j = 0; j < boardMatrix[i].length; j++) {
				// 创建矩形对象，设置大小并填充为透明
				Rectangle rectangle = new Rectangle(Brick_size,Brick_size); 
				rectangle.setFill(Color.TRANSPARENT);

				// 将矩形添加到显示矩阵和游戏面板
				displayMatrix[i][j] = rectangle;
				gamePanel.add(rectangle, j, i - 2);
			}
		}
		
		// 创建用于显示当前砖块的矩形数组
		rectangles = new Rectangle[viewData.getBrickData().length][viewData.getBrickData()[0].length];
		
		
		//迭代砖块数据并将矩形添加到砖块面板
		for(int i = 0; i <viewData.getBrickData().length; i++) {
			for (int j = 0; j < viewData.getBrickData()[i].length; j++) {
				// 创建矩形对象，设置大小并根据砖块数据设置填充颜色
				Rectangle rectangle = new Rectangle(Brick_size,Brick_size);
				rectangle.setFill(getFillColor(viewData.getBrickData()[i][j]));
				// 创建矩形对象，设置大小并根据砖块数据设置填充颜色
				rectangles[i][j] = rectangle;
				brickPanel.add(rectangle, j, i);
			}
		}
		//设置Brick初始位置，为游戏板的中间
		brickPanel.setLayoutX(gamePanel.getLayoutX() + (viewData.getxPostition() * Brick_size));
		brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() 
							  + (viewData.getyPostition() * Brick_size) 
							  + viewData.getyPostition());
		// 生成下一个砖块的预览面板
		generatePreviewPanel(viewData.getNextBrickData());
		
		//初始化游戏视图并启动向下移动砖块的时间线。时间轴设置为定期执行 moveDown() 方法,由后台执行
		timeLine = new Timeline(new KeyFrame(Duration.millis(400), 
				ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))));
		//循环计数设置
		timeLine.setCycleCount(Timeline.INDEFINITE);
		//启动时间线
		timeLine.play();
	}
	
	// 通过矩阵生成下一个砖块的图像
	private void generatePreviewPanel(int[][] nextBrickData) {
		nextBrick.getChildren().clear();
		for(int i = 0; i < nextBrickData.length; i++) {
			for(int j = 0; j < nextBrickData[i].length; j++) {
				Rectangle rectangle = new Rectangle(Brick_size, Brick_size);
				setRectangleData(nextBrickData[i][j], rectangle);
				if(nextBrickData[i][j] != 0) {
					nextBrick.add(rectangle, j, i);
				}
			}
		}
		
	}
	
	
	//刷新游戏背景，更新显示矩阵中的砖块颜色。
	public void refreshGameBackground(int[][] board) {
		// 从第三行开始迭代游戏板矩阵
		for(int i = 2; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				 // 根据砖块颜色设置矩阵中对应位置的矩形颜色
				setRectangleData(board[i][j], displayMatrix[i][j]);
				
			}
		}
	}
	
	//设置矩形的填充颜色和圆角。
	private void setRectangleData(int color, Rectangle rectangle) {
		rectangle.setFill(getFillColor(color));
		rectangle.setArcHeight(9);
		rectangle.setArcWidth(9);
	}

	//将分数值绑定到提供的 IntegerProperty。
	public void bindScore(IntegerProperty integerProperty) {
		scoreValue.textProperty().bind(integerProperty.asString());
		// 添加 level 显示
        integerProperty.addListener((observable, oldValue, newValue) -> {
        	int score = newValue.intValue();
        	int level = (score / 200) + 1; // 每200分升一级，初始为1级
            // 更新界面上的 level 显示
            // （假设有一个名为 levelLabel 的 Label 控件用于显示 level）
        	levelText.setText(Integer.toString(level));
        });
        // 初始化 line 和 level 显示
        lineText.setText("0");
        levelText.setText("1");
	}
	

    public void updateLine(int lines) {
    	// 更新界面上的 line 显示
        lineText.setText(Integer.toString(lines));
    }
	
	//初始化游戏视图并启动向下移动砖块的时间线。当行清除后自动下降一行
	private void moveDown(MoveEvent event) {
		DownData downData = eventListener.onDowEvent(event);
		
		//清除行，获得提示
		if(downData.getClearRow() != null 
		   && downData.getClearRow().getLineRemoved() > 0) {
			NoticePanel noticePanel = new NoticePanel(" + " + downData.getClearRow().getScoreBonus());
			groupNotice.getChildren().add(noticePanel);
			noticePanel.showScore(groupNotice.getChildren());
		}
		
		refreshBrick(downData.getViewData());
	}
	
	//刷新界面中的块位置。viewData 包含砖块位置的数据。
	private void refreshBrick(ViewData viewData) {
		// 设置砖块面板的位置
		brickPanel.setLayoutX(gamePanel.getLayoutX() + viewData.getxPostition() *( Brick_size+1));
		brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() 
							  + (viewData.getyPostition() * Brick_size)
							  + viewData.getyPostition());
		// 更新砖块面板中各个小矩形的颜色
		for(int i = 0; i < viewData.getBrickData().length; i++ ) {
			for(int j = 0; j < viewData.getBrickData()[i].length; j++ ) {
				setRectangleData(viewData.getBrickData()[i][j], 
								 rectangles[i][j]);
				
			}
		}
		// 生成下一个砖块的预览面板
		generatePreviewPanel(viewData.getNextBrickData());
	}
	
	//设置用于处理用户输入的输入事件侦听器。
	public void setEventListener(InputEventListener eventListener) {
		this.eventListener = eventListener;
	}

	//根据砖块类型获取矩形的填充颜色。
	public Paint getFillColor(int i) {
		Paint returPaint;
		switch(i) {
		case 0:
			returPaint = Color.TRANSPARENT;
			break;
		case 1:
			returPaint = Color.KHAKI;//OBrick 黄
			break;
		case 2:
			returPaint = Color.MEDIUMORCHID;//IBrick 紫
			break;
		case 3:
			returPaint = Color.PALEGREEN;//JBrick PaleGreen 绿
			break;
		case 4:
			returPaint = Color.ORANGE;//LBrick 橘
			break;
		case 5:
			returPaint = Color.AQUA;//SBrick 蓝
			break;
		case 6:
			returPaint = Color.LIGHTCORAL;//ZBrick 
			break;
		case 7:
			returPaint = Color.SNOW;//TBrick 白	
			break;
		default:
			returPaint = Color.WHITE;
			break;
		}
		
		return returPaint;
	}

	//分数显示时伴随倒影
	//使用指定的 URL 和 ResourceBundle 初始化 UIController。
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 // 设置游戏面板可获取焦点，以便接收键盘事件
		gamePanel.setFocusTraversable(true);
		 // 请求焦点
		gamePanel.requestFocus();
		// 设置游戏面板的键盘事件处理
		gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				//游戏运行时才可以使用移动转向操作
				System.out.println(paused.getValue());
				if (paused.getValue() == Boolean.FALSE && GameOver.getValue() == Boolean.FALSE) {
			        // 当用户按下键盘中R键时，执行游戏重新启动操作
			        if (event.getCode() == KeyCode.R) {
			            restartGame();
			            event.consume();
			        }					
					//当用户按下键盘中W键和SPACE键时，执行方块转向操作
					if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.W) {
						refreshBrick(eventListener.onRotateEvent());
						event.consume();
					}
					//当用户按下键盘中S键和DOWN键时，执行方块下落操作
					if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
						moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
						event.consume();	
					}
					//当用户按下键盘中A键和LEFT键时，执行方块左移操作
					if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
						refreshBrick(eventListener.onLeftEvent());
						event.consume();
					}
					//当用户按下键盘中D键和RIGHT键时，执行方块右移操作
					if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
						refreshBrick(eventListener.onRightEvent());
						event.consume();
					}
				}			
				//当用户按下键盘中P键,游戏暂停
				if (event.getCode() == KeyCode.P) {
					pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
				}
			}
		});
		// 游戏结束面板默认不可见
		gameOverPanel.setVisible(false);
		
		//游戏暂停
		pauseButton.selectedProperty().bindBidirectional(paused);
		pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				 // 设置游戏面板可获取焦点，以便接收键盘事件
				gamePanel.setFocusTraversable(true);
				 // 请求焦点
				gamePanel.requestFocus();
				if(newValue) {
					timeLine.pause();
					pauseButton.setText("Resume");
					
				}else {
					timeLine.play();
					pauseButton.setText("Pause");
				}
				
			}
		});
				
		//为 ScoreValue 文本创建反射效果。 
		Reflection reflection = new Reflection();
		//设置反射效果的属性
		reflection.setFraction(0.8);
		reflection.setTopOpacity(0.9);
		reflection.setTopOffset(-12);
		//将反射效果应用到 ScoreValue 文本
		scoreValue.setEffect(reflection);
		
	}
	
	// 添加 restartGame 方法
	@FXML
	private void restartGame() {
		 // 设置游戏面板可获取焦点，以便接收键盘事件
		gamePanel.setFocusTraversable(true);
		 // 请求焦点
		gamePanel.requestFocus();
	    // 停止原有的时间轴动画
	    timeLine.stop();

	    // 重新初始化游戏
	    gamePanel.getChildren().clear();
	    nextBrick.getChildren().clear();
	    brickPanel.getChildren().clear();
	    groupNotice.getChildren().clear();
	    gameOverPanel.setVisible(false);

	    // 创建新的游戏控制器并初始化游戏
	    new GameController(this);

	    // 启动新的时间轴动画
	    timeLine.play();
	}
	
	//处理游戏结束。
	public void gameOver() {
		// 停止时间轴动画
		timeLine.stop();
		// 显示游戏结束面板
		gameOverPanel.setVisible(true);
		GameOver.setValue(Boolean.TRUE);
	}
}
