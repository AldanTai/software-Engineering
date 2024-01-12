package LBNgames;

import LBNgames.*;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import LoginSystem.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements WindowListener {
	
	private String name;

	  public MyFrame() {
	    addWindowListener(this); 
	  }
	  
	  @Override
	  public void windowClosing(WindowEvent e) {//監聽正在關閉時
		  Component frame = e.getComponent();
		  mySQLSaveUsers su = new mySQLSaveUsers();
	  		if(!su.checklogin()) {
	  			System.exit(0);
	  		}
	  		name = su.name;
			int result = JOptionPane.showConfirmDialog(//這是有選項的跳出視窗
				    frame, //可以只用null
				    "關閉要登出嗎?",//內容
				    "系統關閉通知",//標題
				    JOptionPane.YES_NO_OPTION // 或 JOptionPane.OK_CANCEL_OPTION
				    
				);
	    
	    //判斷按鈕
	    if(result==JOptionPane.YES_OPTION ) {
	    	su.Logout(name);
			System.exit(0);
	    }
	    else if(result== JOptionPane.NO_OPTION  ){
	    	System.exit(0);
	    }
	    
	  }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	  
	}
