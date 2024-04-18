package HomePage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;




//动画图标
//Download by http://www.codesc.net
public class adddhpicture extends JPanel implements ActionListener{

	ImageIcon[] images; //用于动画的图标数组
	Timer animationTimer; 
	int count = 0; //当前图像编号
	int width; //图像宽度
	int height; //图像高度
	int i=0;
	boolean f1=false;
	boolean f2=false;
	public adddhpicture() //构造函数
	{
		startAnimation();
		images = new ImageIcon[44]; //初始化数组
		for (int i=0;i<44;i++) {
			images[i]=new ImageIcon(getClass().getResource("/dh/image"+i+".png")); //实例化图标
		}
		width = images[0].getIconWidth(); //初始化宽度值
		height = images[0].getIconHeight(); //初始化高度值
		this.setLayout(null);
	}

	public void paintComponent(Graphics g) { //重载组件绘制方法
		super.paintComponent(g); //调用父类函数
		images[count].paintIcon(this,g,0,0); //绘制图标
	}

	public void actionPerformed(ActionEvent actionEvent) {
		repaint();
		count++;
		if(count==44) {
			count=0;
		}
	}

	public void startAnimation() { //开始动画
		if (animationTimer==null) {
			count=0; 
			animationTimer=new Timer(100, this);  //实例化Timer对象
			animationTimer.start(); //开始运行
		} else if (!animationTimer.isRunning()) //如果没有运行
			animationTimer.restart(); //重新运行
	}

	public void stopAnimation() { 
		animationTimer.stop();  //停止动画
	}
}