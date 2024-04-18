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
	//ÿ��ש��Ĵ�С��������Ϊ��λ��
	private static final int Brick_size = 20;
	// ��Ϸʱ������¼�������
	Timeline timeLine;
	public InputEventListener eventListener;
	// ��Ϸ�����ʾ�����ש�����
	private Rectangle[][] displayMatrix;
	private Rectangle[][] rectangles;
	// ��Ϸ״̬���ԣ����ڿ�����ͣ����Ϸ����
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
    
	//�����ṩ�����̾������ͼ���ݳ�ʼ����Ϸ��ͼ�� 
	//boardMatrix ������Ϸ��ľ���
	//viewData ������ǰש����Ϣ�� ViewData ����
	public void initGameView(int[] [] boardMatrix, ViewData viewData) {
		 // ����������ʾ��Ϸ��ľ�������
		displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
		
		//������Ϸ���󲢽�������ӵ���Ϸ���
		for(int i = 2; i < boardMatrix.length; i++) {
			for(int j = 0; j < boardMatrix[i].length; j++) {
				// �������ζ������ô�С�����Ϊ͸��
				Rectangle rectangle = new Rectangle(Brick_size,Brick_size); 
				rectangle.setFill(Color.TRANSPARENT);

				// ��������ӵ���ʾ�������Ϸ���
				displayMatrix[i][j] = rectangle;
				gamePanel.add(rectangle, j, i - 2);
			}
		}
		
		// ����������ʾ��ǰש��ľ�������
		rectangles = new Rectangle[viewData.getBrickData().length][viewData.getBrickData()[0].length];
		
		
		//����ש�����ݲ���������ӵ�ש�����
		for(int i = 0; i <viewData.getBrickData().length; i++) {
			for (int j = 0; j < viewData.getBrickData()[i].length; j++) {
				// �������ζ������ô�С������ש���������������ɫ
				Rectangle rectangle = new Rectangle(Brick_size,Brick_size);
				rectangle.setFill(getFillColor(viewData.getBrickData()[i][j]));
				// �������ζ������ô�С������ש���������������ɫ
				rectangles[i][j] = rectangle;
				brickPanel.add(rectangle, j, i);
			}
		}
		//����Brick��ʼλ�ã�Ϊ��Ϸ����м�
		brickPanel.setLayoutX(gamePanel.getLayoutX() + (viewData.getxPostition() * Brick_size));
		brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() 
							  + (viewData.getyPostition() * Brick_size) 
							  + viewData.getyPostition());
		// ������һ��ש���Ԥ�����
		generatePreviewPanel(viewData.getNextBrickData());
		
		//��ʼ����Ϸ��ͼ�����������ƶ�ש���ʱ���ߡ�ʱ��������Ϊ����ִ�� moveDown() ����,�ɺ�ִ̨��
		timeLine = new Timeline(new KeyFrame(Duration.millis(400), 
				ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))));
		//ѭ����������
		timeLine.setCycleCount(Timeline.INDEFINITE);
		//����ʱ����
		timeLine.play();
	}
	
	// ͨ������������һ��ש���ͼ��
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
	
	
	//ˢ����Ϸ������������ʾ�����е�ש����ɫ��
	public void refreshGameBackground(int[][] board) {
		// �ӵ����п�ʼ������Ϸ�����
		for(int i = 2; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				 // ����ש����ɫ���þ����ж�Ӧλ�õľ�����ɫ
				setRectangleData(board[i][j], displayMatrix[i][j]);
				
			}
		}
	}
	
	//���þ��ε������ɫ��Բ�ǡ�
	private void setRectangleData(int color, Rectangle rectangle) {
		rectangle.setFill(getFillColor(color));
		rectangle.setArcHeight(9);
		rectangle.setArcWidth(9);
	}

	//������ֵ�󶨵��ṩ�� IntegerProperty��
	public void bindScore(IntegerProperty integerProperty) {
		scoreValue.textProperty().bind(integerProperty.asString());
		// ��� level ��ʾ
        integerProperty.addListener((observable, oldValue, newValue) -> {
        	int score = newValue.intValue();
        	int level = (score / 200) + 1; // ÿ200����һ������ʼΪ1��
            // ���½����ϵ� level ��ʾ
            // ��������һ����Ϊ levelLabel �� Label �ؼ�������ʾ level��
        	levelText.setText(Integer.toString(level));
        });
        // ��ʼ�� line �� level ��ʾ
        lineText.setText("0");
        levelText.setText("1");
	}
	

    public void updateLine(int lines) {
    	// ���½����ϵ� line ��ʾ
        lineText.setText(Integer.toString(lines));
    }
	
	//��ʼ����Ϸ��ͼ�����������ƶ�ש���ʱ���ߡ�����������Զ��½�һ��
	private void moveDown(MoveEvent event) {
		DownData downData = eventListener.onDowEvent(event);
		
		//����У������ʾ
		if(downData.getClearRow() != null 
		   && downData.getClearRow().getLineRemoved() > 0) {
			NoticePanel noticePanel = new NoticePanel(" + " + downData.getClearRow().getScoreBonus());
			groupNotice.getChildren().add(noticePanel);
			noticePanel.showScore(groupNotice.getChildren());
		}
		
		refreshBrick(downData.getViewData());
	}
	
	//ˢ�½����еĿ�λ�á�viewData ����ש��λ�õ����ݡ�
	private void refreshBrick(ViewData viewData) {
		// ����ש������λ��
		brickPanel.setLayoutX(gamePanel.getLayoutX() + viewData.getxPostition() *( Brick_size+1));
		brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() 
							  + (viewData.getyPostition() * Brick_size)
							  + viewData.getyPostition());
		// ����ש������и���С���ε���ɫ
		for(int i = 0; i < viewData.getBrickData().length; i++ ) {
			for(int j = 0; j < viewData.getBrickData()[i].length; j++ ) {
				setRectangleData(viewData.getBrickData()[i][j], 
								 rectangles[i][j]);
				
			}
		}
		// ������һ��ש���Ԥ�����
		generatePreviewPanel(viewData.getNextBrickData());
	}
	
	//�������ڴ����û�����������¼���������
	public void setEventListener(InputEventListener eventListener) {
		this.eventListener = eventListener;
	}

	//����ש�����ͻ�ȡ���ε������ɫ��
	public Paint getFillColor(int i) {
		Paint returPaint;
		switch(i) {
		case 0:
			returPaint = Color.TRANSPARENT;
			break;
		case 1:
			returPaint = Color.KHAKI;//OBrick ��
			break;
		case 2:
			returPaint = Color.MEDIUMORCHID;//IBrick ��
			break;
		case 3:
			returPaint = Color.PALEGREEN;//JBrick PaleGreen ��
			break;
		case 4:
			returPaint = Color.ORANGE;//LBrick ��
			break;
		case 5:
			returPaint = Color.AQUA;//SBrick ��
			break;
		case 6:
			returPaint = Color.LIGHTCORAL;//ZBrick 
			break;
		case 7:
			returPaint = Color.SNOW;//TBrick ��	
			break;
		default:
			returPaint = Color.WHITE;
			break;
		}
		
		return returPaint;
	}

	//������ʾʱ���浹Ӱ
	//ʹ��ָ���� URL �� ResourceBundle ��ʼ�� UIController��
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 // ������Ϸ���ɻ�ȡ���㣬�Ա���ռ����¼�
		gamePanel.setFocusTraversable(true);
		 // ���󽹵�
		gamePanel.requestFocus();
		// ������Ϸ���ļ����¼�����
		gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				//��Ϸ����ʱ�ſ���ʹ���ƶ�ת�����
				System.out.println(paused.getValue());
				if (paused.getValue() == Boolean.FALSE && GameOver.getValue() == Boolean.FALSE) {
			        // ���û����¼�����R��ʱ��ִ����Ϸ������������
			        if (event.getCode() == KeyCode.R) {
			            restartGame();
			            event.consume();
			        }					
					//���û����¼�����W����SPACE��ʱ��ִ�з���ת�����
					if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.W) {
						refreshBrick(eventListener.onRotateEvent());
						event.consume();
					}
					//���û����¼�����S����DOWN��ʱ��ִ�з����������
					if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
						moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
						event.consume();	
					}
					//���û����¼�����A����LEFT��ʱ��ִ�з������Ʋ���
					if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
						refreshBrick(eventListener.onLeftEvent());
						event.consume();
					}
					//���û����¼�����D����RIGHT��ʱ��ִ�з������Ʋ���
					if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
						refreshBrick(eventListener.onRightEvent());
						event.consume();
					}
				}			
				//���û����¼�����P��,��Ϸ��ͣ
				if (event.getCode() == KeyCode.P) {
					pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
				}
			}
		});
		// ��Ϸ�������Ĭ�ϲ��ɼ�
		gameOverPanel.setVisible(false);
		
		//��Ϸ��ͣ
		pauseButton.selectedProperty().bindBidirectional(paused);
		pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				 // ������Ϸ���ɻ�ȡ���㣬�Ա���ռ����¼�
				gamePanel.setFocusTraversable(true);
				 // ���󽹵�
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
				
		//Ϊ ScoreValue �ı���������Ч���� 
		Reflection reflection = new Reflection();
		//���÷���Ч��������
		reflection.setFraction(0.8);
		reflection.setTopOpacity(0.9);
		reflection.setTopOffset(-12);
		//������Ч��Ӧ�õ� ScoreValue �ı�
		scoreValue.setEffect(reflection);
		
	}
	
	// ��� restartGame ����
	@FXML
	private void restartGame() {
		 // ������Ϸ���ɻ�ȡ���㣬�Ա���ռ����¼�
		gamePanel.setFocusTraversable(true);
		 // ���󽹵�
		gamePanel.requestFocus();
	    // ֹͣԭ�е�ʱ���ᶯ��
	    timeLine.stop();

	    // ���³�ʼ����Ϸ
	    gamePanel.getChildren().clear();
	    nextBrick.getChildren().clear();
	    brickPanel.getChildren().clear();
	    groupNotice.getChildren().clear();
	    gameOverPanel.setVisible(false);

	    // �����µ���Ϸ����������ʼ����Ϸ
	    new GameController(this);

	    // �����µ�ʱ���ᶯ��
	    timeLine.play();
	}
	
	//������Ϸ������
	public void gameOver() {
		// ֹͣʱ���ᶯ��
		timeLine.stop();
		// ��ʾ��Ϸ�������
		gameOverPanel.setVisible(true);
		GameOver.setValue(Boolean.TRUE);
	}
}
