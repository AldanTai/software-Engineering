package LoginSystem;

import java.io.*;
import java.nio.file.*;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import Email.SendEmail;
import Image.SaveImage;


public class SaveUsers {
	
	//玩家資料
    public String userName;
    private String email;
    private String password;
    private String photo;
    private String team;
    private String formyself;
    
    private String path = "C:/school/eclipse/LBN/usersbase/";
    private String usingpath = "C:/school/eclipse/LBN/usinguser/";
    
    private boolean findjs=false;
    //知道是否有此使用者
    public void Users(String userName)throws Exception {
    	String path;
//    	if(!findjs) {
//    		//執行本地資料夾的內容
//    		path=this.usingpath+userName+".json";
//    	}
//    	else {
    		//執行雲段資料夾的內容
    	path=this.path+userName+".json"; 
    	
		Object ob = new JSONParser().parse(new FileReader(path));
			
    	 // typecasting ob to JSONObject建立 json物件，要做轉乘json的功能
        JSONObject js = (JSONObject) ob;
            
        this.userName = userName;
        this.email = (String) js.get("email");
        this.password = (String) js.get("password");
        this.team = (String)js.get("team");
        this.photo = (String)js.get("photo");
        this.formyself = (String)js.get("formyself");
        //在用輸出兩值
        
    }
    

    
    //登入使用者
     public boolean Login(String userName ,String checkpassword) {
    	JSONObject js = new JSONObject();
    	boolean check=false;
    	try {
    		Users(userName);
    		System.out.println("找到使用者");
    		if(this.password.equals(checkpassword)) {
    			System.out.println("成功登入");
    			check=true;
    			try {
    				//把使用者存到新的資料夾，使用中的資料夾
    				
    				js.put("userName", userName);
    		        js.put("password", password);
    		        js.put("email", email);
    		        js.put("team", team);
    		        js.put("photo", photo);
    		        js.put("formyself", formyself);
    		        
    		        //存在本地資料夾
    	             FileWriter file = new FileWriter( usingpath +userName + ".json");
    	             System.out.println(usingpath+userName+".json");
    	             file.write(js.toJSONString());
    	             System.out.println("登入存取成功");
    	             
    	             file.close();
    	          } catch (IOException e) {
    	        	  System.out.println("登入存取失敗");
    	        	  e.printStackTrace();
    	          }
    	}
    	else {
    		System.out.println("登入失敗");
    		JOptionPane.showMessageDialog(null, "帳號密碼錯誤，請重新輸入");
    	}
    	} catch(Exception e) {
    		System.out.println("無法搜尋到此使用者");
    		JOptionPane.showMessageDialog(null, "無法搜尋到此使用者");
    	}
    	return check;
    }
    
    //Register目前狀況正常存儲值並生成json檔
    public void Register(String userName, String password, String email,String team, String photo, String formyself) {
        // Creating a JSONObject object
        JSONObject js = new JSONObject();

        //Inserting key-value pairs into the json object
        js.put("userName", userName);
        js.put("password", password);
        js.put("email", email);
        js.put("team", team);
        js.put("photo", photo);
        js.put("formyself", formyself);
        
        System.out.println(js.toString());
        try {
             FileWriter file = new FileWriter( path +userName + ".json");
             file.write(js.toJSONString());
             System.out.println("雲端資料存處成功");
             if(!photo.equals("")) {
            	 SaveImage SI = new SaveImage();
            	 try {
					SI.saveImage(photo, userName,0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 System.out.println("資料存處失敗");
				}
            	 
             }
             file.close();
          } catch (IOException e) {
        	  System.out.println("資料存處失敗");
        	  e.printStackTrace();
          }
    }
    
    //與Login相同，但功能是用來判斷使否有此帳號
    public boolean forgetPW(String userName,String email) {
    	int verificationCode = (int)(Math.random()*100000);//隨機數驗證碼
    	System.out.println("check forgetPW user:"+userName);
    	boolean check=false;
    	try {
    		Users(userName);
    		System.out.println("找到使用者");
    		if(this.email.equals(email)) {
    			System.out.println("信箱正確");
    			SendEmail mail= new SendEmail();
				mail.SendEmail(verificationCode, email);
				//寄信
				VerCodeFrame verCodeframe = new VerCodeFrame();
				verCodeframe.setVisible(true);
				String txt =String.format("%06d",verificationCode);
				System.out.println("txt ="+txt);
				//verCodeframe.FPWcheck(txt,userName,email);//提供值
				//確認驗證碼
    			check=true;
    	}
    	else {
    		System.out.println("登入失敗");
    		JOptionPane.showMessageDialog(null, "帳號信箱錯誤，請重新輸入");
    	}
    	} catch(Exception e) {
    		System.out.println("無法搜尋到此使用者");
    		JOptionPane.showMessageDialog(null, "無法搜尋到此使用者");
    	}
    	return check;
    }
    
    //另類的Register
    public void RepushPW(String userName ,String password,String email) {
    	try {
    		Users(userName);
    		System.out.println("找到使用者");
    		JSONObject js = new JSONObject();
    		js.put("userName", userName);
            js.put("password", password);
            js.put("email", email);
            js.put("team", team);
            js.put("photo", photo);
    		System.out.println(js.toString());
    		try {
                FileWriter file = new FileWriter( path +userName + ".json");
                file.write(js.toJSONString());
                System.out.println("資料存處成功");
                file.close();
             //   LoginFrame login=new LoginFrame();
            //    login.setVisible(true);
             } catch (IOException e) {
           	  System.out.println("資料存處失敗");
           	  e.printStackTrace();
             }
    		
    	} catch(Exception e) {
    		System.out.println("沒接收到使用者");
    		//JOptionPane.showMessageDialog(null, "沒接收到使用者");
    	}

    }
    
    //找檔案內的資料
    public boolean findjson() {
    	boolean check=false;
    	File dir = new File(usingpath); 
    	File[] files = dir.listFiles();
    	if(  files.length==0) {
    		System.out.println("No JSON file found");
    		// 遍歷全部文件,目录下没有找到任何JSON文件
    	}
    	else {
    	for (File file : files) {
    		// 獲取文件名  
    		String fileName = file.getName();

          // 根据文件名後綴名判斷是否為JSON文件
          if (fileName.endsWith(".json")) {

            // 找到了JSON文件
            
            // 獲取JSON文件名 
            String jsonFileName = file.getName().replace(".json", "");
            System.out.println("Found JSON File: " + jsonFileName);
            
            
            try {
            	check=true;
				findjs=true;
				Users(jsonFileName);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				findjs=false;
			}
          }

        }
    	}
    	dir.exists();
    	
    	return check;
      }
    
    public void deleteusing(String userName) {
    	String jsonFilePath = usingpath+userName+".json"; 
    	System.out.println(usingpath+userName+".json");
    	clear();
        Path path = Paths.get(jsonFilePath);
        File Files = new File(jsonFilePath);
        Files.delete();
          System.out.println("JSON file deleted successfully!");
      }

    public void clear() {
    	this.userName=null;
    	this.password=null;
    	this.email=null;
    	this.team=null;
    	this.photo=null;
    	this.formyself=null;
    }
    	
    
    

}

