package tetris;

import java.util.ArrayList;
import java.util.List;

//I形俄罗斯方块砖块接口的实现。
public class IBrick implements Brick{

	//存储I砖不同旋转的列表
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//IBrick构造函数，初始化I形砖块的不同旋转并将它们添加到brickMatrix中。
	public IBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{2, 2, 2, 2},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 2, 0, 0},
			{0, 2, 0, 0},
			{0, 2, 0, 0},
			{0, 2, 0, 0}
		});
	}
	//获取I砖的矩阵表示，重写 Brick 接口中定义的方法。
	public List<int[][]> getBrickMatrix() {
		//返回表示 I 形砖矩阵的 2D 整数数组列表。
		return brickMatrix;
	}
	
	
}
