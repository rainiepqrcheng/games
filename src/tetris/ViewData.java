package tetris;

public class ViewData {
	//存储砖块当前形状的数据
	private final int[][] brickData;
	//游戏矩阵中砖块左上角的 x 坐标
	private final int xPostition;
	//游戏矩阵中砖块左上角的 y 坐标
	private final int yPostition;
	
	private final int[][] nextBrickData;
	
	//ViewData 类的构造函数。使用提供的砖块数据和位置坐标初始化 ViewData 对象。
	public ViewData(int[][] brickData, int xPostition, int yPostition, int[][] nextBrickData) {
		this.brickData = brickData;
		this.xPostition = xPostition;
		this.yPostition = yPostition;
		this.nextBrickData = nextBrickData;
	}
	//获取下一个砖块数据。
	public int[][] getNextBrickData() {
		return nextBrickData;
	}

	//获取砖块数据。
	public int[][] getBrickData() {
		//返回砖块当前形状的数据
		return brickData;
	}
	
	//获取砖块左上角的 x 坐标。
	public int getxPostition() {
		return xPostition;
	}

	//获取砖块左上角的 y 坐标。
	public int getyPostition() {
		return yPostition;
	}
	

}
