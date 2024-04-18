package tetris;

import java.util.ArrayList;
import java.util.List;

//O形俄罗斯方块砖块接口的实现。
public class OBrick implements Brick{
	
	//存储O砖不同旋转的列表
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//OBrick构造函数，初始化O形砖块的不同旋转并将它们添加到brickMatrix中。
	public OBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 1, 1, 0},
			{0, 1, 1, 0},
			{0, 0, 0, 0}
		});
	}
	//获取O砖的矩阵表示，重写 Brick 接口中定义的方法。
	public List<int[][]> getBrickMatrix() {
		//返回表示 O 形砖矩阵的 2D 整数数组列表。
		return brickMatrix;
	}
	
}
