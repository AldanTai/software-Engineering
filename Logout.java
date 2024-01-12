package Personal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Logout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logout frame = new Logout();
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
	public Logout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("登出");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 25));
		lblNewLabel.setBounds(180, 10, 85, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("確定要登出嗎？\r\n");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(154, 95, 111, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("取消\r\n");
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		btnNewButton.setBounds(105, 170, 85, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("確認");
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		btnNewButton_1.setBounds(230, 170, 85, 30);
		contentPane.add(btnNewButton_1);
	}
}
