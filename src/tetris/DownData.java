package tetris;

// 表示下落数据的类
public class DownData {

    private final ClearRow clearRow;  // 清除行数据
    private final ViewData viewData;  // 视图数据

    // 构造方法，用于初始化下落数据
    public DownData(ClearRow clearRow, ViewData viewData) {
        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    // 获取清除行数据
    public ClearRow getClearRow() {
        return clearRow;
    }

    // 获取视图数据
    public ViewData getViewData() {
        return viewData;
    }
}
