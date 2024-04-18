package tetris;

//表示清除行操作的类
public class ClearRow {

	private final int scoreBonus;
	// 被清除的行数
	private final int LineRemoved;
	// 下一个矩阵状态
	private final int [][] nextMatrix;

	
	// 构造方法，用于初始化清除行操作
	public ClearRow(int lineRemoved, int[][] nextMatrix, int scoreBonus) {
		
		this.LineRemoved = lineRemoved;
		this.nextMatrix = nextMatrix;
		this.scoreBonus = scoreBonus;
	}
	
	
	
	public int getScoreBonus() {
		return scoreBonus;
	}



	// 获取被清除的行数
	public int getLineRemoved() {
		return LineRemoved;
	}

	// 获取下一个矩阵状态
	public int[][] getNextMatrix() {
		return nextMatrix;
	}
	
}
