package tetris;

// ���ڱ�ʾ��һ��ש�����״��λ�õ���
public class NextBrickShape {
	// ש�����״����
	private final int[][] shape;
	 // ש���λ��
	private final int position;
	
	// ���췽�������ڳ�ʼ����һ��ש�����״��λ��
	public NextBrickShape(int[][] shape, int position) {
		this.shape = shape;
		this.position = position;
	}

	//��ȡש�����״����
	public int[][] getShape() {
		return shape;
	}
	//��ȡש���λ��
	public int getPosition() {
		return position;
	}
	
	
}
