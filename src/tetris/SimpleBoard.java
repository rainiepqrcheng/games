package tetris;

import java.awt.Point;

public class SimpleBoard {
	
	//游戏板尺寸
	private final int width;
	private final int height;
	//表示游戏板当前状态的矩阵
	private int[][] currentGameMatrix;
	//用于创建随机砖块的生成器
	private final RandomBrick brickGenerator;
	//板上当前的砖块
	private Brick brick;
	//砖块当前形状的索引,0表示初始状态
	private int currentShape = 0;
	//跟踪砖块当前位置的偏移程度
	private Point currentOffset;
	//显示分数
	private Score score;
	
	//SimpleBoard 类的构造函数。
	public SimpleBoard(int width, int height) {
		//使用指定的宽度和高度初始化游戏板，width 为游戏板的宽度。, height 为游戏板的高度。
		this.width = width;
		this.height = height;
		currentGameMatrix = new int[width][height];
		
		//创建一个新的砖块生成器。
		brickGenerator = new RandomBrick();
		
		score = new Score();
			
	}
	
	//创建一个新的随机砖块并将其放置在游戏板上。 
	public boolean createNewBrick() {
		//初始状态
		currentShape = 0;
		Brick currentBrick = brickGenerator.getBrick();
		setBrick(currentBrick);
		//设置Brick初始位置，为游戏界面的中间
		currentOffset = new Point(3, 0);
		//如果砖块成功放置在棋盘上，返回true, 否则返回 false。	
		return MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), 
										   currentOffset.x, currentOffset.y);
	}
	
	//将游戏板上当前的砖块向下移动一步
	public boolean moveBrickDown() {
		//使用当前偏移值创建一个新点。
		Point p = new Point(currentOffset);
		//向下移动一点
		p.translate(0, 1);
		//Update the currentOffset with the new point.
		currentOffset = p;
		//检查与游戏板矩阵的冲突。
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//测试边界System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	
	//将游戏板上当前的砖块向左移动一步
	public boolean moveBrickLeft() {
		//使用当前偏移值创建一个新点。
		Point p = new Point(currentOffset);
		//向左移动一点
		p.translate(-1, 0);
		//检查与游戏板矩阵的冲突。
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//测试边界 System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	
	// 将游戏板上当前的砖块向右移动一步
	public boolean moveBrickRight() {
		//使用当前偏移值创建一个新点。
		Point p = new Point(currentOffset);
		//向左移动一点
		p.translate(1, 0);
		//检查与游戏板矩阵的冲突。
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//测试边界System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	// 获取下一个砖块的形状
	public NextBrickShape getNextShape() {
		int nextShape = currentShape;
		nextShape = ++nextShape % brick.getBrickMatrix().size();
		return new NextBrickShape(brick.getBrickMatrix().get(nextShape), nextShape);
	}
	
	//砖块转向
	public boolean rotateBrick() {
		NextBrickShape nextShape = getNextShape();
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, nextShape.getShape(), 
													   currentOffset.x, currentOffset.y);
		//判断边框冲突
		if (conflict) {
			return false;
		}else {
			setCurrentShape(nextShape.getPosition());
			return true;
		}
		
	}
	
	// 设置当前形状
	public void setCurrentShape(int currentShape) {
		this.currentShape = currentShape;
	}

	//获取表示砖块当前形状和位置的视图数据。
	public ViewData getViewData() {
		//ViewData 对象，包含有关砖块当前形状和位置的信息。
		return new ViewData(getCurrentShape(), 
							currentOffset.x, currentOffset.y,
							brickGenerator.getNextBrick().getBrickMatrix().get(0));
	}
	
	//获取表示砖块当前形状的矩阵。 
	public int[][] getCurrentShape(){
		//返回砖块当前形状的矩阵。
		return this.brick.getBrickMatrix().get(currentShape);
	}
	
	//设置游戏板上当前的砖块。 
	public void setBrick(Brick brick) {
		//brick设置为当前砖块的砖块对象。
		this.brick = brick;
		currentOffset = new Point(3,0);
	}
	//获取表示游戏板当前状态的矩阵。
	public int[][] getBoardMatrix() {
		// 返回游戏板当前状态的矩阵
		return currentGameMatrix;
	}
	// 获取当前游戏分数对象
	public Score getScore() {
		return score;
	}
	// 合并砖块到背景
	public void mergeBrickToBackground() {
		currentGameMatrix = MatrixOperations.merge(currentGameMatrix, getCurrentShape(),
												   currentOffset.x, currentOffset.y);
	}

	// 清除满行
	public ClearRow clearRows() {
		ClearRow clearRow = MatrixOperations.checkRemoveing(currentGameMatrix);
		currentGameMatrix = clearRow.getNextMatrix();
		
		return clearRow;
	}




	
	
}
