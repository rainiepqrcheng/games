package tetris;

import java.util.ArrayList;
import java.util.List;

//Z�ζ���˹����ש��ӿڵ�ʵ�֡�
public class ZBrick implements Brick{
	
	//�洢Zש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//ZBrick���캯������ʼ��Z��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
	public ZBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{6, 6, 0, 0},
			{0, 6, 6, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 6, 0, 0},
			{6, 6, 0, 0},
			{6, 0, 0, 0},
			{0, 0, 0, 0}
		});
	}
	//��ȡZש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ Z ��ש����� 2D ���������б�
		return brickMatrix;
	}

}
