package LoginSystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import LBNgames.*;


public class OnlyPWFrame extends forgetPWFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField password;
	private JPasswordField checkpassword;
	private String user="";
	

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlyPWFrame frame = new OnlyPWFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public OnlyPWFrame() {
		//user=forgetPW.userName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("LBN");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 95, 24);
		contentPane.add(lblNewLabel);
		// 建立標籤LBN
		
		JLabel title = new JLabel("更改密碼");
		title.setFont(new Font("新細明體", Font.PLAIN, 25));
		title.setBounds(280, 11, 120, 30);
		contentPane.add(title);
		
		JButton MainFrameButton = new JButton("回首頁");
		MainFrameButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainFrameButton.setBounds(591, 11, 90, 30);
		contentPane.add(MainFrameButton);
		
		JLabel Label_1 = new JLabel("建立新密碼");
		Label_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		Label_1.setBounds(120, 115, 76, 18);
		contentPane.add(Label_1);
		// 建立密碼標籤
		
		JLabel Label_2 = new JLabel("確認密碼");
		Label_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		Label_2.setBounds(120, 210, 76, 18);
		contentPane.add(Label_2);
		// 建立確認密碼標籤
		
		password = new JPasswordField();
		password.setBounds(236, 110, 200, 20);
		contentPane.add(password);
		//設定密碼框
		
		checkpassword = new JPasswordField();
		checkpassword.setBounds(236, 206, 200, 20);
		contentPane.add(checkpassword);
		//設定確認密碼框
		
		JButton changePW = new JButton("確認");
		changePW.setFont(new Font("新細明體", Font.PLAIN, 15));
		changePW.setBounds(280, 280, 110, 40);
		contentPane.add(changePW);
		
		
		
		
		changePW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登入畫面
				System.out.println("buttoncheck:"+user);
				String getPW = String.valueOf(password.getPassword());
				String getcheckPW = String.valueOf(checkpassword.getPassword());
				
				mySQLSaveUsers saveuser=new mySQLSaveUsers();
				if(getPW.equals(null)| getcheckPW.equals(null) ) {
					JOptionPane.showMessageDialog(null, "請輸入密碼");
					//dispose();//关闭当前登录页面
				}
				else if(getPW.equals(getcheckPW)) {
					System.out.println("確認密碼正確");
					try {
						System.out.println(user);
						saveuser.changepassword(user,getPW);
						dispose();//关闭当前登录页面
					}catch(Exception ex){
						
						}

				}
				else {
					JOptionPane.showMessageDialog(null, "密碼有錯誤，請重新輸入");
				}
			}
		});
		
		MainFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登入畫面
				System.out.println("OnlyPW跳到登入畫面");
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});
	}

	public void saveUser(String ID) {
		// TODO Auto-generated method stub
		this.user=ID;
	}


}
