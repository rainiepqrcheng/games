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

// 表示游戏通知面板的类
public class NoticePanel extends BorderPane {

    // 构造方法，用于初始化通知面板
    public NoticePanel(String text) {
    	// 设置最小高度
        setMinHeight(200);  
        // 设置最小宽度
        setMinWidth(220);  

        // 创建标签对象
        Label score = new Label(text);  

        score.getStyleClass().add("bouns-style");
        // 添加发光效果
        // 创建 Glow 对象，其构造方法参数是光晕的程度，0.4 表示光晕程度为 50%。
        Effect glow = new Glow(0.5);
        score.setEffect(glow);
        score.setTextFill(Color.WHITE);
        
        // 将标签设置为面板的中心内容
        setCenter(score);  
    }
    //游戏面板中间的分数提示特效
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
