package tetris;

import java.util.ArrayList;
import java.util.List;

//I�ζ���˹����ש��ӿڵ�ʵ�֡�
public class IBrick implements Brick{

	//�洢Iש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//IBrick���캯������ʼ��I��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
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
	//��ȡIש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ I ��ש����� 2D ���������б�
		return brickMatrix;
	}
	
	
}
