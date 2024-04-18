package tetris;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//随机生成Brick
public class RandomBrick {
	//存储不同类型砖块的列表
	private final List<Brick> brickList;
	//用双端队列存储下一个要使用的砖块
	private final Deque<Brick> nextBrick = new ArrayDeque<>();
	
	//RandomBrick 类的构造函数。
	public RandomBrick() {
		//用不同类型的砖块初始化brickList，添加不同类型的砖块到brickList中
		brickList = new ArrayList<>();
		brickList.add(new IBrick());
		brickList.add(new JBrick());
		brickList.add(new LBrick());
		brickList.add(new OBrick());
		brickList.add(new TBrick());
		brickList.add(new SBrick());
		brickList.add(new ZBrick());
		
		//用两个随机砖块填充 nextBrick 双端队列
		nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
		nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
		
	}
	
	public Brick getNextBrick() {
		return nextBrick.peek();
	}
	
	//从双端队列中获取下一块砖。 
	public Brick getBrick() {
		//19
		if (nextBrick.size() <= 1) {
			nextBrick.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));	
		}
		//返回游戏中要使用的下一个砖块。
		return nextBrick.poll();
		
	}
	
	
}
