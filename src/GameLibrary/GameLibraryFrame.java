package GameLibrary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import javax.swing.border.MatteBorder;

import HomePage.HomePageBackground;
import HomePage.addPictrue;
import HomePage.addchangePictrue;


import eatingsnake.App;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import tank.Main;

public class GameLibraryFrame {
	//窗体
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		JFrame MyFrame=new JFrame("Game Library");//页面定义
		HomePageBackground bj=new HomePageBackground();
		MatteBorder border = new MatteBorder(null);//设置无边框
		//左
		  addchangePictrue gamelibrary=new addchangePictrue(new File("data/GameLibrary/left/游戏库2.png"), new File("data/GameLibrary/left/游戏库2.png"));
		  addPictrue userimg=new addPictrue(new File("data/GameLibrary/left/用户.png"));
		  JLabel userlb=new JLabel();//用户昵称
		//右
		 //添加游戏
		   int x=280;
		   int y=130;
		   addchangePictrue game1=new addchangePictrue(new File("data/GameLibrary/addgame/snake.jpg"), new File("data/GameLibrary/addgame/snake.jpg"));
		   addchangePictrue game2=new addchangePictrue(new File("data/GameLibrary/addgame/tank.jpg"), new File("data/GameLibrary/addgame/tank.jpg"));
		   addchangePictrue game3=new addchangePictrue(new File("data/GameLibrary/addgame/tetris.jpg"), new File("data/GameLibrary/addgame/tetris.jpg"));
		   addchangePictrue game4=new addchangePictrue(new File("data/GameLibrary/addgame/lightfight.png"), new File("data/GameLibrary/addgame/lightfight.png"));
		   //输入框
		   JTextField selecttext=new JTextField();
		  //游戏玩家信息
		   String name;
		  //好友
		   addchangePictrue friendselectimg=new addchangePictrue(new File("data/friend/搜索标志1.png"), new File("data/friend/搜索标志2.png"));


	//sql语句
		   String sql;
    //构造器
	public GameLibraryFrame(String name) {
		this.name=name;
		setform();
		SetLeftControl();
		try {
			SetGameSql();
		} catch (SQLException e) {
			System.out.print("数据库连接失败!");
		}
	}
	public void setform(){
		//主背景绘制
		MyFrame.setSize(1774, 989);
		MyFrame.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		MyFrame.setResizable(false);
		MyFrame.setVisible(true);
		MyFrame.setLocation((screen.width-MyFrame.getWidth())/2, (screen.height-MyFrame.getHeight())/2-20);
		MyFrame.add(bj);
		bj.setLayout(null);
		bj.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setdownload(0,0);
			}
		});
	}
	public void SetLeftControl() {
		//添加主页
		bj.add(gamelibrary);
        gamelibrary.setBackground(null);
        gamelibrary.setOpaque(false);
        gamelibrary.setBounds(23, 110, 160, 91);
        gamelibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MyFrame.dispose();
				new GameLibraryFrame(name);
			}
		});

		//添加用户
		//图片
		 bj.add(userimg);
		 userimg.setBackground(null);
		 userimg.setOpaque(false);
		 userimg.setBounds(30, 860, 47, 50);
		 //文字
		bj.add(userlb);
		userlb.setText(name);
		userlb.setBounds(105, 865, 100, 43);
		userlb.setFont(new Font("黑体",Font.BOLD,25));
		userlb.setForeground(new Color(122,123,127));

	}

	    
	//下载列表
	public void setdownload(int x,int y) {
		try {
			SetGameSql();
		} catch (SQLException e1) {
			System.out.print("数据库连接失败!");
		}
	}



		 public void SetGameSql() throws SQLException{
			 x=280;
			 y=130;
			 String url = "jdbc:sqlite:data/game.db";
			 Connection conn = DriverManager.getConnection(url);
			 Statement st = conn.createStatement();
			 //遍历语句
			 sql="select * from ";
			 sql+=name;
			 ResultSet rs=st.executeQuery(sql);
			 while(rs.next()) {
				 String gameid=rs.getString("Gameid");
				 //贪吃蛇
				 if(gameid.equals("0")) {
					 System.out.print(1);
					 bj.add(game1);
					 game1.setBackground(null);
					 game1.setOpaque(false);
					 game1.setBounds(x, y, 360, 626);
					 x=x+350;
					 game1.addMouseListener(new MouseAdapter() {
						 @Override
						public void mousePressed(MouseEvent e) {


									 JFXPanel jfxPanel = new JFXPanel(); // JFXPanel 是桥接 Swing 和 JavaFX 的关键
									 Platform.runLater(() -> {
										 try {
											 new App().start(new Stage());
										 } catch (IOException e1) {
											 e1.printStackTrace();
										 }
									 });
							 MyFrame.add(jfxPanel);
							 MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 MyFrame.setVisible(true);

						 }
					});
				 }
				 //大鱼吃小鱼
				 if(gameid.equals("1")) {
					 System.out.print(2);
					 bj.add(game2);
					 game2.setBackground(null);
					 game2.setOpaque(false);
					 game2.setBounds(x, y, 360, 626);
					 x=x+350;
					 game2.addMouseListener(new MouseAdapter() {
						 @Override
						public void mousePressed(MouseEvent e) {

							 JFXPanel jfxPanel = new JFXPanel(); // JFXPanel 是桥接 Swing 和 JavaFX 的关键
							 Platform.runLater(() -> {
								 try {
									 new Main().start(new Stage());
								 } catch (Exception e1) {
									 e1.printStackTrace();
								 }
							 });
							 MyFrame.add(jfxPanel);
							 MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 MyFrame.setVisible(true);
//							FirstFrame fish=new FirstFrame();
//							fish.setDefaultCloseOperation(fish.HIDE_ON_CLOSE);
						}
					});
				 }
				 //扫雷
				 if(gameid.equals("2")) {
					 System.out.print(3);
					 bj.add(game3);
					 game3.setBackground(null);
					 game3.setOpaque(false);
					 game3.setBounds(x, y, 360, 626);
					 x=x+350;
					 game3.addMouseListener(new MouseAdapter() {
						 @Override
						public void mousePressed(MouseEvent e) {


							 JFXPanel jfxPanel = new JFXPanel(); // JFXPanel 是桥接 Swing 和 JavaFX 的关键
							 Platform.runLater(() -> {
								 try {
									 new tetris.Main().start(new Stage());
								 } catch (Exception e1) {
									 e1.printStackTrace();
								 }
							 });
							 MyFrame.add(jfxPanel);
							 MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 MyFrame.setVisible(true);
						 }
					});
				 }
				 //飞机大战
				 if(gameid.equals("3")) {
					 System.out.print(4);
					 bj.add(game4);
					 game4.setBackground(null);
					 game4.setOpaque(false);
					 game4.setBounds(x, y, 360, 626);
					 x=x+350;
					 game4.addMouseListener(new MouseAdapter() {
						 @Override
						public void mousePressed(MouseEvent e) {
							 JFXPanel jfxPanel = new JFXPanel(); // JFXPanel 是桥接 Swing 和 JavaFX 的关键
							 Platform.runLater(() -> {
								 try {
									 Stage stage = new Stage();
									 new lightingfight.App().start(stage);
								 } catch (IOException e1) {
									 e1.printStackTrace();
								 }
							 });
							 MyFrame.add(jfxPanel);
							 MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 MyFrame.setVisible(true);
						 }
					});
				 }
			 }
			 
		 }
}
