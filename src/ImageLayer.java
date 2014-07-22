package racing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer 
{
	Image image;
	private int x;
	private int y;
	private int d;
	
	public ImageLayer(String name, int x, int y, int d){
		
		image = Toolkit.getDefaultToolkit().getImage(name);
		
		this.x = x;
		this.y = y;
		
		this.d = d;	
	}
	

	public void draw(Graphics g){
		
		
			g.drawImage(image, x -Camera.x, y - Camera.y, null);
		//g.drawImage(image, x -Camera.x/d + i * 720, y - Camera.y, null);
		
	}
	
	
	
	
}
