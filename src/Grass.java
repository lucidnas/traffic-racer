package racing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Grass {
	public int x;
	public int y;
	public Image image;
	public int d;
	
	public Grass(String name, int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
		image = Toolkit.getDefaultToolkit().getImage("images/" +name);
	}
	
	public void draw(Graphics g){
				
		for(int i = 0; i < 300; i++)
		{
			// limits number of grass drawn to camera view
			if (i >= Camera.y)
			{ 
				// draws the grass on the y axis
				for (y= 0; y < Game.winH; y += image.getHeight(null) )
					{
					for(x = 0; x < Game.winW; x += image.getWidth(null))
					
					{
					g.drawImage(image, x - Camera.x, y - Camera.y - i *image.getHeight(null) , null);
					}
					
				
				}
			
			}
		
		

	
		// TODO Auto-generated constructor stub
	
		}
	}
}
