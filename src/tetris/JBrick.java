package tetris;

import java.util.ArrayList;
import java.util.List;

//J形俄罗斯方块砖块接口的实现。
public class JBrick implements Brick{
	
	//存储J砖不同旋转的列表
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//JBrick构造函数，初始化J形砖块的不同旋转并将它们添加到brickMatrix中。
	public JBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{3, 3, 3, 0},
			{0, 0, 3, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 0, 3, 0},
			{0, 0, 3, 0},
			{0, 3, 3, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 3, 0, 0},
			{0, 3, 3, 3},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 3, 3, 0},
			{0, 3, 0, 0},
			{0, 3, 0, 0}
		});
	}
	//获取J砖的矩阵表示，重写 Brick 接口中定义的方法。
	public List<int[][]> getBrickMatrix() {
		//返回表示 J 形砖矩阵的 2D 整数数组列表。
		return brickMatrix;
	}
}
