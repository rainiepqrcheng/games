package tetris;

import java.util.ArrayList;
import java.util.List;

//L�ζ���˹����ש��ӿڵ�ʵ�֡�
public class LBrick implements Brick{
	
	//�洢Lש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//LBrick���캯������ʼ��L��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
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
	//��ȡLש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ L ��ש����� 2D ���������б�
		return brickMatrix;
	}
}
