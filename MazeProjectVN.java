import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class MazeProjectVN extends JPanel implements KeyListener, ActionListener
{
	private JFrame frame;
	private int size = 30, width = 1500, height = 1000;
	private char[][] maze;
	private Timer t;
	private MazeElement finish;
	private Explorer explorer;
	private Monster monster;
	private int count = 0;
	private int count2 = 0;
	private String winner = "";

	public MazeProjectVN(){
		//Maze variables
		setBoard("maze0.txt");
		frame=new JFrame("A-Mazing Program");
		frame.setSize(width,height);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		t = new Timer(500, this);  // will trigger actionPerformed every 500 ms
		t.start();
	}

	// All Graphics handled in this method.  Don't do calculations here
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		for(int r=0;r<maze.length;r++){
			for(int c=0;c<maze[0].length;c++){

				g2.setColor(Color.GRAY);
				if (maze[r][c]=='#')
					g2.fillRect(c*size+size,r*size+size,size,size); //Wall
				else
					g2.drawRect(c*size+size,r*size+size,size,size);  //Open

				//Paint MazeElements
				Location here = new Location(r,c);
				if (here.equals(explorer.getLoc()))
					g2.drawImage(explorer.getImg(), c*size+size,r*size+size,size,size, Color.WHITE, this);
				if (here.equals(finish.getLoc()))
				    g2.drawImage(finish.getImg(), c*size+size,r*size+size,size,size, Color.WHITE, this);

				if (here.equals(monster.getLoc()))
					g2.drawImage(monster.getImg(), c*size+size,r*size+size,size,size, Color.WHITE, this);
				}
		}

		// Display at bottom of page
		int hor = size;
		int vert = maze.length*size+ 2*size;
		g2.setFont(new Font("Arial",Font.BOLD,20));
		g2.setColor(Color.PINK);
		if (!(explorer.getLoc().equals(finish.getLoc())))
			g2.drawString(explorer.getSteps(),hor,vert);
			if(count2 > 0){
				g2.drawString("Finished Level: " + count + ", " + winner + " steps" ,hor,vert + 20);
				count2 = 0;
			}
		else if (explorer.getLoc().equals(finish.getLoc())){
			if(count <= 2){
				count++;
				count2++;
			}
			winner = explorer.getSteps();
			if(count == 1)
				setBoard("maze1.txt");
			if(count == 2)
				setBoard("maze2.txt");
			repaint();
		}
	}


	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		// Call explorer method
		explorer.move(e.getKeyCode(), maze);
		repaint();
	}

	/*** empty methods needed for interfaces **/
	public void keyReleased(KeyEvent e){

		}
	public void keyTyped(KeyEvent e){}
	public void actionPerformed(ActionEvent e) {}

	public void setBoard(String fileName){
		File file = new File(fileName);
		    try{
				ArrayList<String[]> a = new ArrayList<String[]>();
				int column = 1;
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String text = "";
				while ((text = reader.readLine()) != null){
				a.add(text.split("(?!^)"));
		    }
			int row = a.size();
			int col = a.get(0).length;
			for(int i = 1; i < a.size(); i++){
				if(col != a.get(i).length)
				{
					throw new IOException("**NOT ALL LINES THE SAME LENGTH**");
				}
			}
			char[][] temp = new char[row][col];
			for(int r = 0; r<row; r++){
				for(int c = 0; c<col; c++){
					if((a.get(r)[c]).charAt(0) != ' ' &&(a.get(r)[c]).charAt(0) != '#'){
						if((a.get(r)[c]).charAt(0) =='F')
					   		finish = new MazeElement(new Location(r,c),size,"finish.png");
					   	if((a.get(r)[c]).charAt(0) =='E')
					   		explorer = new Explorer(new Location(r,c),size);
					   	if((a.get(r)[c]).charAt(0) =='M')
					   		monster = new Monster(new Location(r,c),size, "monster.png");
					temp[r][c] = ' ';
				}else
					temp[r][c] = (a.get(r)[c]).charAt(0);
				}
			}
			maze = temp;
			}catch (Exception e) {
				System.out.println(e);
			}
	}

	public static void main(String[] args){
		MazeProjectVN app=new MazeProjectVN();
	}
}