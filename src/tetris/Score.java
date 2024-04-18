package tetris;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

//代表游戏得分的类。
public class Score {
	
	//新增
	 private int lines; // 添加 lines 成员变量，用于存储消除的行数
	//表示分数的整数属性。
	private IntegerProperty score = new SimpleIntegerProperty(0);

	//获取分数属性。
	public IntegerProperty scoreProperty() {
		return score;
	}
	
	//根据操作计算得分
	public void add(int i) {
		score.setValue(score.getValue() + i);
	}
	//获取line
    public int getLines() {
        return lines;
    }
    //line增加
    public void addLines(int lines) {
        this.lines += lines;
    }
}
