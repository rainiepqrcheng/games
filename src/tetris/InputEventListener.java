package tetris;



//���������¼��������ӿ�
public interface InputEventListener {
	// ���������ƶ��¼������ش�������Ϸ��ͼ����
	DownData onDowEvent(MoveEvent event);
	
	// ���������ƶ��¼������ش�������Ϸ��ͼ����
	ViewData onLeftEvent(); 
	
	// ���������ƶ��¼������ش�������Ϸ��ͼ����
	ViewData onRightEvent(); 
	
	//����ת���¼������ش�������Ϸ��ͼ����
	ViewData onRotateEvent();

}
