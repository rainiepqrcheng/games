package tetris;

import java.util.ArrayList;
import java.util.List;

//S�ζ���˹����ש��ӿڵ�ʵ�֡�
public class SBrick implements Brick{
	
	//�洢Sש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//SBrick���캯������ʼ��S��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
	public SBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 5, 5, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0}
		});
		
		brickMatrix.add(new int[][] {
			{5, 0, 0, 0},
			{5, 5, 0, 0},
			{0, 5, 0, 0},
			{0, 0, 0, 0}
		});
	}
	//��ȡSש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ S ��ש����� 2D ���������б�
		return brickMatrix;
	}

}
