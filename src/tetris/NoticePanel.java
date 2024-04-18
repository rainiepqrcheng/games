package tetris;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

// ��ʾ��Ϸ֪ͨ������
public class NoticePanel extends BorderPane {

    // ���췽�������ڳ�ʼ��֪ͨ���
    public NoticePanel(String text) {
    	// ������С�߶�
        setMinHeight(200);  
        // ������С���
        setMinWidth(220);  

        // ������ǩ����
        Label score = new Label(text);  

        score.getStyleClass().add("bouns-style");
        // ��ӷ���Ч��
        // ���� Glow �����乹�췽�������ǹ��εĳ̶ȣ�0.4 ��ʾ���γ̶�Ϊ 50%��
        Effect glow = new Glow(0.5);
        score.setEffect(glow);
        score.setTextFill(Color.WHITE);
        
        // ����ǩ����Ϊ������������
        setCenter(score);  
    }
    //��Ϸ����м�ķ�����ʾ��Ч
	public void showScore(ObservableList<Node> list) {
		FadeTransition ft = new FadeTransition(Duration.millis(2000), this);
		TranslateTransition tt = new TranslateTransition(Duration.millis(2500), this);
		tt.setToY(this.getLayoutY() - 40);
		ft.setFromValue(1);
		ft.setToValue(0);
		ParallelTransition transition = new ParallelTransition(tt, ft);
			transition.setOnFinished(new EventHandler<ActionEvent>() {	
				@Override
				public void handle(ActionEvent arg0) {
					list.remove(NoticePanel.this);
				}
			});
			transition.play();
		
	}
}
