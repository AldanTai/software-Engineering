package LBNgames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Image.SaveImage;

import java.awt.EventQueue;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class adFrame  extends JFrame{

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel imageDisplayPanel;
    private JDateChooser dateChooser1;
    private JDateChooser dateChooser2;
    private JTextField previewTextField; // 新增預覽圖片的文字框
    private String photo;
    private String user;
    
    private Date Date1;
    private Date Date2;

    private float wallet= (float) 0.0;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adFrame window = new adFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public adFrame() {
        initialize();
    }

    private void initialize() {
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 創建主面板
        mainPanel = new JPanel(null);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // 添加上傳圖片按鈕到新的面板
        JPanel buttonPanel = new JPanel();
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(157, 27, 97, 23);
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadImage("ad/", user);
            }
        });
        buttonPanel.setLayout(null);
        buttonPanel.add(uploadButton);
        buttonPanel.setBounds(38, 100, 412, 50);
        mainPanel.add(buttonPanel);
        
        JButton mainButton = new JButton("回首頁");
		mainButton.setFont(new Font("新細明體", Font.BOLD, 20));
		mainButton.setBounds(650, 10, 110, 35);
		mainPanel.add(mainButton);
		
		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳到首頁畫面
				Interface_control mainframe=new Interface_control();
				mainframe.setVisible(true);
				dispose();//关闭当前登录页面
			}
		});
        
        
        // 添加預覽圖片的文字框
        previewTextField = new JTextField("預覽圖片");
        previewTextField.setEditable(false);
        previewTextField.setBounds(38, 150, 412, 40);
        previewTextField.setFont(new Font("新細明體", Font.PLAIN, 20));
        mainPanel.add(previewTextField);

        // 商品按鈕
        JButton MainCommodityButton = new JButton("商品");
        MainCommodityButton.setFont(new Font("新細明體", Font.PLAIN, 15));
        MainCommodityButton.setBounds(500, 425, 100, 30);
        mainPanel.add(MainCommodityButton);
        // 確認按鈕
        JButton MainSureButton = new JButton("確認");
        MainSureButton.setFont(new Font("新細明體", Font.PLAIN, 15));
        MainSureButton.setBounds(650, 425, 100, 30);
        mainPanel.add(MainSureButton);
        // 添加 "請廣告商投入你的廣告" 標籤
        JLabel adSaid = new JLabel("廣告商投入廣告");
        adSaid.setFont(new Font("新細明體", Font.PLAIN, 25));
        adSaid.setBounds(160, 60, 250, 30);
        mainPanel.add(adSaid);

        // 創建一個用於存儲上傳圖片的面板
        imageDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageDisplayPanel.setBackground(new Color(192, 192, 192));
        imageDisplayPanel.setBounds(50, 200, 400, 250);
        imageDisplayPanel.setBorder(new LineBorder(Color.BLACK)); // 設置邊框
        mainPanel.add(imageDisplayPanel, BorderLayout.CENTER);

        // 添加日期選擇器到新的面板
        JPanel datePanel1 = new JPanel(new FlowLayout());
        JLabel lblNewLabel_1 = new JLabel("初始日期");
        lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
        datePanel1.add(lblNewLabel_1);
        dateChooser1 = new JDateChooser();
        datePanel1.add(dateChooser1);
        datePanel1.setBounds(500, 50, 250, 30);
        mainPanel.add(datePanel1);

        // 添加第二個日期選擇器
        JPanel datePanel2 = new JPanel(new FlowLayout());
        JLabel lblNewLabel_2 = new JLabel("最後日期");
        lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 15));
        datePanel2.add(lblNewLabel_2);
        dateChooser2 = new JDateChooser();
        datePanel2.add(dateChooser2);
        datePanel2.setBounds(500, 100, 250, 30);
        mainPanel.add(datePanel2);
        
        // 確認時間按鈕
        JButton timeSureButton = new JButton("時間確認");
        timeSureButton.setFont(new Font("新細明體", Font.PLAIN, 15));
        timeSureButton.setBounds(580, 150, 100, 30);
        mainPanel.add(timeSureButton);

        // 添加顯示金額的文本框
        JLabel wallettitle = new JLabel("金額"+wallet+"$(USD)");
        wallettitle.setFont(new Font("新細明體", Font.PLAIN, 28));
        wallettitle.setBounds(535, 250, 180, 30);
        mainPanel.add(wallettitle);
        
        dateChooser1.addPropertyChangeListener("date", evt -> {
        	  if(evt.getNewValue() != null){
        	     // 监听到选择日期事件
        		  Date1 = (Date)evt.getNewValue();

        	  }
        	   
        	});
        	   
        dateChooser2.addPropertyChangeListener("date", evt -> {
      	  if(evt.getNewValue() != null){
      	     // 监听到选择日期事件
      	  	  Date2 = (Date)evt.getNewValue();
      	  	 
      	  } 
      	   
      	});
        
        //時間按鈕被按下
        timeSureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Date1 != null && Date2 != null) {
					ZoneId zone = ZoneId.of("Asia/Taipei"); 

					// 转换为LocalDate
					LocalDate startDate = Date1.toInstant()
                                  .atZone(zone)
                                  .toLocalDate();

					LocalDate endDate = Date2.toInstant()
        	                    .atZone(zone)   
        	                    .toLocalDate(); 

					// 计算天数差      
					long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
					if(daysBetween>0) {
						
						wallet = Math.abs((float) (daysBetween*0.3));
						System.out.println(wallet);
						setVisible(true);
						}
				}
			}
		});
        
        System.out.println(Date1 +" , "+Date2);
        
       
        
        MainSureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!photo.equals("") && wallet != 0.0){
				SaveImage SI = new SaveImage();
				SI.ad(photo, user, 4);
				}
			}
		});

    }

    // 接收jpg檔案跟目錄
    public String uploadImage(String foldername,String user) {
    	String pic="";
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG files ( .jpg , .jpeg , .gif )", "jpg", "jpeg", "gif");
        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().getPath();
            Date selectedDate1 = dateChooser1.getDate();
            Date selectedDate2 = dateChooser2.getDate();
            displayImage(selectedDirectory);
            String[] str = selectedDirectory.split("\\.");
            pic=foldername+user+"."+str[str.length-1];
        }
        return pic;
    }

    private JLabel displayImage(String directoryPath) {
    	
        ImageIcon icon = new ImageIcon(directoryPath);
        JLabel imageLabel = new JLabel(icon);

        imageDisplayPanel.removeAll();
        imageDisplayPanel.add(imageLabel);

        // 設定預覽圖片的文字框
        previewTextField.setText("預覽圖片: " + directoryPath);
        photo=directoryPath;
        imageDisplayPanel.revalidate();
        imageDisplayPanel.repaint();
        
        return imageLabel;
    }
}
