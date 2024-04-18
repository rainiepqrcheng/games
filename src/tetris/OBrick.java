package tetris;

import java.util.ArrayList;
import java.util.List;

//O�ζ���˹����ש��ӿڵ�ʵ�֡�
public class OBrick implements Brick{
	
	//�洢Oש��ͬ��ת���б�
	private final List<int[][]> brickMatrix =  new ArrayList<>();
	
	//OBrick���캯������ʼ��O��ש��Ĳ�ͬ��ת����������ӵ�brickMatrix�С�
	public OBrick() {
		brickMatrix.add(new int[][] {
			{0, 0, 0, 0},
			{0, 1, 1, 0},
			{0, 1, 1, 0},
			{0, 0, 0, 0}
		});
	}
	//��ȡOש�ľ����ʾ����д Brick �ӿ��ж���ķ�����
	public List<int[][]> getBrickMatrix() {
		//���ر�ʾ O ��ש����� 2D ���������б�
		return brickMatrix;
	}
	
}
