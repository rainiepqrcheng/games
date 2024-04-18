package tetris;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


//此类负责管理游戏逻辑以及UI和游戏板之间的交互。
public class GameController implements InputEventListener {
	
	//游戏版边框大小
	private SimpleBoard board = new SimpleBoard(26, 10);
	//用于处理 UI 更新的 UIController 实例
	private final UIController viewController;
	
		
	//GameController 类的构造函数。初始化 GameController 并创建一个新的游戏板。 
	//@param c 用于管理 UI 更新的 UIController 实例。
	public GameController(UIController c) {
		//设置用于通信的 UIController。
		this.viewController = c;
		//将 GameController 设置为 UI 事件的事件侦听器。
		this.viewController.setEventListener(this);
		
		//在游戏板上创建一个新的砖块
		board.createNewBrick();
		//使用具有初始棋盘状态的 UIController 初始化游戏视图
		this.viewController.initGameView(board.getBoardMatrix(), board.getViewData());
		//显示分数
		this.viewController.bindScore(board.getScore().scoreProperty());
	}
	
	//向下移动事件的事件处理程序方法。
	@Override
	public DownData onDowEvent(MoveEvent event) {
		//将游戏板上当前的砖块向下移动,到达边界后Background
		boolean canMove = board.moveBrickDown();
		ClearRow clearRow = null;
		if(!canMove) {
	        // 当砖块无法再向下移动时，执行以下操作：
			// 将当前砖块合并到游戏板的背景中
			board.mergeBrickToBackground();
			// 检查并清除可能存在的完整行
			clearRow = board.clearRows();

			// 如果有完整的行被清除，增加分数
			if(clearRow.getLineRemoved() > 0) {
				board.getScore().add(clearRow.getScoreBonus());
				// 增加 lines
		        board.getScore().addLines(clearRow.getLineRemoved()); 
		        // 更新 line 显示
		        viewController.updateLine(board.getScore().getLines()); 
				
			}
			
			// 创建新的砖块，并检查游戏是否结束
			if(board.createNewBrick()) {
				viewController.gameOver();
				showMsg("your score:"+board.getScore().scoreProperty().intValue()
						+" level:"+viewController.levelText.getText()
						);
			}
						
		}else {
			//根据方块下降计分，下降一行计分1
			if(event.getEventSource() == EventSource.USER
				//|| event.getEventSource() == EventSource.THREAD
				) {
				board.getScore().add(1);
			}
		}
		
		// 刷新游戏背景显示
		viewController.refreshGameBackground(board.getBoardMatrix());
		
		//返回更新后的ViewData以反映游戏状态的变化。
		return new DownData(clearRow, board.getViewData());
	}

	// 处理向左移动事件的方法
	@Override
	public ViewData onLeftEvent() {
		board.moveBrickLeft();
		return board.getViewData();
	}

	// 处理向右移动事件的方法
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
	// 处理旋转砖块事件的方法
	@Override
	public ViewData onRotateEvent() {
		board.rotateBrick();
		return board.getViewData();
	}
	
}
