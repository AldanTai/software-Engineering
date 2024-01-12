package LBNgames;

//這是視窗畫面
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Email.SendEmail;
import LoginSystem.VerCodeFrame;
import LoginSystem.mySQLSaveUsers;
import OldFrame.MainFrame;




public class forgetPWFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JTextField email;
	protected String userName=new String();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgetPWFrame frame = new forgetPWFrame();
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
	public forgetPWFrame() {
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
		lblNewLabel.setFont(new Font("Ink Free", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 95, 24);
		contentPane.add(lblNewLabel);
		// 建立標籤LBN
		
		JButton MainFrameButton = new JButton("回首頁");
		MainFrameButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainFrameButton.setBounds(591, 11, 90, 30);
		contentPane.add(MainFrameButton);
		
		JLabel title = new JLabel("忘記密碼");
		title.setFont(new Font("新細明體", Font.PLAIN, 25));
		title.setBounds(280, 11, 120, 30);
		contentPane.add(title);
		
		JLabel Label_1 = new JLabel("帳號");
		Label_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		Label_1.setBounds(120, 115, 46, 18);
		contentPane.add(Label_1);
		
		JLabel Label_2 = new JLabel("email");
		Label_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		Label_2.setBounds(120, 210, 46, 18);
		contentPane.add(Label_2);
		
		JButton changePW = new JButton("確認");
		changePW.setFont(new Font("新細明體", Font.PLAIN, 15));
		changePW.setBounds(280, 280, 110, 40);
		contentPane.add(changePW);
		
		
		ID = new JTextField();
		ID.setBounds(236, 110, 200, 20);
		contentPane.add(ID);
		//輸入帳號名稱
		
		email = new JTextField();
		email.setBounds(236, 206, 200, 20);
		contentPane.add(email);
		//輸入信箱名稱
		
		MainFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁畫面
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});
		
		changePW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登入畫面
				if(Checkusers()) {
					//JOptionPane.showMessageDialog(null, "資料通過");
					
					SendEmail mail= new SendEmail();
					int verificationCode = (int)(Math.random()*100000);//隨機數驗證碼
					mail.SendEmail(verificationCode, email.getText());
			
					VerCodeFrame verCodeframe = new VerCodeFrame();
					verCodeframe.setVisible(true);
					String txt =String.format("%06d",verificationCode);
					verCodeframe.FPWcheck(txt,ID.getText());//提供值	
					System.out.println("顯示驗證碼 ="+ txt);
					
					dispose();
				}				
				
			}
		});
	}
	
	public boolean Checkusers() {
		boolean check=false;
		
		String getemail=email.getText();
		//int verificationCode = 1;
		userName= ID.getText();
		mySQLSaveUsers su=new mySQLSaveUsers();
		if(su.checkUser("user", userName, getemail)) {

			check=true;
		}
		else {
			JOptionPane.showMessageDialog(null, "請輸入正確的帳號與信箱");
		}
		
		return check;	
	}
	
	
}
