package LoginSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LBNgames.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JTextField;
import javax.swing.JButton;

public class VerCodeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VerCode;
	private String checkVer = new String();//寄信時也會收到的值
	private String ID = new String();
	private String password = new String();
	private String email = new String();
	private int checksmun = 1;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerCodeFrame frame = new VerCodeFrame();
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
	public VerCodeFrame() {
		System.out.println("運行驗證碼畫面");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel VerCodetitle = new JLabel("驗證碼");
		VerCodetitle.setFont(new Font("新細明體", Font.PLAIN, 25));
		VerCodetitle.setBounds(165, 10, 85, 30);
		contentPane.add(VerCodetitle);
		
		JLabel lblNewLabel_1 = new JLabel("請輸入驗證碼");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(24, 73, 111, 23);
		contentPane.add(lblNewLabel_1);
		
		VerCode = new JTextField();
		VerCode.setBounds(145, 74, 168, 21);
		contentPane.add(VerCode);
		VerCode.setColumns(6);
		
		JButton checkButton = new JButton("確認");
		checkButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		checkButton.setBounds(165, 160, 85, 30);
		contentPane.add(checkButton);
		
		JLabel wrongtitle = new JLabel("");
		wrongtitle.setFont(new Font("新細明體", Font.PLAIN, 15));
		wrongtitle.setBounds(145, 105, 168, 23);
		contentPane.add(wrongtitle);
		//錯誤提示
		
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//確認驗證碼
				if(FPWcheck(checkVer,ID) && checksmun ==0){
					System.out.println("結束驗證碼畫面");
					dispose();//关闭当前登录页面
					
					OnlyPWFrame onlyPWframe=new OnlyPWFrame();
					onlyPWframe.setVisible(true);
					onlyPWframe.saveUser(ID);
					System.out.println("VerCode ID:"+ID);
					
					System.out.println("跳到OnlyPW畫面");
				}
				else if(check(checkVer,ID,password,email,checksmun) && checksmun==1){
					
					dispose();//关闭当前登录页面
					LoginFrame Loginframe=new LoginFrame();
					Loginframe.setVisible(true);
				}
				 
				else {
					wrongtitle.setText("錯誤驗證碼，請確認後再輸入");
					
				}
			}
		});
	}
	
	
	
	public boolean check(String txt,String ID,String password, String email, int checksmun) {
		boolean checks= false;
		checkVer = txt;
		this.ID = ID;
		this.password = password;
		this.email = email;
		this.checksmun = checksmun;
		
		String getVerCode = VerCode.getText();
		if(getVerCode.equals(checkVer)) {
			checks=true;
			System.out.println("資料上傳");
			mySQLSaveUsers saveusers= new mySQLSaveUsers();
			saveusers.Register(ID,password,email,"","","",0);
		}
		else {
			checks=false;
		}
		return checks;
	}
	
	public boolean FPWcheck(String txt, String ID) {
		
		this.ID = ID;
		boolean checks= false;
		checkVer = txt;
		String getVerCode = VerCode.getText();
		if(getVerCode.equals(checkVer)) {
			checksmun =0;
			checks=true;
			
		}
		else {
			checks=false;
		}
		return checks;
	}
}
