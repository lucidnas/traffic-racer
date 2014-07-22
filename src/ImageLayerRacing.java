package racing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayerRacing 
{
	Image image;
	private int x;
	private int y;
	private int d;
	
	public ImageLayerRacing(String name, int x, int y, int d){
		
		image = Toolkit.getDefaultToolkit().getImage("C:/Users/nasbone/workspace/images/" + name);
		
		this.x = x;
		this.y = y;
		
		this.d = d;	
	}
	

	public void draw(Graphics g){
		
		for(int i = 0; i < 10; i++){
		
		g.drawImage(image, x -CameraRacing.x/d + i * 720, y - CameraRacing.y, null);
		}
	}
	
	
	
	
}
