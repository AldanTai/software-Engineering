package music;

import java.io.IOException;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;

public class Music {
	
	Clip clip;
	
	FloatControl volume;
	
	private String song="C:/school/eclipse/LBN/music/Toothless Dancing Meme [NEW VARIATIONS].wav";

	public Music() {
		Music((float) 0.0);
		
	}
	
	public void open(float volume) {
		Music(volume);
		clip.start();
	}
	
	private void Music(float d) {
		// TODO Auto-generated method stub
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(song)));
			//下面這被註解的部分不能運行
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 设置音量大小(0.0 - 1.0)
			if (volume != null) {
			    volume.setValue(d);     
			  }
			
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(){
		clip.stop();
	}
	
}
 
