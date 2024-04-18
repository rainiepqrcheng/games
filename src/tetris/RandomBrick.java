package tetris;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//�������Brick
public class RandomBrick {
	//�洢��ͬ����ש����б�
	private final List<Brick> brickList;
	//��˫�˶��д洢��һ��Ҫʹ�õ�ש��
	private final Deque<Brick> nextBrick = new ArrayDeque<>();
	
	//RandomBrick ��Ĺ��캯����
	public RandomBrick() {
		//�ò�ͬ���͵�ש���ʼ��brickList����Ӳ�ͬ���͵�ש�鵽brickList��
		brickList = new ArrayList<>();
		brickList.add(new IBrick());
		brickList.add(new JBrick());
		brickList.add(new LBrick());
		brickList.add(new OBrick());
		brickList.add(new TBrick());
		brickList.add(new SBrick());
		brickList.add(new ZBrick());
		
		//���������ש����� nextBrick ˫�˶���
		nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
		nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
		
	}
	
	public Brick getNextBrick() {
		return nextBrick.peek();
	}
	
	//��˫�˶����л�ȡ��һ��ש�� 
	public Brick getBrick() {
		//19
		if (nextBrick.size() <= 1) {
			nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	
		}
		//������Ϸ��Ҫʹ�õ���һ��ש�顣
		return nextBrick.poll();
		
	}
	
	
}
