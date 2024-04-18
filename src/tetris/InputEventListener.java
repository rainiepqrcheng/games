package tetris;



//定义输入事件监听器接口
public interface InputEventListener {
	// 处理向下移动事件，返回处理后的游戏视图数据
	DownData onDowEvent(MoveEvent event);
	
	// 处理向左移动事件，返回处理后的游戏视图数据
	ViewData onLeftEvent(); 
	
	// 处理向右移动事件，返回处理后的游戏视图数据
	ViewData onRightEvent(); 
	
	//处理转向事件，返回处理后的游戏视图数据
	ViewData onRotateEvent();

}
