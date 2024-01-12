package Personal;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class team extends JFrame {
	public static int a;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					team frame = new team();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	
	public team() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon img = new ImageIcon("C:\\Users\\l9999\\Downloads\\Heart_of_Iron_IV.jpg");
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 766, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		// 建立視窗
		
		JLabel Title = new JLabel("LBN");
		Title.setBounds(0, 0, 95, 24);
		panel_1.add(Title);
		Title.setFont(new Font("Ink Free", Font.BOLD, 20));
		// 建立標籤LBN
		
		
		JButton mainframeButton = new JButton("回首頁");
		mainframeButton.setFont(new Font("新細明體", Font.BOLD, 20));
		mainframeButton.setBounds(656, 0, 110, 39);
		panel_1.add(mainframeButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(256, 90, 470, 300);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton personButton = new JButton("個人");
		personButton.setBounds(0, 0, 59, 35);
		panel_2.add(personButton);
		
		JButton groupButton = new JButton("團體");
		groupButton.setBounds(57, 0, 59, 35);
		panel_2.add(groupButton);
		
		
		
		JButton btnNewButton = new JButton("更換隊徽");
		btnNewButton.setBounds(10, 166, 106, 23);
		panel_2.add(btnNewButton);
		
		Panel avatar = new Panel();
		avatar.setBounds(10, 50, 105, 110);
		panel_2.add(avatar);
		avatar.add(new JLabel(img));
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 217, 450, 73);
		panel_2.add(textPane);
		
		JLabel lblNewLabel = new JLabel("隊伍介紹");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel.setBounds(13, 191, 103, 23);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("隊伍名稱：");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(146, 50, 103, 23);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("隊伍ＩＤ：");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(146, 95, 103, 23);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("隊伍人數：");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(146, 140, 103, 23);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(220, 50, 103, 23);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(220, 95, 103, 23);
		panel_2.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(220, 137, 103, 23);
		panel_2.add(lblNewLabel_1_1_2);
		
		

		mainframeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登入畫面
				
				
				dispose();//关闭当前登录页面
			}

			

			
		});
	}
}
