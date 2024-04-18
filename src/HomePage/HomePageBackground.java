package HomePage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HomePageBackground extends JPanel{
	Image bj;//背景
	//登陆

			
	int bjwidth;//窗体宽度
	int bjheight;//窗体长度
	
	//构造函数
	public HomePageBackground(){
		getImageArc();
	}
	
	//获取图片的地址
	public void getImageArc(){
		try {
			this.bj=ImageIO.read(new File("data/HomePage/left/bj.png"));//背景图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//画板
	@Override	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		//获取窗体的长宽
		bjwidth=this.getWidth();
		bjheight=this.getHeight();
		
		
		//绘制图片
		g.drawImage(this.bj, 0, 0, bjwidth, bjheight, null);//背景图
		 
	}
	
}
