package Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SaveImage {
	
	private File Folder;
	private String path="Image/";
	
	
    public String saveImage(String local_pic, String picName, int choose) throws Exception {
    	String[] getsplit=local_pic.split("\\.");
    	System.out.println("圖片格式： "+getsplit[getsplit.length-1]);
    	String pic="";
        // 源图像文件路径  
        String srcImagePath = local_pic;

        // 目标文件夹路径，目前屬於本地路徑檔
        switch(choose) {
        case 0:
        	Folder = new File(path+"user/") ;
        	break;
        case 1:
        	Folder = new File(path+"shop/") ;
        	break;
        case 2:
        	Folder = new File(path+"team/") ;
        	break;
        case 3:
        	Folder = new File(path+"title/") ;
        	break;
        case 4:
        	Folder = new File(path+"ad/") ;
        	break;
        }
        
        
        
        // 新文件名
        String newFileName = Folder.toPath()+picName+"."+getsplit[getsplit.length-1];
        File srcFile = new File(srcImagePath);
        
        // 建立目标文件夹 
        if (!Folder.exists()) {
        	Folder.mkdirs();
        }

        // 构建目标文件
        File destFile = new File( newFileName);
        pic= newFileName;
        // 文件Copy
        InputStream in = new FileInputStream(srcFile);
        OutputStream out = new FileOutputStream(destFile);
        
        //圖片複製緩衝區
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        in.close();
        out.close();
        
        System.out.println("图像文件复制完毕");

        return pic;
    }
    public void ad(String local_pic,String pic, int choose) {
    	this.path +="ad/"; 
    	
    	try {
			saveImage(local_pic,pic,choose);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
