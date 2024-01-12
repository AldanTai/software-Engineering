package LBNgames;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.*;

import Image.SaveImage;
import LoginSystem.mySQLSaveUsers;

public class personalFrame extends MyFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField getself;
	private JTextField getemail;
	
	private JTextField getteamname;
	private JTextField getteamself;
	
	private JPanel panel = new JPanel();
	private CardLayout cardLayout;
    private JPanel cardPanel;
    private int changeframe=1;
    private int times=0;
    
    private String titleName="個人資料";
    
    //同一頁面切換數字
    private int personalchange=1;
    private int Matchchange=1;
    
    
    //玩家個資
    private String ID;
    private String photo;
    private String email;
    private String team;

    private String formyself;
    private int win;
    private int lose;
    
    //隊伍檔案
    private String teamname="";
    private String teamphoto;
    private int teammax; 
    private String teamself="";
    
    
    //沒意義的物件
    private String[] URIarr= {
    		"https://www.youtube.com/watch?v=nDUQ-MvnO6s",
    		"https://www.youtube.com/watch?v=dQw4w9WgXcQ",
    		"https://www.youtube.com/watch?v=E8gmARGvPlI",
    		"https://www.youtube.com/watch?v=yXQViqx6GMY",
    		"https://www.youtube.com/watch?v=5KK1ec2rdKY",
    		"https://www.youtube.com/watch?v=_VCAeYVYmyI",
    		"https://www.youtube.com/watch?v=PrE5YuraYNg",
    		}; 
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					personalFrame frame = new personalFrame();
					MyFrame mf= new MyFrame();
					frame.setVisible(true);
					
					frame.addWindowListener(mf);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//功能執行欄位
	public personalFrame() {
		mySQLSaveUsers su = new mySQLSaveUsers();
  		if(su.checklogin()) {
			System.out.println("個人有找到");
			//有值
			ID=su.name;
			email=su.email;
			team = su.team;
			formyself= su.selfIntroduction;
			photo= su.photo;
			win= su.win;
			lose= su.lose;
			System.out.println("personal get name:"+ID);
			//finduser();
			}
		
		
		setTitle("LBNgames");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		cardLayout= new CardLayout();
		cardPanel=new JPanel(cardLayout);
		
		setshowframe();
	}

	
	private void setshowframe() {
		panel=createPage(changeframe);
		cardPanel.add(panel);
		if(times>0) {
			cardLayout.next(cardPanel);
			
		}
		else {
			getContentPane().add(cardPanel);
			times++;
		}
		
	}
	
	private JPanel createPage(int frameName) {
		JPanel panel = new JPanel();
		switch(frameName) {
		case 1:
			panel=Personal();
			break;
		case 2:
			panel =Matchframe();
			break;
		case 3:
			panel = teamframe();
			break;
		case 4:
			//panel = GameRecordframe(); 
			break;
		case 9:
			panel=logoutframe();
			break;
		}
		return panel;
	}

	//改變顯示頁面

	
	//下方為頁面

	private JPanel titleframe() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 766, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		// 建立視窗
		
		JLabel Title = new JLabel("LBN");
		Title.setBounds(10, 10, 95, 24);
		panel.add(Title);
		Title.setFont(new Font("Ink Free", Font.BOLD, 20));
		// 建立標籤LBN
		JLabel name = new JLabel(titleName);
		name.setFont(new Font("新細明體", Font.BOLD, 25));
		name.setBounds(190, 25, 137, 51);
		panel.add(name);
		
		JButton mainButton = new JButton("回首頁");
		mainButton.setFont(new Font("新細明體", Font.BOLD, 20));
		mainButton.setBounds(650, 10, 110, 35);
		panel.add(mainButton);
		
		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到首頁畫面
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});
		return panel;
	}
	
	//左邊的顯示圖案紐
	private JPanel mainbutton() {
		JPanel menu = new JPanel();
		menu.setBounds(45, 90, 148, 300);
		contentPane.add(menu);
		menu.setLayout(null);
		
		JButton imformationButton = new JButton("個人資料");
		imformationButton.setFont(new Font("標楷體", Font.BOLD, 20));
		imformationButton.setBounds(0, 0, 148, 60);
		menu.add(imformationButton);
		// 新增個人資料按鈕
		
		JButton matchButton = new JButton("個人賽事");
		matchButton.setFont(new Font("標楷體", Font.BOLD, 20));
		matchButton.setBounds(0, 60, 148, 60);
		menu.add(matchButton);
		// 新增個人賽事按鈕
		
		JButton teamButton = new JButton("隊伍資料");
		teamButton.setFont(new Font("標楷體", Font.BOLD, 20));
		teamButton.setBounds(0, 120, 148, 60);
		menu.add(teamButton);
		// 新增隊伍資料按鈕
		
		JButton adButton = new JButton("廣告商註冊");
		adButton.setFont(new Font("標楷體", Font.BOLD, 20));
		adButton.setBounds(0, 180, 148, 60);
		menu.add(adButton);
		// 新增廣告商註冊按鈕
		
		JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("標楷體", Font.BOLD, 20));
		logoutButton.setBounds(0, 240, 148, 60);
		menu.add(logoutButton);
		// 新增登出按鈕
		
		//執行個人按鈕
		imformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleName="個人資料";
				changeframe=1;
				setshowframe();
			}
		});
		
		//執行賽事按鈕
		matchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleName="個人賽事";
				changeframe=2;
				setshowframe();
			}
		});
		
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleName="隊伍資訊";
				changeframe=3;
				setshowframe();
			}
		});
		
		adButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adFrame ad = new adFrame();
				ad.setVisible(true);
				dispose();
			}
		});
		
		//執行登出按鈕
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登出畫面
				titleName="登出";
				changeframe=9;
				setshowframe();
			}
		});
		
		return menu;
	}
	
	//把personal的修改與基礎資料合併
	private JPanel Personal() {
		JPanel Allpanel = new JPanel();
		contentPane.add(Allpanel);
		Allpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(199, 90, 539, 360);
		panel.setBackground(new Color(192, 192, 192));
		Allpanel.add(panel);
		panel.setLayout(null);
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		
		
		Allpanel.add(titleframe());
		Allpanel.add(mainbutton());
		
		//使用者圖片
		JPanel avatar = new JPanel();
		panel.add(avatar);
		
		
		ImageIcon img = new ImageIcon(photo);
		avatar.add(new JLabel(img));
		avatar.setBounds(15, 15, 145, 145);
		avatar.setBorder(blackline);
		
		
		JPanel forpersonal = new JPanel();
		forpersonal.setBounds(190, 10, 310, 160);
		panel.add(forpersonal);
		forpersonal.setBorder(blackline);
		forpersonal.setLayout(null);
		
		
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		nameLabel.setBounds(5, 10, 300, 25);
		forpersonal.add(nameLabel);
		
		JLabel teamLabel = new JLabel("隊伍：");
		teamLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		teamLabel.setBounds(5, 65, 85, 25);
		forpersonal.add(teamLabel);
		
		JLabel mailLabel = new JLabel("email：");
		mailLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		mailLabel.setBounds(5, 125, 85, 25);
		forpersonal.add(mailLabel);
		
		JLabel lblNewLabel = new JLabel("個人介紹:");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setBounds(8, 195, 80, 20);
		panel.add(lblNewLabel);
		
		if(personalchange==1) {
			JLabel nameLabel2 = new JLabel(ID);
			nameLabel2.setFont(new Font("新細明體", Font.PLAIN, 18));
			nameLabel2.setBounds(55, 10, 85, 25);
			forpersonal.add(nameLabel2);
			
			JLabel teamLabel2 = new JLabel(team);
			teamLabel2.setFont(new Font("新細明體", Font.PLAIN, 18));
			teamLabel2.setBounds(55, 65, 85, 25);
			forpersonal.add(teamLabel2);
		
			JLabel mailLabel2 = new JLabel(email);
			mailLabel2.setFont(new Font("新細明體", Font.PLAIN, 18));
			mailLabel2.setBounds(55, 125, 85, 25);
			forpersonal.add(mailLabel2);
			
			JButton editButton = new JButton("編輯");
			editButton.setFont(new Font("新細明體", Font.BOLD, 20));
			editButton.setBounds(193, 314, 110, 39);
			panel.add(editButton);
		
		
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBorder(blackline);
			panel_2.setBounds(10, 216, 430, 84);
			panel_2.setLayout(null);
			panel.add(panel_2);
		
			JLabel myself_title = new JLabel(formyself);
			myself_title.setFont(new Font("新細明體", Font.PLAIN, 15));
			myself_title.setBounds(0, 0, 430, 84);
			panel_2.add(myself_title);
		
			//改成修改介面
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personalchange=2;
					setshowframe();
				}
			});	
		
		}
		else {
			
			JLabel nameLabel2 = new JLabel(ID);
			nameLabel2.setFont(new Font("新細明體", Font.PLAIN, 18));
			nameLabel2.setBounds(55, 10, 85, 25);
			forpersonal.add(nameLabel2);
			
			JLabel teamLabel2 = new JLabel(team);
			teamLabel2.setFont(new Font("新細明體", Font.PLAIN, 18));
			teamLabel2.setBounds(55, 65, 85, 25);
			forpersonal.add(teamLabel2);
			
			getemail = new JTextField(email);
			getemail.setColumns(10);
			getemail.setBounds(55, 125, 220, 20);
			forpersonal.add(getemail);
			
			JButton changeButton = new JButton("更換頭像");
			changeButton.setBounds(20, 163, 125, 30);
			panel.add(changeButton);
			
			
			JButton backButton = new JButton("回上頁");
			backButton.setFont(new Font("新細明體", Font.BOLD, 20));
			backButton.setBounds(150, 314, 110, 39);
			panel.add(backButton);
			
			JButton saveButton = new JButton("保存");
			saveButton.setFont(new Font("新細明體", Font.BOLD, 20));
			saveButton.setBounds(270, 314, 110, 39);
			panel.add(saveButton);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBorder(blackline);
			panel_2.setBounds(10, 216, 430, 84);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			getself = new JTextField(formyself);
			getself.setColumns(10);
			getself.setBounds(0, 0, 430, 84);
			panel_2.add(getself);
			
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personalchange=1;
					setshowframe();
				}
			});
			
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//做SaveUsers的Register
					if(!getemail.getText().equals(email) && getemail.getText() != null) {
						email=getemail.getText();
					}
					if(!getself.getText().equals(formyself) && getself.getText() != null) {
						formyself=getself.getText();
					}
					
					if(!photo.equals("")){
						SaveImage SI = new SaveImage();
						SI.ad(photo, ID, 0);
						}
					
					mySQLSaveUsers SU= new mySQLSaveUsers();
					SU.changeself(ID, email, team, photo, formyself);
					
					personalchange=1;
					setshowframe();
				}
			});
			
			//改變
			changeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					adFrame ad =new adFrame();
					photo=ad.uploadImage("Image/user/",ID);
					setshowframe();
				}
			});
		}

		
		
		return Allpanel;
	}

	//個人賽事界面
	private JPanel Matchframe() {
		JPanel Allpanel = new JPanel();
		contentPane.add(Allpanel);
		Allpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(199, 90, 539, 363);
		panel.setBackground(new Color(192, 192, 192));
		Allpanel.add(panel);
		panel.setLayout(null);
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		
		
		Allpanel.add(titleframe());
		Allpanel.add(mainbutton());
		
		//遊戲名稱
		JLabel gameLabel = new JLabel("遊戲名稱：LOL");
		gameLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		gameLabel.setBounds(15, 10, 300, 25);
		panel.add(gameLabel);
		
		//遊戲畫面
		JPanel avatar = new JPanel();
		panel.add(avatar);
		
		ImageIcon img = new ImageIcon("Image/game/LOL.jpg");
		avatar.add(new JLabel(img));
		avatar.setBounds(15, 45, 145, 145);
		avatar.setBorder(blackline);
		
		JPanel forpersonal = new JPanel();
		forpersonal.setBounds(190, 10, 310, 160);
		panel.add(forpersonal);
		forpersonal.setBorder(blackline);
		forpersonal.setLayout(null);
		
		
		JLabel nameLabel = new JLabel("ID："+ID);
		nameLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		nameLabel.setBounds(5, 10, 300, 25);
		forpersonal.add(nameLabel);
		
		JLabel teamLabel = new JLabel("WIN："+win);
		teamLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		teamLabel.setBounds(5, 65, 85, 25);
		forpersonal.add(teamLabel);
		
		JLabel mailLabel = new JLabel("LOSE："+lose);
		mailLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		mailLabel.setBounds(5, 125, 85, 25);
		forpersonal.add(mailLabel);
		
		return Allpanel;
	}
	
	//團隊按鈕
	private JPanel teamframe() {
		teamname=team;
		
		JPanel Allpanel = new JPanel();
		contentPane.add(Allpanel);
		Allpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 90, 539, 360);
		panel.setBackground(new Color(192, 192, 192));
		Allpanel.add(panel);
		panel.setLayout(null);
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		
		//一定要加
		Allpanel.add(titleframe());
		Allpanel.add(mainbutton());
		
		
		JButton personButton = new JButton("個人");
		personButton.setBounds(0, 0, 60, 35);
		panel.add(personButton);
		
		JButton groupButton = new JButton("團體");
		groupButton.setBounds(57, 0, 60, 35);
		panel.add(groupButton);
		
		JPanel game = new JPanel();
		game.setBorder(blackline);
		game.setBounds(0, 35, 539, 325);
		panel.add(game);
		game.setLayout(null);
		
		if(team.isEmpty() && Matchchange==1) {
			JLabel lblNewLabel = new JLabel("您尚未加入隊伍喔，是否加入或創建？");
			lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 15));
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setBounds(135, 110, 265, 28);
			game.add(lblNewLabel);
			
			JButton buildteamButton = new JButton("創建");
			buildteamButton.setBounds(155, 180, 60, 35);
			game.add(buildteamButton);
			
			JButton addteamButton = new JButton("加入");
			addteamButton.setBounds(295, 180, 60, 35);
			game.add(addteamButton);
			
			buildteamButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Matchchange=3;
					setshowframe();
				}
			});
				
			addteamButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Matchchange=2;
					setshowframe();
				}
			});
		}else if(Matchchange==2) {
			JPanel newteam = new JPanel();
			game.add(newteam);
			newteam.setBounds(0, 0, 539, 40);
			newteam.setBorder(blackline);
			newteam.setLayout(null);
			
			JLabel teamclass1 = new JLabel(teamname+"戰隊");
			teamclass1.setFont(new Font("新細明體", Font.BOLD, 20));
			teamclass1.setBounds(10, 0, 150, 40);
			
			newteam.add(teamclass1);
			
			JLabel teamuser = new JLabel("人數：1/50");
			teamuser.setFont(new Font("新細明體", Font.BOLD, 20));
			teamuser.setBounds(200, 0, 200, 40);
			
			newteam.add(teamuser);
			
			JButton seeteamButton = new JButton("查看");
			seeteamButton.setBounds(479, 0, 60, 40);
			game.add(seeteamButton);
			
			newteam.add(seeteamButton);
			
			seeteamButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Matchchange=1;
					setshowframe();
				}
			});
		}
		
		
		else {
			//顯示隊伍名稱與內容
			
			//創建隊伍
			//隊伍圖片
			JPanel avatar = new JPanel();
			game.add(avatar);
			
			ImageIcon img = new ImageIcon("Image/team/team.gif");
			avatar.add(new JLabel(img));
			avatar.setBounds(5, 15, 145, 145);
			avatar.setBorder(blackline);
			
			JPanel forpersonal = new JPanel();
			forpersonal.setBounds(190, 10, 310, 160);
			game.add(forpersonal);
			forpersonal.setBorder(blackline);
			forpersonal.setLayout(null);
			
			
			JLabel nameLabel = new JLabel("隊伍名稱："+teamname);
			nameLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
			nameLabel.setBounds(5, 10, 300, 25);
			forpersonal.add(nameLabel);
			
			JLabel teamLabel = new JLabel("隊伍ID：");
			teamLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
			teamLabel.setBounds(5, 65, 85, 25);
			forpersonal.add(teamLabel);
			
			JLabel mailLabel = new JLabel("隊伍人數：1/50");
			mailLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
			mailLabel.setBounds(5, 125, 250, 25);
			forpersonal.add(mailLabel);
			
			JLabel lblNewLabel = new JLabel("隊伍介紹:");
			lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
			lblNewLabel.setBounds(8, 195, 80, 20);
			game.add(lblNewLabel);
			
			JLabel myself_title = new JLabel(teamself);
			myself_title.setFont(new Font("新細明體", Font.PLAIN, 15));
			myself_title.setBounds(0, 0, 430, 84);
			lblNewLabel.add(myself_title);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBorder(blackline);
			panel_2.setBounds(10, 216, 430, 84);
			panel_2.setLayout(null);
			game.add(panel_2);
		
			JLabel teamselftitle = new JLabel(teamself);
			teamselftitle.setFont(new Font("新細明體", Font.PLAIN, 15));
			teamselftitle.setBounds(0, 0, 430, 84);
			panel_2.add(teamselftitle);
			if(team != null && Matchchange==1 ) {
				JButton outteamButton = new JButton("退出");
				outteamButton.setBounds(455, 240, 60, 35);
				game.add(outteamButton);
				
				outteamButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						team= "";
						
						mySQLSaveUsers SU= new mySQLSaveUsers();
						SU.changeself(ID, email, team, photo, formyself);
						
						Matchchange=1;
						setshowframe();
					}
				});
				
			}
			
			if(Matchchange==3) {
				
				//團隊加入名稱
				getteamname= new JTextField(teamname);
				getteamname.setColumns(10);
				getteamname.setBounds(100, 10, 200, 20);
				forpersonal.add(getteamname);
				
				//隊伍自界
				getteamself = new JTextField(teamself);
				getteamself.setColumns(10);
				getteamself.setBounds(0, 0, 430, 84);
				panel_2.add(getteamself);
				
				//頭像更換
				JButton changeButton = new JButton("更換頭像");
				changeButton.setBounds(20, 163, 125, 30);
				game.add(changeButton);
				
				JButton buildteamButton = new JButton("創建");
				buildteamButton.setBounds(455, 240, 60, 35);
				game.add(buildteamButton);
				
				//改變
				changeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						adFrame ad =new adFrame();
						teamphoto=ad.uploadImage("Image/team/",ID);
						setshowframe();
					}
				});
				
				//改變
				buildteamButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teamname=getteamname.getText();
						team=teamname;
						teamself = getteamself.getText();
						if(teamphoto != null){
							SaveImage SI = new SaveImage();
							SI.ad(teamphoto, ID, 2);
							}
						System.out.println(ID);
						mySQLSaveUsers SU= new mySQLSaveUsers();
						SU.changeself(ID, email, team, photo, formyself);
						
						Matchchange=1;
						setshowframe();
					}
				});
			}
		}
		//個人頁面
		personButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matchchange=1;
				setshowframe();
			}
		});
		
		//團對頁面
		groupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matchchange=2;
				setshowframe();
			}
		});
		
		return Allpanel;
	}
	
	//登出按件
	private JPanel logoutframe() {
		JPanel Allpanel = new JPanel();
		contentPane.add(Allpanel);
		Allpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(199, 90, 539, 363);
		panel.setBackground(new Color(192, 192, 192));
		Allpanel.add(panel);
		panel.setLayout(null);
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		
		
		Allpanel.add(titleframe());
		Allpanel.add(mainbutton());
		
		JLabel lblNewLabel = new JLabel("使用者："+ID);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel.setBounds(177, 30, 174, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("確定要登出嗎？");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(205, 110, 111, 23);
		panel.add(lblNewLabel_1);
		
		JButton noButton = new JButton("取消");
		noButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		noButton.setBounds(325, 240, 90, 40);
		panel.add(noButton);
		
		JButton yesButton = new JButton("確認");
		yesButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		yesButton.setBounds(115, 240, 90, 40);
		panel.add(yesButton);
		
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					  Desktop.getDesktop().browse(new URI(URIarr[(int)(Math.random()*100)%URIarr.length]));
					  Thread.sleep(5000);
					  JOptionPane.showMessageDialog(null, "音樂好聽嗎?");
					} catch (IOException | URISyntaxException e1) {
					  e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});	
		
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mySQLSaveUsers su = new mySQLSaveUsers();
				su.Logout(ID);
				System.out.println("ID為:"+ID);
				Interface_control ic = new Interface_control();
				ic.setVisible(true);
				dispose();
			}
		});	
		
		return Allpanel;
	}
	
	

}
