package racing;

public class Camera 
{
	public static int x;
	public static int y;
	
	public static void set(int x, int y)
	{
		Camera.x = x;
		Camera.y = y;
	}

	public static void moveLeftBy(int dx)
	{
		Camera.x += dx; 
	}
	
	public static void moveRightBy(int dx)
	{
		Camera.x -= dx;
	}
	
	public static void moveUpBy(int dy)
	{
		Camera.y -= dy;
	}
	
	public static void moveDownBy(int dy)
	{
		Camera.y += dy;
	}
}
