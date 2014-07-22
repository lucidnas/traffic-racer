package racing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Road
{
	int x;
	int y;
	String name;
	Image image;
	public Road(String name, int x, int y) 
	{
		this.x = x;
		this.y = y;
		image = Toolkit.getDefaultToolkit().getImage("images/"+name);
	}
	
	public void draw(Graphics g)
	{
		
		for(int i = 0; i < 300; i++)
		{
			int limit = 512;
			// limits number of grass drawn to camera view
			//if ( (limit + Camera.y) > 0)
			{
				
				g.drawImage(image, x - Camera.x  , y - Camera.y - i * image.getHeight(null), null);
				//limit += 512;
			}
			//limit += 512;
			
			
			
		/*System.out.println(i*image.getHeight(null) + ", Camera: " + Camera.y + ", y: "+ y  );
		System.out.println(limit);
		System.out.println(limit + Camera.y);*/
		}
		
		
	}
}
