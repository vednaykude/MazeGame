import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Monster extends MazeElement
{
	public Monster(Location loc, int size,String imgString)
	{
		super(loc, size, imgString);
	}
	public void move(char[][] maze)
	{
		Runnable helloRunnable = new Runnable() {
		    public void run() {
				int r = getLoc().getR();
				int c = getLoc().getC();
		        if(r+1 < maze.length && maze[r+1][c] == ' '){//col exists and open
					getLoc().incR(1);
				}
				if(r-1 < maze.length && maze[r-1][c] == ' '){//col exists and open
					getLoc().incR(-1);
				}
		    }
		};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 2, TimeUnit.SECONDS);

	}
	public static void main(String[] args){

	}
}