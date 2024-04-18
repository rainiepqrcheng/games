package tetris;

//表示移动事件的类
public class MoveEvent {
	// 移动事件的类型，包括DOWN、LEFT、RIGHT、ROTATE
	private final EventType evemType;
	// 事件来源，包括 是用户触发 和线程触发
	private final EventSource eventSource;
	
	// 构造方法，用于初始化移动事件
	public MoveEvent(EventType evemType, EventSource eventSource) {
		this.evemType = evemType;
		this.eventSource = eventSource;
	}

	//获取移动事件的类型
	public EventType getEvemType() {
		return evemType;
	}

	//获取事件的来源
	public EventSource getEventSource() {
		return eventSource;
	}
	
	
	

}
