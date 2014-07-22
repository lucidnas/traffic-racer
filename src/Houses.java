package racing;

import java.awt.Graphics;

public class Houses extends ImageLoader
{

	public Houses(String name, int x, int y, int d) {
		super(name, x, y, d);
		
	}
	
	public void draw(Graphics g){
		
		
		for (int i = 0; i < 100; i++) g.drawImage(image, x, y - Camera.y - i * 1500, null);
	
	
}

	
	

}
