package Image;

import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.*; 
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class showImage extends JFrame {

    
    JLabel label;
    ArrayList<File> imageList;
    int currentIndex = 0;

    public JLabel showImage(String folderPath,int x, int y, int times) {
    
    	label = new JLabel();
    	label.setVisible(true); 
    	add(label);
        
        imageList = new ArrayList<>();
        File folder = new File(folderPath);
        for(File f: folder.listFiles()) {
            imageList.add(f); 
        }
        
        showNextImage(x,y);

        Timer timer = new Timer(times, (e) -> {
            showNextImage(x,y);
        });
        timer.setRepeats(true);
        timer.start();
        
        return label;
		
        
    }
    
    
    
    public void showNextImage(int x, int y) {
        File imageFile = imageList.get((int)(Math.random()*10)%imageList.size()); 
        
        try {
        	ImageIcon icon= new ImageIcon(imageFile.toString());
            if (isGIF(imageFile)) { // 检查是否gif动图 

                if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    icon.setImageObserver(label);
                }
            } 
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            
            label.setIcon(icon);
            revalidate();
            repaint();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    private boolean isGIF(File file) {
        return file.getName().endsWith("gif"); 
    }
    
}
