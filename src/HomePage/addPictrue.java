package HomePage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class addPictrue extends JPanel{
	//背景
	//登陆
	Image image;
	int bjwidth;//窗体宽度
	int bjheight;//窗体长度
	
	//构造函数
	public addPictrue(File fl){
		try {
			this.image=ImageIO.read(fl);
		} catch (IOException e) {
			System.out.print("没有找到图片!");
		}
	}
	@Override	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		//获取窗体的长宽
		bjwidth=this.getWidth();
		bjheight=this.getHeight();
		
		
		//绘制图片
		g.drawImage(this.image, 0, 0, bjwidth, bjheight, null);//背景图
		 
	}
	
}