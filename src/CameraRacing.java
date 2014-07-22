package racing;

public class CameraRacing 
{
	static int x;
	static int y;
	
	public static void set(int x, int y)
	{
		CameraRacing.x = x;
		CameraRacing.y = y;
	}

	public static void moveLeftBy(int dx)
	{
		x += dx;
	}
	
	public static void moveRightBy(int dx)
	{
		x -= dx;
	}
	
	public static void moveUpBy(int dy)
	{
		y -= dy;
	}
	
	public static void moveDownBy(int dy)
	{
		y += dy;
	}
}
