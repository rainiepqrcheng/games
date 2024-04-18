package tetris;

//��ʾ�ƶ��¼�����
public class MoveEvent {
	// �ƶ��¼������ͣ�����DOWN��LEFT��RIGHT��ROTATE
	private final EventType evemType;
	// �¼���Դ������ ���û����� ���̴߳���
	private final EventSource eventSource;
	
	// ���췽�������ڳ�ʼ���ƶ��¼�
	public MoveEvent(EventType evemType, EventSource eventSource) {
		this.evemType = evemType;
		this.eventSource = eventSource;
	}

	//��ȡ�ƶ��¼�������
	public EventType getEvemType() {
		return evemType;
	}

	//��ȡ�¼�����Դ
	public EventSource getEventSource() {
		return eventSource;
	}
	
	
	

}
