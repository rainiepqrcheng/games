package tetris;

import java.util.ArrayList;
import java.util.List;

//J�ζ���˹����ש��ӿڵ�ʵ�֡�
public class JBrick implements Brick{
	
	//�洢Jש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//JBrick���캯������ʼ��J��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
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
	//��ȡJש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ J ��ש����� 2D ���������б�
		return brickMatrix;
	}
}
