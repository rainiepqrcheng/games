package tetris;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


//��ʾ��Ϸ����ʱ��ʾ�Ľ������
public class OverPanel extends BorderPane{
	// ���췽�������ڳ�ʼ����Ϸ��������
	public OverPanel() {
		
		Label gameOverLabel = new Label("GAME OVER");
		gameOverLabel.getStyleClass().add("game-over-style");
		
		// ����ǩ����Ϊ�������������
		setCenter(gameOverLabel);
	}
	
}
