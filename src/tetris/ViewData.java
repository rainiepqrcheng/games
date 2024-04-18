package tetris;

public class ViewData {
	//�洢ש�鵱ǰ��״������
	private final int[][] brickData;
	//��Ϸ������ש�����Ͻǵ� x ����
	private final int xPostition;
	//��Ϸ������ש�����Ͻǵ� y ����
	private final int yPostition;
	
	private final int[][] nextBrickData;
	
	//ViewData ��Ĺ��캯����ʹ���ṩ��ש�����ݺ�λ�������ʼ�� ViewData ����
	public ViewData(int[][] brickData, int xPostition, int yPostition, int[][] nextBrickData) {
		this.brickData = brickData;
		this.xPostition = xPostition;
		this.yPostition = yPostition;
		this.nextBrickData = nextBrickData;
	}
	//��ȡ��һ��ש�����ݡ�
	public int[][] getNextBrickData() {
		return nextBrickData;
	}

	//��ȡש�����ݡ�
	public int[][] getBrickData() {
		//����ש�鵱ǰ��״������
		return brickData;
	}
	
	//��ȡש�����Ͻǵ� x ���ꡣ
	public int getxPostition() {
		return xPostition;
	}

	//��ȡש�����Ͻǵ� y ���ꡣ
	public int getyPostition() {
		return yPostition;
	}
	

}
