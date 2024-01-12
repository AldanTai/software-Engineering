package LoginSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

import Email.SendEmail;
import LBNgames.*;



public class RegisterFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ID;
	private JPasswordField password;
	private JPasswordField checkpassword;
	private JTextField email;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
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
		JLabel Title = new JLabel("LBN");
		Title.setFont(new Font("Ink Free", Font.BOLD, 20));
		Title.setBounds(10, 10, 95, 24);
		contentPane.add(Title);
		// 建立標籤LBN
		
		JButton MainFrameButton = new JButton("回首頁");
		MainFrameButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainFrameButton.setBounds(591, 11, 90, 30);
		contentPane.add(MainFrameButton);
		// 建立回首頁按鈕
		
		JLabel ID_title = new JLabel("帳號");
		ID_title.setFont(new Font("新細明體", Font.PLAIN, 15));
		ID_title.setBounds(95, 70, 46, 18);
		contentPane.add(ID_title);
		// 建立帳號標籤
		
		JLabel password_title = new JLabel("密碼");
		password_title.setFont(new Font("新細明體", Font.PLAIN, 15));
		password_title.setBounds(95, 130, 46, 18);
		contentPane.add(password_title);
		// 建立密碼標籤
		
		JButton RegisterButton = new JButton("註冊\r\n");
		RegisterButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		RegisterButton.setBounds(289, 330, 90, 30);
		contentPane.add(RegisterButton);
		// 建立註冊按鈕
		
		
		JLabel Register_title = new JLabel("註冊");
		Register_title.setFont(new Font("新細明體", Font.PLAIN, 25));
		Register_title.setBounds(306, 10, 61, 30);
		contentPane.add(Register_title);
		// 建立標題"註冊"標籤
		
		JLabel checkPassword_title = new JLabel("確認密碼\r\n");
		checkPassword_title.setFont(new Font("新細明體", Font.PLAIN, 15));
		checkPassword_title.setBounds(95, 190, 73, 18);
		contentPane.add(checkPassword_title);
		// 建立確認密碼標籤
		
		JLabel lblNewLabel_3 = new JLabel("email");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(95, 250, 73, 18);
		contentPane.add(lblNewLabel_3);
		// 建立信箱標籤
		
		ID = new JTextField();
		ID.setBounds(240, 70, 200, 20);
		contentPane.add(ID);
		ID.setColumns(10);
		//ID輸入框，也就是使用者ID，以後登入就輸入ID與密碼即可登入
		
		password = new JPasswordField();
		password.setBounds(240, 129, 200, 20);
		contentPane.add(password);
		//密碼輸入框，也就是使用者密碼，以後登入就輸入ID與密碼即可登入
		
		checkpassword = new JPasswordField();
		checkpassword.setBounds(240, 189, 200, 20);
		contentPane.add(checkpassword);
		//確認密碼輸入框，也就是雙重驗證密碼，確認使用者沒有忘記密碼
		
		email = new JTextField();
		email.setBounds(240, 249, 200, 20);
		contentPane.add(email);
		//信箱輸入框，用來確認帳號不為假帳號來佔系統存處空間，忘記密碼也可以用信箱回傳確認是否為本人
		
		
		JLabel wrongID = new JLabel("");
		wrongID.setBounds(450, 72, 127, 15);
		contentPane.add(wrongID);
		//帳號重複錯誤
		
		
		MainFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到主頁畫面
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				System.out.println("註冊跳到主頁畫面");
				dispose();//关闭当前登录页面
			}
		});
		
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//先確認有沒有輸入
				String getID = ID.getText();
				String getPW = String.valueOf(password.getPassword());
				String getcheckPW = String.valueOf(checkpassword.getPassword());
				String getemail = email.getText();
				mySQLSaveUsers saveuser= new mySQLSaveUsers();
				
				
				if(getID.equals("") | getPW.equals("") | getcheckPW.equals("") | email.getText().equals("")) {
					System.out.println("註冊失敗");
					JOptionPane.showMessageDialog(null, "請輸入正確的內容");
					
				}
				else if(getPW.equals(getcheckPW)) {
					//寄信跟繼確認數字，如果通過就回主畫面，沒過就確認到過為止
					String check =saveuser.login("user",getID,getPW);
					if(check.equals("PW")) {
						wrongID.setText("此帳號名稱已被使用");
					}
					else {
						Saveusers(getID,getPW);
					}
					

				}
					
			}
		});
	}
	
	public void Saveusers(String ID ,String password) {
		
		int verificationCode = (int)(Math.random()*100000);//隨機數驗證碼
		
		String getemail=email.getText();
		//int verificationCode = 1;
		SendEmail mail= new SendEmail();
		System.out.println("運行信件程式");
		mail.SendEmail(verificationCode, getemail);
		
		VerCodeFrame verCodeframe = new VerCodeFrame();
		verCodeframe.setVisible(true);
		System.out.println("註冊畫面跳到驗證碼畫面");
		String txt =String.format("%06d",verificationCode);
		verCodeframe.check(txt,ID,password,getemail,1);//提供值
		System.out.println("顯示驗證碼 ="+ txt);
		dispose();

	}
}
