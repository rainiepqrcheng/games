package tetris;




import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TipUI extends AnchorPane {

	private String mainid;
	public String getMainid() {
		return mainid;
	}
	public void setMainid(String mainid) {
		this.mainid = mainid;
	}
	public TipUI(String param) {
		this.mainid=param;
		Label label_1837453=new Label(param);
		label_1837453.setFont(new Font("Arial", 14));
		label_1837453.setPrefWidth(Double.valueOf(200));
		label_1837453.setPrefHeight(Double.valueOf(23));
		this.setLeftAnchor(label_1837453, Double.valueOf(111));
		this.setTopAnchor(label_1837453, Double.valueOf(141));
		this.getChildren().add(label_1837453);
		Button btn_2259845=new Button("return");
		btn_2259845.setFont(new Font("Arial", 14));
		btn_2259845.setPrefWidth(Double.valueOf(93));
		btn_2259845.setPrefHeight(Double.valueOf(23));
		this.setLeftAnchor(btn_2259845, Double.valueOf(133));
		this.setTopAnchor(btn_2259845, Double.valueOf(291));
		btn_2259845.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ea) {
				((Stage) ((Button)ea.getSource()).getScene().getWindow()).close();
			}
		});
		this.getChildren().add(btn_2259845);
		
	}
 	public void showMsg(String msg){
   	 Alert alert = new Alert(AlertType.INFORMATION);
        alert.titleProperty().set("Tip");
        alert.headerTextProperty().set(msg);
        alert.showAndWait();
   }
}
