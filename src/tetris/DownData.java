package tetris;

// ��ʾ�������ݵ���
public class DownData {

    private final ClearRow clearRow;  // ���������
    private final ViewData viewData;  // ��ͼ����

    // ���췽�������ڳ�ʼ����������
    public DownData(ClearRow clearRow, ViewData viewData) {
        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    // ��ȡ���������
    public ClearRow getClearRow() {
        return clearRow;
    }

    // ��ȡ��ͼ����
    public ViewData getViewData() {
        return viewData;
    }
}
