package tetris;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

//������Ϸ�÷ֵ��ࡣ
public class Score {
	
	//����
	 private int lines; // ��� lines ��Ա���������ڴ洢����������
	//��ʾ�������������ԡ�
	private IntegerProperty score = new SimpleIntegerProperty(0);

	//��ȡ�������ԡ�
	public IntegerProperty scoreProperty() {
		return score;
	}
	
	//���ݲ�������÷�
	public void add(int i) {
		score.setValue(score.getValue() + i);
	}
	//��ȡline
    public int getLines() {
        return lines;
    }
    //line����
    public void addLines(int lines) {
        this.lines += lines;
    }
}
