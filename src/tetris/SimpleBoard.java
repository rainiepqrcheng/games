package tetris;

import java.awt.Point;

public class SimpleBoard {
	
	//��Ϸ��ߴ�
	private final int width;
	private final int height;
	//��ʾ��Ϸ�嵱ǰ״̬�ľ���
	private int[][] currentGameMatrix;
	//���ڴ������ש���������
	private final RandomBrick brickGenerator;
	//���ϵ�ǰ��ש��
	private Brick brick;
	//ש�鵱ǰ��״������,0��ʾ��ʼ״̬
	private int currentShape = 0;
	//����ש�鵱ǰλ�õ�ƫ�Ƴ̶�
	private Point currentOffset;
	//��ʾ����
	private Score score;
	
	//SimpleBoard ��Ĺ��캯����
	public SimpleBoard(int width, int height) {
		//ʹ��ָ���Ŀ�Ⱥ͸߶ȳ�ʼ����Ϸ�壬width Ϊ��Ϸ��Ŀ�ȡ�, height Ϊ��Ϸ��ĸ߶ȡ�
		this.width = width;
		this.height = height;
		currentGameMatrix = new int[width][height];
		
		//����һ���µ�ש����������
		brickGenerator = new RandomBrick();
		
		score = new Score();
			
	}
	
	//����һ���µ����ש�鲢�����������Ϸ���ϡ� 
	public boolean createNewBrick() {
		//��ʼ״̬
		currentShape = 0;
		Brick currentBrick = brickGenerator.getBrick();
		setBrick(currentBrick);
		//����Brick��ʼλ�ã�Ϊ��Ϸ������м�
		currentOffset = new Point(3, 0);
		//���ש��ɹ������������ϣ�����true, ���򷵻� false��	
		return MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), 
										   currentOffset.x, currentOffset.y);
	}
	
	//����Ϸ���ϵ�ǰ��ש�������ƶ�һ��
	public boolean moveBrickDown() {
		//ʹ�õ�ǰƫ��ֵ����һ���µ㡣
		Point p = new Point(currentOffset);
		//�����ƶ�һ��
		p.translate(0, 1);
		//Update the currentOffset with the new point.
		currentOffset = p;
		//�������Ϸ�����ĳ�ͻ��
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//���Ա߽�System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	
	//����Ϸ���ϵ�ǰ��ש�������ƶ�һ��
	public boolean moveBrickLeft() {
		//ʹ�õ�ǰƫ��ֵ����һ���µ㡣
		Point p = new Point(currentOffset);
		//�����ƶ�һ��
		p.translate(-1, 0);
		//�������Ϸ�����ĳ�ͻ��
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//���Ա߽� System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	
	// ����Ϸ���ϵ�ǰ��ש�������ƶ�һ��
	public boolean moveBrickRight() {
		//ʹ�õ�ǰƫ��ֵ����һ���µ㡣
		Point p = new Point(currentOffset);
		//�����ƶ�һ��
		p.translate(1, 0);
		//�������Ϸ�����ĳ�ͻ��
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
		if(conflict) {
			//���Ա߽�System.out.println("Out of bounds");
			return false;
		}else {
			currentOffset = p;
			return true;
		}
	}
	// ��ȡ��һ��ש�����״
	public NextBrickShape getNextShape() {
		int nextShape = currentShape;
		nextShape = ++nextShape % brick.getBrickMatrix().size();
		return new NextBrickShape(brick.getBrickMatrix().get(nextShape), nextShape);
	}
	
	//ש��ת��
	public boolean rotateBrick() {
		NextBrickShape nextShape = getNextShape();
		boolean conflict = MatrixOperations.intersects(currentGameMatrix, nextShape.getShape(), 
													   currentOffset.x, currentOffset.y);
		//�жϱ߿��ͻ
		if (conflict) {
			return false;
		}else {
			setCurrentShape(nextShape.getPosition());
			return true;
		}
		
	}
	
	// ���õ�ǰ��״
	public void setCurrentShape(int currentShape) {
		this.currentShape = currentShape;
	}

	//��ȡ��ʾש�鵱ǰ��״��λ�õ���ͼ���ݡ�
	public ViewData getViewData() {
		//ViewData ���󣬰����й�ש�鵱ǰ��״��λ�õ���Ϣ��
		return new ViewData(getCurrentShape(), 
							currentOffset.x, currentOffset.y,
							brickGenerator.getNextBrick().getBrickMatrix().get(0));
	}
	
	//��ȡ��ʾש�鵱ǰ��״�ľ��� 
	public int[][] getCurrentShape(){
		//����ש�鵱ǰ��״�ľ���
		return this.brick.getBrickMatrix().get(currentShape);
	}
	
	//������Ϸ���ϵ�ǰ��ש�顣 
	public void setBrick(Brick brick) {
		//brick����Ϊ��ǰש���ש�����
		this.brick = brick;
		currentOffset = new Point(3,0);
	}
	//��ȡ��ʾ��Ϸ�嵱ǰ״̬�ľ���
	public int[][] getBoardMatrix() {
		// ������Ϸ�嵱ǰ״̬�ľ���
		return currentGameMatrix;
	}
	// ��ȡ��ǰ��Ϸ��������
	public Score getScore() {
		return score;
	}
	// �ϲ�ש�鵽����
	public void mergeBrickToBackground() {
		currentGameMatrix = MatrixOperations.merge(currentGameMatrix, getCurrentShape(),
												   currentOffset.x, currentOffset.y);
	}

	// �������
	public ClearRow clearRows() {
		ClearRow clearRow = MatrixOperations.checkRemoveing(currentGameMatrix);
		currentGameMatrix = clearRow.getNextMatrix();
		
		return clearRow;
	}




	
	
}
