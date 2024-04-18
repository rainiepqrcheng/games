package tetris;

//��ʾ����в�������
public class ClearRow {

	private final int scoreBonus;
	// �����������
	private final int LineRemoved;
	// ��һ������״̬
	private final int [][] nextMatrix;

	
	// ���췽�������ڳ�ʼ������в���
	public ClearRow(int lineRemoved, int[][] nextMatrix, int scoreBonus) {
		
		this.LineRemoved = lineRemoved;
		this.nextMatrix = nextMatrix;
		this.scoreBonus = scoreBonus;
	}
	
	
	
	public int getScoreBonus() {
		return scoreBonus;
	}



	// ��ȡ�����������
	public int getLineRemoved() {
		return LineRemoved;
	}

	// ��ȡ��һ������״̬
	public int[][] getNextMatrix() {
		return nextMatrix;
	}
	
}
