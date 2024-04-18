package tetris;

import java.util.ArrayList;
import java.util.List;

//L形俄罗斯方块砖块接口的实现。
public class LBrick implements Brick{
	
	//存储L砖不同旋转的列表
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//LBrick构造函数，初始化L形砖块的不同旋转并将它们添加到brickMatrix中。
	public LBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 4, 4, 4},
			{0, 4, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 4, 4, 0},
			{0, 0, 4, 0},
			{0, 0, 4, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 0, 4, 0},
			{4, 4, 4, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 4, 0, 0},
			{0, 4, 0, 0},
			{0, 4, 4, 0},
			{0, 0, 0, 0}
		});
	}
	//获取L砖的矩阵表示，重写 Brick 接口中定义的方法。
	public List<int[][]> getBrickMatrix() {
		//返回表示 L 形砖矩阵的 2D 整数数组列表。
		return brickMatrix;
	}
}
