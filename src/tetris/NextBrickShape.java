package tetris;

// 用于表示下一个砖块的形状和位置的类
public class NextBrickShape {
	// 砖块的形状矩阵
	private final int[][] shape;
	 // 砖块的位置
	private final int position;
	
	// 构造方法，用于初始化下一个砖块的形状和位置
	public NextBrickShape(int[][] shape, int position) {
		this.shape = shape;
		this.position = position;
	}

	//获取砖块的形状矩阵
	public int[][] getShape() {
		return shape;
	}
	//获取砖块的位置
	public int getPosition() {
		return position;
	}
	
	
}
