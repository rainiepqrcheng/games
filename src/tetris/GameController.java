package tetris;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


//���ฺ�������Ϸ�߼��Լ�UI����Ϸ��֮��Ľ�����
public class GameController implements InputEventListener {
	
	//��Ϸ��߿��С
	private SimpleBoard board = new SimpleBoard(26, 10);
	//���ڴ��� UI ���µ� UIController ʵ��
	private final UIController viewController;
	
		
	//GameController ��Ĺ��캯������ʼ�� GameController ������һ���µ���Ϸ�塣 
	//@param c ���ڹ��� UI ���µ� UIController ʵ����
	public GameController(UIController c) {
		//��������ͨ�ŵ� UIController��
		this.viewController = c;
		//�� GameController ����Ϊ UI �¼����¼���������
		this.viewController.setEventListener(this);
		
		//����Ϸ���ϴ���һ���µ�ש��
		board.createNewBrick();
		//ʹ�þ��г�ʼ����״̬�� UIController ��ʼ����Ϸ��ͼ
		this.viewController.initGameView(board.getBoardMatrix(), board.getViewData());
		//��ʾ����
		this.viewController.bindScore(board.getScore().scoreProperty());
	}
	
	//�����ƶ��¼����¼�������򷽷���
	@Override
	public DownData onDowEvent(MoveEvent event) {
		//����Ϸ���ϵ�ǰ��ש�������ƶ�,����߽��Background
		boolean canMove = board.moveBrickDown();
		ClearRow clearRow = null;
		if(!canMove) {
	        // ��ש���޷��������ƶ�ʱ��ִ�����²�����
			// ����ǰש��ϲ�����Ϸ��ı�����
			board.mergeBrickToBackground();
			// ��鲢������ܴ��ڵ�������
			clearRow = board.clearRows();

			// ������������б���������ӷ���
			if(clearRow.getLineRemoved() > 0) {
				board.getScore().add(clearRow.getScoreBonus());
				// ���� lines
		        board.getScore().addLines(clearRow.getLineRemoved()); 
		        // ���� line ��ʾ
		        viewController.updateLine(board.getScore().getLines()); 
				
			}
			
			// �����µ�ש�飬�������Ϸ�Ƿ����
			if(board.createNewBrick()) {
				viewController.gameOver();
				showMsg("your score:"+board.getScore().scoreProperty().intValue()
						+" level:"+viewController.levelText.getText()
						);
			}
						
		}else {
			//���ݷ����½��Ʒ֣��½�һ�мƷ�1
			if(event.getEventSource() == EventSource.USER
				//|| event.getEventSource() == EventSource.THREAD
				) {
				board.getScore().add(1);
			}
		}
		
		// ˢ����Ϸ������ʾ
		viewController.refreshGameBackground(board.getBoardMatrix());
		
		//���ظ��º��ViewData�Է�ӳ��Ϸ״̬�ı仯��
		return new DownData(clearRow, board.getViewData());
	}

	// ���������ƶ��¼��ķ���
	@Override
	public ViewData onLeftEvent() {
		board.moveBrickLeft();
		return board.getViewData();
	}

	// ���������ƶ��¼��ķ���
	@Override
	public ViewData onRightEvent() {
		board.moveBrickRight();
		return board.getViewData();
	}
	public void showMsg(String msg){
	   	 Alert alert = new Alert(AlertType.INFORMATION);
	        alert.titleProperty().set("");
	        alert.headerTextProperty().set(msg);
	        alert.showAndWait();
	   }
	// ������תש���¼��ķ���
	@Override
	public ViewData onRotateEvent() {
		board.rotateBrick();
		return board.getViewData();
	}
	
}
