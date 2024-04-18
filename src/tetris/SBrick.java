package tetris;

import java.util.ArrayList;
import java.util.List;

//S形俄罗斯方块砖块接口的实现。
public class SBrick implements Brick{
	
	//存储S砖不同旋转的列表
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//SBrick构造函数，初始化S形砖块的不同旋转并将它们添加到brickMatrix中。
	public SBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 5, 5, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{5, 0, 0, 0},
			{5, 5, 0, 0},
			{0, 5, 0, 0},
			{0, 0, 0, 0}
		});
	}
	//获取S砖的矩阵表示，重写 Brick 接口中定义的方法。
	public List<int[][]> getBrickMatrix() {
		//返回表示 S 形砖矩阵的 2D 整数数组列表。
		return brickMatrix;
	}

}
