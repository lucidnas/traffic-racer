package racing;

import java.awt.Graphics;

public class Trees extends ImageLoader
{

	public Trees(String name, int x, int y, int d) {
		super(name, x, y, d);
	}

	
	public void draw(Graphics g)
	{
		
		
		for(int i = 0; i < d; i++) g.drawImage(image, x, y - Camera.y - i * 80, null);
	}

}
