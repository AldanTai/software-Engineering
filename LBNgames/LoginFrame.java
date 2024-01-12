package LBNgames;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import LoginSystem.RegisterFrame;
import LoginSystem.mySQLSaveUsers;






public class LoginFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JPasswordField password;
	public String Loginwrongtitle="";
	public boolean checkforlogin=false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public LoginFrame() {
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
		lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 95, 24);
		contentPane.add(lblNewLabel);
		// 建立標籤LBN
		JButton MainFrameButton = new JButton("回首頁");
		MainFrameButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainFrameButton.setBounds(591, 11, 90, 30);
		contentPane.add(MainFrameButton);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(120, 85, 46, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密碼");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(120, 160, 46, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton RegisterButton = new JButton("註冊\r\n");
		RegisterButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		RegisterButton.setBounds(277, 288, 90, 30);
		contentPane.add(RegisterButton);
		
		JButton LoginButton = new JButton("登入\r\n");
		LoginButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		LoginButton.setBounds(277, 230, 90, 30);
		contentPane.add(LoginButton);
		
		JButton forgetPasswordButton = new JButton("忘記密碼");
		forgetPasswordButton.setBounds(473, 160, 90, 30);
		contentPane.add(forgetPasswordButton);
		
		JLabel lblNewLabel_3 = new JLabel("沒有帳號嗎？");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(136, 290, 103, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("登入");
		lblNewLabel_4.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(306, 10, 61, 30);
		contentPane.add(lblNewLabel_4);
		
		ID =  new JTextField();
		ID.setBounds(236, 85, 200, 20);
		contentPane.add(ID);
		//帳號輸入
		
		password = new JPasswordField();
		password.setBounds(236, 160, 200, 20);
		contentPane.add(password);
		//密碼輸入
		
		JLabel wrongtitle = new JLabel(Loginwrongtitle);
		wrongtitle.setBounds(236, 194, 200, 15);
		contentPane.add(wrongtitle);
		
		
		MainFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到登入畫面
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});
		
		LoginButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				// 先確認後再跳到登入畫面
				mySQLSaveUsers saveuser=new mySQLSaveUsers();
				String getID = ID.getText();
				String getPW = String.valueOf(password.getPassword());
				String check = saveuser.login("user",getID,getPW);
				
				if(check.equals("OK")) {
					JOptionPane.showMessageDialog(null, "成功登入");
					Interface_control mainframe=new Interface_control();
						
					mainframe.setVisible(true);
					dispose();//关闭当前登录页面
				}
				else if(check.equals("ID")) { 
					JOptionPane.showMessageDialog(null, "查無此帳號，請重新輸入");
					
				}
				else if(check.equals("PW")) { 
					JOptionPane.showMessageDialog(null, "密碼錯誤，請重新輸入");
					
				}
			}
		});
		
		
		forgetPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到忘記密碼畫面
				forgetPWFrame forgetPasswordframe=new forgetPWFrame();
				forgetPasswordframe.setVisible(true);
				dispose();//关闭当前登录页面
				
			}
		});
		
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到註冊畫面
				RegisterFrame Registerframe=new RegisterFrame();
				Registerframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});

	}
}
