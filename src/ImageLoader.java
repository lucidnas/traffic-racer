package racing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLoader 
{
	public Image image;
	public int x;
	public int y;
	public int d;
	
	public  ImageLoader(String name, int x, int y, int d){
		
		image = Toolkit.getDefaultToolkit().getImage("images/"+ name);
		
		this.x = x;
		this.y = y;
		
		this.d = d;	
	}
	

	public void draw(Graphics g){
		
		
			g.drawImage(image, x, y - Camera.y, null);
		//g.drawImage(image, x -Camera.x/d + i * 720, y - Camera.y, null);
		
	}
	
	
	
	
}
