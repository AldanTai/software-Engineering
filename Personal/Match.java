package Personal;
import java.awt.Dimension;
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
import javax.swing.JComboBox;


public class Match extends JFrame {
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
					Match frame = new Match();
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
	
	public Match() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
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
		
		Panel game = new Panel();
		
		game.setBounds(0, 35, 470, 265);
		panel_2.add(game);
		
		Dimension size = new Dimension(150, 30);
		game.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 150, 30);
		comboBox.setPreferredSize(size);
		comboBox.addItem("选项1");
		comboBox.addItem("选项2");
		game.add(comboBox);

		
			

			
	
	}
}
