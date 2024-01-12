package LBNgames;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Image.showImage;
import LoginSystem.mySQLSaveUsers;
import music.Music;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;





public class Interface_control extends MyFrame{

	private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel contentPane;
    private JPanel panel = new JPanel();
    private int changeframe=1;
    private int times=0;
    private int songtimes=0;
    private int shopframe=0;
    
    private boolean iflogin=false;
    public String IDlabel="";
    private String Loginlabel="登入";
    private String folder= "Image/";
    private Music music = new Music();
	
    private Map attributes;
	private int width = 145;
	private int height = 145;
	
  
	private String[] choose = {"全部", "電競商品"};
  	
    
	public Interface_control() {
		
		//讀取玩家資料
		mySQLSaveUsers su = new mySQLSaveUsers();
  		if(su.checklogin()) {
  	    // 分别取出玩家數據
  			IDlabel =  "歡迎使用者: "+su.name;
  			//email= data.getemail();
  			//password = data.getPW();
  			//team= data.getteam();
  			//photo= data.getphoto();
  	    // 使用数据集
  	    // ...
  			System.out.println("主頁說有找到");
  			Loginlabel="個人";
  			iflogin=true;
  		}
		

		
		setTitle("LBNgames");
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		cardLayout= new CardLayout();
		cardPanel=new JPanel(cardLayout);
		setshowframe();
			
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//不做反應

		
		
	}
	

	 
	  //讀取玩家資料，讀取功能
  	
  	//讀取資料到此
	
	
	
	
	//設定畫面顯示
	private void setshowframe() {
		panel=createPage(changeframe);
		cardPanel.add(panel);
		if(times>0) {
			cardLayout.next(cardPanel);
			
		}
		else {
			add(cardPanel);
			times++;
		}
		
	}
	
	//改變顯示頁面
	private JPanel createPage(int frameName) {
		music.close();
		JPanel panel = new JPanel();
		switch(frameName) {
		case 1:
			panel=mainshow();
			break;
		case 2:
			panel = contestframe();
			break;
		case 3:
			panel = liveframe();
			music.open((float) 0.5);
			break;
		case 4:
			panel = GameRecordframe(); 
			break;
		case 5:
			panel=Shopframe();
			break;
		}
		return panel;
	}
	
	//標題顯示狀態欄位
	
	//標題與登入介面
 	private JPanel titleframe() {
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 790, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Title = new JLabel("LBN");
		Title.setFont(new Font("Ink Free", Font.BOLD, 20));
		Title.setBounds(10, 10, 95, 24);
		panel.add(Title);
		// 建立標籤LBN
		
		JLabel IDLabel = new JLabel(IDlabel);
		IDLabel.setBounds(575, 14, 102, 15);
		IDLabel.setText(IDlabel);
		panel.add(IDLabel);
		
		JButton LoginButton = new JButton(Loginlabel);
		LoginButton.setBounds(687, 6, 89, 28);
		LoginButton.setText(Loginlabel);
		panel.add(LoginButton);
		// 建立登入按鈕
		
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(iflogin) {
					personalFrame ps=new personalFrame();
					ps.setVisible(true);
					System.out.println("主頁畫面跳到登入畫面");
					dispose();//关闭当前登录页面
				}
				else {
					LoginFrame Loginframe=new LoginFrame();
					Loginframe.setVisible(true);
					System.out.println("主頁畫面跳到登入畫面");
					dispose();//关闭当前登录页面
				}
			}
		});
		return panel;
	}

 	//按鈕介面
	private JPanel Mainframe(){
		JPanel panel = new JPanel();
		panel.setBounds(20, 44, 734, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		// 建立首頁按鈕
		
		
		JButton MainFrameButton = new JButton("首頁");
		MainFrameButton.setBounds(0, 0, 148, 48);
		MainFrameButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		panel.add(MainFrameButton);
		

		JButton ContestButton = new JButton("賽事");
		ContestButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		ContestButton.setBounds(146, 0, 148, 48);
		panel.add(ContestButton);
		// 建立賽事按鈕
		
		JButton LiveButton = new JButton("直播");
		LiveButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		LiveButton.setBounds(293, 0, 148, 48);
		panel.add(LiveButton);
		// 建立直播按鈕
		
		JButton GameRecordButton = new JButton("戰況");
		GameRecordButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		GameRecordButton.setBounds(440, 0, 148, 48);
		panel.add(GameRecordButton);
		// 建立戰況按鈕
		
		JButton StoreButton = new JButton("商店");
		StoreButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		StoreButton.setBounds(586, 0, 148, 48);
		panel.add(StoreButton);
		
		//主頁面按鈕
		MainFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁面
				changeframe=1;
				setshowframe();
			}
		});
		
		//賽事頁面按鈕
		ContestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁面
				changeframe=2;
				setshowframe();
			}
		});
		
		//直播頁面按鈕
		LiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁面
				changeframe=3;
				setshowframe();
				
			}
		});
		
		GameRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁面
				changeframe=4;
				setshowframe();
			}
		});
		
		//商店頁面按鈕
		StoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到商店畫面
				changeframe=5;
				setshowframe();
			}
		});
		
		
		
		return panel;
	}
	
	//上面會重複使用
	
	
	
	//以下是不同頁面，點擊後分開使用
	
	//主頁介面
	private JPanel mainshow() {
		//這是main的需求
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleframe());//把title加進來
		panel.add(Mainframe());//把button加進來
		
		Panel Header = new Panel();
		
		showImage Image = new showImage();
		
		String titlefolder= folder+"titleframe/";
		
		Header.add(Image.showImage(titlefolder, 437, 217,5000));
		Header.setBounds(167, 127, 437, 217);
		panel.add(Header);
		// 預設建立為直播畫面區
				
		showImage adImage = new showImage(); 
		
		String adfolder= folder+"ad/";
		Panel Sponser = new Panel();
		Sponser.add(adImage.showImage(adfolder, 140, 329,3400));
		Sponser.setBounds(614, 124, 140, 329);
		panel.add(Sponser);
		// 預設建立為贊助商廣告區
			
		Panel Game = new Panel();
		Game.add(new JLabel());
		Game.setBounds(167, 364, 437, 89);
		panel.add(Game);
		
		JLabel label = new JLabel("2024_0205 LOL 亞洲區賽事報名");
		label.setFont(new Font("新細明體", Font.PLAIN, 20));
		label.setBounds(0, 0,270,20);
		// 設定字體為藍色
		label.setForeground(Color.BLUE);
		// 設定底線
		Font font = label.getFont();
		attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		//加入底線
		label.setFont(font.deriveFont(attributes));
		Game.add(label);
		
		JLabel label_1 = new JLabel("2024_0316 APEX 台灣區賽事報名");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		label_1.setBounds(0, 20,270,20);
		// 設定字體為藍色
		label_1.setForeground(Color.BLUE);
		// 設定底線
		label_1.setFont(font.deriveFont(attributes));
		Game.add(label_1);
		// 預設建立為遊戲相關賽事區
			
		JLabel label_2 = new JLabel("2024_0327 傳說對決 台灣高雄區賽事報名");
		label_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		label_2.setBounds(0, 40,270,20);
		// 設定字體為藍色
		label_2.setForeground(Color.BLUE);
		// 設定底線
		label_2.setFont(font.deriveFont(attributes));
		Game.add(label_2);
		
		return panel;
	}
	
	//賽事頁面
	private JPanel contestframe() {
		
		JPanel panel= new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleframe());
		panel.add(Mainframe());
		
		JPanel MarchList = new JPanel();
		MarchList.setBounds(25, 124, 140, 329);
		panel.add(MarchList);
		
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		
		// 建立遊戲列表的panel
		showImage adImage = new showImage(); 
		String adfolder= folder+"ad/";
		Panel Sponser = new Panel();
		Sponser.add(adImage.showImage(adfolder, 140, 329,3400));
		Sponser.setBounds(614, 124, 140, 329);
		panel.add(Sponser);
		// 建立贊助商廣告的panel

		JPanel lastestContest = new JPanel();
		lastestContest.setBounds(180, 160, 420, 30);
		lastestContest.setBorder(blackline);
		panel.add(lastestContest);
		
		JLabel label_2 = new JLabel("2024_0327 傳說對決 台灣高雄區賽事報名");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		label_2.setBounds(0, 40,270,20);
		// 設定字體為藍色
		label_2.setForeground(Color.BLUE);
		
		//底線設定
		Font font = label_2.getFont();
		attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label_2.setFont(font.deriveFont(attributes));
		lastestContest.add(label_2);
		
		
		// 建立最新的遊戲相關賽事的panel

		JPanel oldContest = new JPanel();
		oldContest.setBounds(180,  200, 420, 30);
		oldContest.setBorder(blackline);
		panel.add(oldContest);
		
		JLabel label_1 = new JLabel("2024_0316 APEX 台灣區賽事報名");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		label_1.setBounds(0, 20,270,20);
		// 設定字體為藍色
		label_1.setForeground(Color.BLUE);
		// 設定底線
		label_1.setFont(font.deriveFont(attributes));
		oldContest.add(label_1);
		// 建立舊的遊戲相關賽事的panel

		JTextField gameSearch = new JTextField();
		gameSearch.setBounds(237, 110, 160, 25);
		panel.add(gameSearch);
		// 建立遊戲搜索的文本框
		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(439, 110, 89, 25);
        panel.add(dateChooser1);
		// 建立時間的文本框

		JButton search = new JButton("搜索");
		search.setFont(new Font("新細明體", Font.PLAIN, 15));
		search.setBounds(534, 110, 70, 25);
		panel.add(search);
		
		JLabel datelabel = new JLabel("日期");
		datelabel.setBounds(410, 110, 33, 24);
		panel.add(datelabel);
		
		JLabel lblNewLabel = new JLabel("賽事名稱");
		lblNewLabel.setBounds(180, 110, 59, 24);
		panel.add(lblNewLabel);
		// 建立並設定搜索按鈕
		
		return panel;
	}
	
	//直播頁面(暫時棄用)
	private JPanel liveframe() {
		JPanel panel= new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleframe());
		panel.add(Mainframe());
		
		Panel Header = new Panel();
		JLabel title = new JLabel("目前沒有你所要的資源");
		title.setFont(new Font("新細明體", Font.PLAIN, 20));
		Header.add(title);
		
		Header.setBounds(167, 107, 437,30);
		panel.add(Header);
		
		JPanel avatar = new JPanel();
		panel.add(avatar);
		
		ImageIcon img = new ImageIcon("C:/school/eclipse/LBN/Image/live/chimuelo-toothless.gif");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setPreferredSize(new Dimension(300, 300));
		avatar.add(imgLabel);
		
		avatar.setBounds(250, 147, 300,300);
		
		JButton volume = new JButton("切換音量");
		volume.setFont(new Font("新細明體", Font.PLAIN, 15));
		volume.setBounds(630, 180, 100, 25);
		panel.add(volume);
		volume.setLayout(null);
		
		volume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//切換音量
				if(songtimes%2==0) {
					music.close();
					songtimes++;
				}
				else {
					music.open((float) 0.5);
					
				}
			}
		});
		//music.open((float) 0.5);
		
		
		try {
			Desktop.getDesktop().browse(new URI("https://www.twitch.tv/lolpacifictw"));
			
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return panel;
	}

	//遊戲專題專區
	private JPanel GameRecordframe() {
		JPanel panel= new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleframe());
		panel.add(Mainframe());
		
		//註記之後要做成新戰隊要加入時新增新框架
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 121, 107, 23);
		comboBox.addItem("全部");
		comboBox.addItem("LOL");

		JPanel SponsoredAds = new JPanel();
		SponsoredAds.setBounds(614, 124, 140, 329);
		panel.add(SponsoredAds);
		SponsoredAds.setLayout(null);
		// 建立贊助商廣告的panel

		JPanel TeamGameRecordTable = new JPanel();
		TeamGameRecordTable.setBounds(180, 200, 400, 250);
		panel.add(TeamGameRecordTable);
		TeamGameRecordTable.setLayout(null);
		// 建立隊伍遊戲戰績表的的panel

		JTextField teamSearch = new JTextField();
		teamSearch.setBounds(220, 124, 250, 24);
		panel.add(teamSearch);
		// 建立隊伍搜索的文本框

		JButton search = new JButton("搜索");
		search.setFont(new Font("新細明體", Font.PLAIN, 15));
		search.setBounds(483, 124, 73, 25);
		panel.add(search);
		// 建立並設定搜索按鈕
		
		return panel;
	}
	
	//商店介面
	private JPanel Shopframe() {
		JPanel Allpanel = new JPanel();
		contentPane.add(Allpanel);
		Allpanel.setLayout(null);
		Allpanel.add(titleframe());//
		Allpanel.add(Mainframe());//
		
		JLabel lblNewLabel = new JLabel("贊助商名稱(OR)其商品");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel.setBounds(167, 120, 168, 24);
		Allpanel.add(lblNewLabel);
		
		//註記之後要做成商家要加入時新增新框架
		JComboBox comboBox = new JComboBox<>(choose);
		comboBox.setBounds(20, 121, 107, 23);
		

		//使用方式應該都相同
		comboBox.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent event) {
		    // 处理选择事件
			  String getword = (String)comboBox.getSelectedItem();
			  
			  switch(getword) {
			  	case "全部":
			  		shopframe=0;
			  		setshowframe();
			  		break;
			  	case "電競商品":
			  		shopframe =1;
			  		setshowframe();
			  		break;
			  
			  }
			  
		  }  
		});

		Allpanel.add(comboBox);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(160, 140, 589, 300);
		panel.setBackground(new Color(192, 192, 192));
		Allpanel.add(panel);
		panel.setLayout(null);
		//做黑框
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(blackline);
		//顯示商店內容
		
		if(shopframe==0) {
			JPanel avatar = new JPanel();
			panel.add(avatar);
			
			
			ImageIcon icon = new ImageIcon("Image/shop/電競耳機(1280元).jpg");
			Image img = icon.getImage(); 
			Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			ImageIcon newIcon = new ImageIcon(newImg);
			avatar.add(new JLabel(newIcon));
			avatar.setBounds(10, 10, width, height);
			avatar.setBorder(blackline);
			
			JPanel avatar1 = new JPanel();
			panel.add(avatar1);
			
			ImageIcon icon1 = new ImageIcon("Image/shop/電競無線滑鼠1(999元).jpg");
			Image img1 = icon1.getImage(); 
			Image newImg1 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			ImageIcon newIcon1 = new ImageIcon(newImg1);
			avatar1.add(new JLabel(newIcon1));
			avatar1.setBounds(160, 10, width, height);
			avatar1.setBorder(blackline);
			
		}
		if(shopframe==1) {
			JPanel avatar = new JPanel();
			panel.add(avatar);
			
			
			ImageIcon icon = new ImageIcon("Image/shop/電競鍵盤2(2190元).jpg");
			Image img = icon.getImage(); 
			Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			ImageIcon newIcon = new ImageIcon(newImg);
			avatar.add(new JLabel(newIcon));
			avatar.setBounds(10, 10, width, height);
			avatar.setBorder(blackline);
			
			JPanel avatar1 = new JPanel();
			panel.add(avatar1);
			
			ImageIcon icon1 = new ImageIcon("Image/shop/電競螢幕(16900元).jpg");
			Image img1 = icon1.getImage(); 
			Image newImg1 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			ImageIcon newIcon1 = new ImageIcon(newImg1);
			avatar1.add(new JLabel(newIcon1));
			avatar1.setBounds(160, 10, width, height);
			avatar1.setBorder(blackline);
			
		}
		
		return Allpanel;
	}
	
	
	public static void main(String[] args) {
		Interface_control frame = new Interface_control();
		
		MyFrame myFrame = new MyFrame();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				frame.setVisible(true);

				frame.addWindowListener(myFrame);
	                
			}
	    });
		
	    
	}
	
}


