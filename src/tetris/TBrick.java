package tetris;

import java.util.ArrayList;
import java.util.List;

//T�ζ���˹����ש��ӿڵ�ʵ�֡�
public class TBrick implements Brick{
	
	//�洢Tש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//SBrick���캯������ʼ��S��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
	public TBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{7, 7, 7, 0},
			{0, 7, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 7, 0, 0},
			{7, 7, 0, 0},
			{0, 7, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 7, 0, 0},
			{7, 7, 7, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{0, 7, 0, 0},
			{0, 7, 7, 0},
			{0, 7, 0, 0},
			{0, 0, 0, 0}
		});
	}
	//��ȡTש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ T ��ש����� 2D ���������б�
		return brickMatrix;
	}

}
