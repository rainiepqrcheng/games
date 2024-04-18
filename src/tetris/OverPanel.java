package tetris;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


//表示游戏结束时显示的界面的类
public class OverPanel extends BorderPane{
	// 构造方法，用于初始化游戏结束界面
	public OverPanel() {
		
		Label gameOverLabel = new Label("GAME OVER");
		gameOverLabel.getStyleClass().add("game-over-style");
		
		// 将标签设置为界面的中心内容
		setCenter(gameOverLabel);
	}
	
}
