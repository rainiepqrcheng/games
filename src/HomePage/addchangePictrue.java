package HomePage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class addchangePictrue extends JPanel{
	Image image1;
	Image image2;
	boolean f=false;
	//构造函数
	public addchangePictrue(File fl,File f2){
		MyMouseAdapter l=new MyMouseAdapter();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		try {
			image1=ImageIO.read(fl);
			image2=ImageIO.read(f2);
		} catch (IOException e) {
			System.out.print("没有找到图片!");
		}
	}
	@Override	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//获取窗体的长宽
		int width=this.getWidth();
		int height=this.getHeight();
		//绘制图片
		if(!f){
			g.drawImage(this.image1,0,0,width,height,null);
		}else{
			g.drawImage(this.image2,0,0,width,height,null);
		}
		 
	}
	public class MyMouseAdapter extends MouseAdapter{
		//进入
		@Override
		public void mouseEntered(MouseEvent e) {
			f=true;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			repaint();
		}
		//移除
		@Override
		public void mouseExited(MouseEvent e) {
			f=false;
			repaint();
		}
	}
}