import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Explorer extends MazeElement
{

	private int dir;
	private int steps;
	public static final String[] IMG_NAMES = {"runnerN.png", "runnerE.png", "runnerS.png", "runnerW.png"}; //add one for each direction
	private BufferedImage[] images;

	public Explorer(Location loc, int size)
	{
		super(loc, size);
		dir = 1; // face east by default
		steps = 0;
		images = new BufferedImage[IMG_NAMES.length];

		System.out.println("Done super");

		//Load files into the BufferedImage array based on the file names in IMG_NAMES
		for(int i = 0; i < IMG_NAMES.length; i++)
			try {  // Loads a placeholder image
				images[i] = ImageIO.read(new File(IMG_NAMES[i]));
				System.out.println("Loaded "+IMG_NAMES[i]);
				} catch (IOException e) {
				System.out.println("Image not loaded");
			}


	}

	//@Override
	public BufferedImage getImg()
	{
		return images[dir];
	}
	/*@Override
	public boolean intersects(Explorer other){

		return this.loc.equals(other.loc);
	}
	@Override*/
	public void move(int key, char[][] maze) {
		if(key == 39){
			if (dir < 3)
				dir++;
			else
				dir = 0;
		}
		if(key == 37){
			if (dir > 0)
				dir--;
			else
				dir = 3;
		}
		if(key == 38){ //up arrow move forward
			int r = getLoc().getR();
			int c = getLoc().getC();
				if(dir == 1){
					if(c+1 < maze[r].length && maze[r][c+1] == ' '){//col exists and open
						getLoc().incC(1);
						steps++;
					}
			}
				if(dir == 2){
					if(r+1 < maze.length && maze[r+1][c] == ' '){//col exists and open
						getLoc().incR(1);
						steps++;
					}
			}
			if(dir == 0){
				if(r-1 < maze.length && maze[r-1][c] == ' '){//col exists and open
					getLoc().incR(-1);
					steps++;
				}
			}
			if(dir == 3){
				if(c-1 >= 0 && maze[r][c-1] == ' '){//col exists and open
					getLoc().incC(-1);
					steps++;
				}
			}
		}
		if(key == 40){ //down arrow mve back
			int r = getLoc().getR();
			int c = getLoc().getC();
				if(dir == 1){
					if(c-1 >= 0 && maze[r][c-1] == ' '){//col exists and open
						getLoc().incC(-1);
						steps++;
					}
				}
				if(dir == 2){
					if(r-1 >= 0 && maze[r-1][c] == ' '){//col exists and open
						getLoc().incR(-1);
						steps++;
					}
			}
				if(dir == 3){
					if(c+1 < maze[r].length && maze[r][c+1] == ' '){//col exists and open
						getLoc().incC(1);
						steps++;
					}
				}
				if(dir == 0){
					if(r+1 >= 0 && maze[r+1][c] == ' '){//col exists and open
						getLoc().incR(1);
						steps++;
					}
			}

		}
	} // DOES NOTHING BY DEFAULT COMPLETE FOR MOVING ELEMENTS
	public String getSteps(){
		return "Steps taken: " + steps;
	}
	public static void main(String[] args){

	}

}