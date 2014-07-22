package racing;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound 
{
		
	
	private AudioClip clip;
	
	public Sound(String filename)
	{
		try{
			clip = Applet.newAudioClip(Sound.class.getResource("sounds/"+ filename));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void play()
	{
		try{
			clip.play();
		}catch(Exception e){}
		
	}
}
