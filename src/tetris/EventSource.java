package tetris;

public enum EventSource {
	//表示事件的来源是用户。在某个上下文中，可能有一些事件是由用户触发或产生的。
	//表示事件的来源是线程。线程通常是在后台执行的一段代码，可能会触发某些事件。
	USER, THREAD

}
