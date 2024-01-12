package Image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

public class Newad extends JFrame {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel imageDisplayPanel;
	private JDateChooser dateChooser1;
	private JDateChooser dateChooser2;
	private JTextField previewTextField;
	private File adFolder;
	private int userCount = 0;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Newad window = new Newad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Newad() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(null);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton uploadButton = new JButton("Upload Image");
		uploadButton.setBounds(157, 27, 97, 23);
		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});
		buttonPanel.setLayout(null);
		buttonPanel.add(uploadButton);
		buttonPanel.setBounds(38, 100, 412, 50);
		mainPanel.add(buttonPanel);

		previewTextField = new JTextField("預覽圖片");
		previewTextField.setEditable(false);
		previewTextField.setBounds(38, 150, 412, 40);
		previewTextField.setFont(new Font("新細明體", Font.PLAIN, 20));
		mainPanel.add(previewTextField);

		JButton MainCommodityButton = new JButton("商品");
		MainCommodityButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainCommodityButton.setBounds(500, 425, 100, 30);
		mainPanel.add(MainCommodityButton);

		JButton MainSureButton = new JButton("確認");
		MainSureButton.setFont(new Font("新細明體", Font.PLAIN, 15));
		MainSureButton.setBounds(650, 425, 100, 30);
		mainPanel.add(MainSureButton);

		JLabel adSaid = new JLabel("廣告商投入廣告");
		adSaid.setFont(new Font("新細明體", Font.PLAIN, 25));
		adSaid.setBounds(160, 60, 250, 30);
		mainPanel.add(adSaid);

		imageDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imageDisplayPanel.setBackground(new Color(192, 192, 192));
		imageDisplayPanel.setBounds(50, 200, 400, 250);
		imageDisplayPanel.setBorder(new LineBorder(Color.BLACK));
		mainPanel.add(imageDisplayPanel, BorderLayout.CENTER);

		JPanel datePanel1 = new JPanel(new FlowLayout());
		JLabel lblNewLabel_1 = new JLabel("初始日期");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		datePanel1.add(lblNewLabel_1);
		dateChooser1 = new JDateChooser();
		datePanel1.add(dateChooser1);
		datePanel1.setBounds(500, 50, 250, 30);
		mainPanel.add(datePanel1);

		JPanel datePanel2 = new JPanel(new FlowLayout());
		JLabel lblNewLabel_2 = new JLabel("最後日期");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		datePanel2.add(lblNewLabel_2);
		dateChooser2 = new JDateChooser();
		datePanel2.add(dateChooser2);
		datePanel2.setBounds(500, 100, 250, 30);
		mainPanel.add(datePanel2);

		JLabel checkPassword_title = new JLabel("金額 :");
		checkPassword_title.setFont(new Font("新細明體", Font.PLAIN, 28));
		checkPassword_title.setBounds(575, 250, 100, 30);
		mainPanel.add(checkPassword_title);
		
		Date startDate = dateChooser1.getDate();
		Date endDate = dateChooser2.getDate();
		
		//路徑黨設定
		adFolder = new File("Image/ad");
		if (!adFolder.exists()) {
			adFolder.mkdir();
		}
					
		MainSureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//確認狀態
	
			}
		});
		
		
	}

	String uploadImage() {
		String pic = "";
		JFileChooser fileChooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files ( .jpg , .jpeg , .gif )", "jpg",
				"jpeg", "gif");
		fileChooser.setFileFilter(filter);

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			String selectedFilePath = fileChooser.getSelectedFile().getPath();
			displayImage(selectedFilePath);
			pic = selectedFilePath;
		}
		return pic;
	}

	private void displayImage(String filePath) {
		System.out.println("Selected File: " + filePath);


		File imageFile = new File(filePath);
		String[] arr = filePath.split("\\.");
		Path newFilePath = adFolder.toPath().resolve(getNewFileName("."+arr[arr.length-1]));

		try {
			Files.copy(imageFile.toPath(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(newFilePath.toString());
		
		// 調整圖片大小為 (140, 329)大小設定這裡
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(140, 329, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);

		JLabel imageLabel = new JLabel(icon);

		imageDisplayPanel.removeAll();
		imageDisplayPanel.add(imageLabel);

		previewTextField.setText("預覽圖片: " + newFilePath.toString());

		imageDisplayPanel.revalidate();
		imageDisplayPanel.repaint();
	}

	private String getNewFileName(String end) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = dateFormat.format(new Date());
		
		return "user" + "_" + dateStr + end;
	}
}
