package HomePage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class speed{
	public speed(String text) {
		//窗体
		Toolkit toolkit=Toolkit.getDefaultToolkit();
 	    Dimension screen=toolkit.getScreenSize();
		JFrame c = new JFrame("加速器");
		c.setSize(300, 200);
		c.setDefaultCloseOperation(c.HIDE_ON_CLOSE);
		c.setVisible(true);
		c.setResizable(false);
		c.setLocation((screen.width-c.getWidth())/2,(screen.height-c.getHeight())/2);
		c.setLayout(null);
		//文字
		JLabel textlb=new JLabel(text);//"昵称不能为空！"
		c.add(textlb);
		textlb.setBounds(60,50, 240, 60);
		textlb.setFont(new Font("黑体",Font.PLAIN, 20));
		//图片
		Image image=null;
		try {
		    image=ImageIO.read(new File("./data/debug/加速器.png"));
		    } catch (IOException e) {
		        System.out.println("图片加载失败");
		    }
		 //设置图片
	    JLabel label=new JLabel(new ImageIcon(image));
	    c.add(label);
	    label.setBounds(5,55,59,45);  
	}

}
