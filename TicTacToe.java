package tictactoegame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JApplet;


@SuppressWarnings("serial")
public class TicTacToe extends JApplet implements MouseListener{
	public int[] squares= new int[9];//array for each square of the board
	Font TitleText=new Font("Title",Font.BOLD,40);//All the fonts used in the text of the game
	Font NameText=new Font("Name",Font.ITALIC,25);
	Font PlayText=new Font("Play",Font.BOLD,35);
	Font QuitText=new Font("Quit",Font.ITALIC,20);
	public int turn = 1; //Used to determine the turn that game is on 
	public int test=0;//A universal variable to test random integers with
	public int option=0;//for the options of turn 2
	public int option2=0;//for the options of turns after that
	public int state=0;//to change to different images
	public boolean moved=false;//to check if the computer has moved
	public boolean win=false;//to check for computer's win
	public boolean tie=false;//to check for a tie
	Random r = new Random();//universal random generator
	//public File TicTacToe= new File("TicTacToeSave.txt");//creates a text file stored in the bin
	public int yourScore;//allows use of the your score
	public int compScore;//allows use of the computer's score
	public int tieScore;//allows use of tie scores
	public int start;//checks if it is the first time played
	
	/*public void createSaveFile() throws IOException//checks for and handles ioexception
	{			
		if(!TicTacToe.exists())//Checks if the file exists. If not create a new save
		{
			TicTacToe.createNewFile();//save creation
			String path = TicTacToe.getAbsolutePath();//gets the file path
			PrintWriter outside = new PrintWriter(new BufferedWriter(new FileWriter(path)));//uses the path to edit variables
			outside.println("0");//yourscore
			outside.println("0");//compscore
			outside.println("0");//tiescore
			outside.println("0");//starting story
			outside.close();
			//reads the code
			BufferedReader g = new BufferedReader(new FileReader(TicTacToe.getAbsolutePath()));
			yourScore=Integer.parseInt(g.readLine());
			compScore=Integer.parseInt(g.readLine());
			tieScore=Integer.parseInt(g.readLine());
			start=Integer.parseInt(g.readLine());
			g.close();
		}
		if(TicTacToe.exists())//sets the variables saved to ones usable in program
		{
			BufferedReader g = new BufferedReader(new FileReader(TicTacToe.getAbsolutePath()));
			yourScore=Integer.parseInt(g.readLine());
			compScore=Integer.parseInt(g.readLine());
			tieScore=Integer.parseInt(g.readLine());
			start=Integer.parseInt(g.readLine());
			g.close();
		}	





	}

	public void save() throws IOException//save method
	{
		PrintWriter outside = new PrintWriter(new BufferedWriter(new FileWriter(TicTacToe.getAbsolutePath())));
		outside.println(yourScore);
		outside.println(compScore);
		outside.println(tieScore);
		outside.println(start);
		outside.close();
	}*/
	public void init()//sets up the applet,mouse, and savefile
	{
		/*try {
			createSaveFile();
		} catch (IOException e) {
		}*/
		setSize(600,600);
		addMouseListener(this);
	}



	public void paint(Graphics page)//all the graphics
	{
		page.clearRect(0,0,600,600);//clear screen
		page.drawString(Integer.toString(turn),200,500);
		page.drawString(Integer.toString(option2),200,520);
		if(start==0)//checks to see if its the first time played
		{
			state=4;
			start=1;
			/*try {
				save();
			} catch (IOException e) {
			}*/
			repaint();
		}
		if(state==4)//story screen
		{
			page.setFont(QuitText);
			page.drawString("You and Your Computer Have been having Many battles in",20,20);
			page.drawString("Tic Tac Toe. It started off weak and stupid, but the ",20,50);
			page.drawString("computer has been adapting. It has found and ",20,80);
			page.drawString("remembered ways to Counter your every move.",20,110);
			page.drawString("After hundreds of games in, the computer seems to have",20,140);
			page.drawString("found your every weakness and has plans to exploit each",20,170);
			page.drawString("one. It has gotten to the point where the computer",20,200);
			page.drawString("seems unbeatable. How do you think you will fair now?",20,230);
			page.drawString("Is facing the computer hopeless, or not?",20,260);
		}
		
		if(state==0)//Menu Screen
		{
			turn=1;
			win=false;
			tie=false;

			for(int i=0;i<squares.length;i++) //clears board
			{
				squares[i]=0;
			}
			page.setColor(Color.CYAN);
			page.setFont(TitleText);//sets textfont of all text after
			page.drawString("Hopeless Tic Tac Toe",100,100);
			page.setFont(NameText);//sets textfont of all text after
			page.setColor(Color.GREEN);
			page.drawString("By : Jonathan Wu",180,150);
			page.setColor(Color.black);
			page.drawRoundRect(150,250,250,80,50,50);//draws the play button
			page.setFont(PlayText);//sets textfont of all text after
			page.drawString("PLAY",230,300);//text of the play button
			page.drawRoundRect(200,380,150,60,200,20);//draws the quit button
			page.setFont(QuitText);//sets textfont of all text after
			page.drawString("QUIT",250,410);//text of the quit button


		}

		if(state==1||state==2)
		{
			moved=false;//resets if the player has moved
			page.drawLine(0, 100, 300, 100);//draws board
			page.drawLine(0, 200, 300, 200);
			page.drawLine(100, 0, 100, 300);
			page.drawLine(200,0, 200, 300);
			page.setColor(Color.blue);
			if(squares[0]==1)//draws circle box 1
			{
				page.drawOval(0,0,100,100);
			}
			if(squares[1]==1)//draws circle box 2
			{
				page.drawOval(100,0,100,100);
			}
			if(squares[2]==1)//draws circle box 3
			{
				page.drawOval(200,0,100,100);
			}
			if(squares[3]==1)//draws circle box 4
			{
				page.drawOval(0,100,100,100);
			}
			if(squares[4]==1)//draws circle box 5
			{
				page.drawOval(100,100,100,100);
			}
			if(squares[5]==1)//draws circle box 6
			{
				page.drawOval(200,100,100,100);
			}
			if(squares[6]==1)//draws circle box 7
			{
				page.drawOval(0,200,100,100);
			}
			if(squares[7]==1)//draws circle box 8
			{
				page.drawOval(100,200,100,100);
			}
			if(squares[8]==1)//draws circle box 9
			{
				page.drawOval(200,200,100,100);
			}
			page.setColor(Color.red);//sets color to red
			if(squares[0]==2)//draws Square box 1
			{
				page.drawLine(0,0,100,100);
				page.drawLine(100,0,0,100);	
			}
			if(squares[1]==2)//draws Sqaure box 2
			{
				page.drawLine(100,0,200,100);
				page.drawLine(200,0,100,100);	
			}
			if(squares[2]==2)//draws Sqaure box 3
			{
				page.drawLine(200,0,300,100);
				page.drawLine(300,0,200,100);	
			}
			if(squares[3]==2)//draws Sqaure box 4
			{
				page.drawLine(0,100,100,200);
				page.drawLine(100,100,0,200);	
			}
			if(squares[4]==2)//draws Sqaure box 5
			{
				page.drawLine(100,100,200,200);
				page.drawLine(200,100,100,200);	
			}
			if(squares[5]==2)//draws Sqaure box 6
			{
				page.drawLine(200,100,300,200);
				page.drawLine(300,100,200,200);	
			}
			if(squares[6]==2)//draws Sqaure box 7
			{
				page.drawLine(0,200,100,300);
				page.drawLine(100,200,0,300);	
			}
			if(squares[7]==2)//draws Sqaure box 8
			{
				page.drawLine(100,200,200,300);
				page.drawLine(200,200,100,300);	
			}
			if(squares[8]==2)//draws Sqaure box 9
			{
				page.drawLine(200,200,300,300);
				page.drawLine(300,200,200,300);	
			}
			winTest();//test for winning
		}
		if(state==1)//game when player moves first 
		{
			page.setColor(Color.green);
			page.setFont(NameText);
			page.drawString("You First",350,100);
			if(win==true)
			{
				page.setColor(Color.red);
				page.setFont(PlayText);
				page.drawString("Computer Wins!",100,350);
			}
			if(tie==true)
			{
				page.setColor(Color.YELLOW);
				page.setFont(PlayText);
				page.drawString("Tie!",150,350);
			}
			page.setFont(QuitText);
			page.setColor(Color.blue);
			page.drawString("Computer Thinking...",100,400);
			turnTestFirst();//tests through the moves of the computer if player goes first
			page.clearRect(80,380,300,100);
		}
		if(state==2)//game when player moves second
		{
			page.setColor(Color.red);
			page.setFont(NameText);
			page.drawString("Computer First",350,100);
			if(win==true)
			{
				page.setColor(Color.red);
				page.setFont(PlayText);
				page.drawString("Computer Wins!",100,350);
			}
			if(tie==true)
			{
				page.setColor(Color.yellow);
				page.setFont(PlayText);
				page.drawString("Tie!",150,350);
			}
			page.setFont(QuitText);
			page.setColor(Color.blue);
			page.drawString("Computer Thinking...",100,400);
			turnTestSecond();//tests through the moves of the computer if player goes second
			page.clearRect(80,380,300,100);
		}

		if(state==3)//the loss screen
		{
			if(win==true)
			{
				page.setColor(Color.red);
				page.setFont(TitleText);
				page.drawString("You Lost",100,100);

			}
			if(tie==true)
			{
				page.setColor(Color.yellow);
				page.setFont(TitleText);
				page.drawString("You Tied",100,100);
			}//graphics
			page.setColor(Color.black);
			page.setFont(TitleText);
			page.drawString("SCORES",320,100);
			page.setFont(PlayText);
			page.setColor(Color.green);
			page.drawString(Integer.toString(yourScore),350,150);
			page.setColor(Color.yellow);
			page.drawString(Integer.toString(tieScore),400,150);
			page.setColor(Color.red);
			page.drawString(Integer.toString(compScore),450,150);
			page.setFont(QuitText);
			page.setColor(Color.black);
			page.drawString("Wins",340,180);
			page.drawString("Ties",390,180);
			page.drawString("Losses",440,180);
			page.drawRoundRect(80,200,200,80,20,100);
			page.setColor(Color.green);
			page.setFont(PlayText);
			page.drawString("Try Again?",95,250);
			page.setColor(Color.red);
			page.drawRoundRect(100,300,150,60,100,20);
			page.setFont(QuitText);
			page.drawString("It's Hopeless",118,333);

		}
	}
	public void turnTestFirst()//computer moves if player goes first
	{
		if(turn==2)
		{
			turn++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if(squares[4]==0)
			{
				squares[4]=1;
				option=0;
				repaint();
			}
			else if(squares[4]==2)
			{
				test=r.nextInt(4);
				if(test==0)
				{
					squares[0]=1;
					option=1;
					repaint();
				}
				else if(test==1)
				{
					squares[2]=1;
					option=2;
					repaint();
				}
				else if(test==2)
				{
					squares[6]=1;
					option=3;
					repaint();
				}
				else if(test==3)
				{
					squares[8]=1;
					option=4;
					repaint();
				}	
			}
		}
		else if(turn==4)
		{
			turn++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			defenseTest();
			if(moved==false)
			{
				if(option==0)
				{
					if(squares[3]==2&&squares[2]==2)
					{
						moved=true;
						squares[0]=1;
						repaint();
					}
					else if(squares[1]==2&&squares[6]==2)
					{
						moved=true;
						squares[0]=1;
						repaint();
					}
					else if(squares[3]==2&&squares[8]==2)
					{
						moved=true;
						squares[0]=1;
						repaint();
					}
					else if(squares[1]==2&&squares[8]==2)
					{
						moved=true;
						squares[0]=1;
						repaint();
					}
					else if(squares[0]==2&&squares[8]==2)
					{
						test=r.nextInt(4);
						if(test==0)
						{
							moved=true;
							squares[1]=1;
							repaint();
						}
						else if(test==1)
						{
							moved=true;
							squares[3]=1;
							repaint();
						}
						else if(test==2)
						{
							moved=true;
							squares[5]=1;
							repaint();
						}
						else if(test==3)
						{
							moved=true;
							squares[7]=1;
							repaint();
						}	
					}
					else if(squares[2]==2&&squares[6]==2)
					{
						test=r.nextInt(4);
						if(test==0)
						{
							moved=true;
							squares[1]=1;
							repaint();
						}
						else if(test==1)
						{
							moved=true;
							squares[3]=1;
							repaint();
						}
						else if(test==2)
						{
							moved=true;
							squares[5]=1;
							repaint();
						}
						else if(test==3)
						{
							moved=true;
							squares[7]=1;
							repaint();
						}	
					}

				}
				if(option==1)
				{
					if(squares[8]==2)
					{
						moved=true;
						squares[2]=1;
						repaint();
					}
				}
				if(option==2)
				{
					if(squares[6]==2)
					{
						moved=true;
						squares[0]=1;
						repaint();
					}
				}
				if(option==3)
				{
					if(squares[2]==2)
					{
						moved=true;
						squares[8]=1;
						repaint();
					}
				}
				if(option==4)
				{
					if(squares[0]==2)
					{
						moved=true;
						squares[6]=1;
						repaint();
					}
				}
				if(moved==false)
				{
					test=0;
					for(int i=0;i<squares.length;i++)
					{
						if(squares[i]==0)
						{
							test=i;
						}
					}
					squares[test]=1;
					repaint();



				}
			}
		}
		else if(turn==6)
		{
			turn++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			offenseTest();
			defenseTest();
			if(moved==false)
			{
				test=0;
				for(int i=0;i<squares.length;i++)
				{
					if(squares[i]==0)
					{
						test=i;
					}
				}
				squares[test]=1;
				repaint();

			}
		}
		else if(turn==8)
		{
			turn++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			offenseTest();
			defenseTest();
			if(moved==false)
			{
				test=0;
				for(int i=0;i<squares.length;i++)
				{
					if(squares[i]==0)
					{
						test=i;
					}
				}
				squares[test]=1;
				repaint();
			}
		}
	}
	public void turnTestSecond()//computer moves if player goes second
	{
		if(turn==1)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			squares[4]=2;
			turn++;
			repaint();
		}
		else if(turn==3)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			turn++;
			if(squares[0]==1)
			{
				squares[8]=2;
				option=0;
				repaint();
			}
			else if(squares[2]==1)
			{
				squares[6]=2;
				option=1;
				repaint();
			}
			else if(squares[6]==1)
			{
				squares[2]=2;
				option=2;
				repaint();
			}
			else if(squares[8]==1)
			{
				squares[0]=2;
				option=3;
				repaint();
			}
			else
			{
				test=r.nextInt(4);
				if(test==0)
				{
					squares[8]=2;
					option=0;
					repaint();
				}
				else if(test==1)
				{
					squares[6]=2;
					option=1;
					repaint();
				}
				else if(test==2)
				{
					squares[2]=2;
					option=2;
					repaint();
				}
				else if(test==3)
				{
					squares[0]=2;
					option=3;
					repaint();
				}
			}
		}
		else if(turn==5)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			offenseTest();
			turn++;
			if(moved==false)
			{
				if(option==0)
				{

					if(squares[1]==1)
					{
						squares[2]=2;
						option2=1;
						repaint();
					}
					else if(squares[2]==1)
					{
						squares[1]=2;
						option2=2;
						repaint();
					}
					else if(squares[3]==1)
					{
						squares[6]=2;
						option2=3;
						repaint();
					}
					else if(squares[5]==1)
					{
						squares[1]=2;
						option2=4;
						repaint();
					}
					else if(squares[6]==1)
					{
						squares[3]=2;
						option2=5;
						repaint();
					}

					else if(squares[7]==1)
					{
						squares[2]=2;
						option2=6;
						repaint();
					}
				}

				if(option==1)
				{
					if(squares[0]==1)
					{
						squares[1]=2;
						option2=7;
						repaint();
					}
					else if(squares[1]==1)
					{
						squares[0]=2;
						option2=8;
						repaint();
					}
					else if(squares[3]==1)
					{
						squares[8]=2;
						option2=9;
						repaint();
					}
					else if(squares[5]==1)
					{
						squares[8]=2;
						option2=10;
						repaint();
					}
					else if(squares[7]==1)
					{
						squares[0]=2;
						option2=11;
						repaint();
					}
					else if(squares[8]==1)
					{
						squares[5]=2;
						option2=12;
						repaint();
					}
				}
				if(option==2)
				{
					if(squares[0]==1)
					{
						squares[3]=2;
						option2=13;
						repaint();
					}
					if(squares[1]==1)
					{
						squares[8]=2;
						option2=14;
						repaint();
					}
					if(squares[3]==1)
					{
						squares[0]=2;
						option2=15;
						repaint();
					}
					if(squares[5]==1)
					{
						squares[0]=2;
						option2=16;
						repaint();
					}
					if(squares[7]==1)
					{
						squares[8]=2;
						option2=17;
						repaint();
					}
					if(squares[8]==1)
					{
						squares[7]=2;
						option2=18;
						repaint();
					}
				}
				if(option==3)
				{
					if(squares[1]==1)
					{
						squares[6]=2;
						option2=19;
						repaint();
					}
					if(squares[2]==1)
					{
						squares[5]=2;
						option2=20;
						repaint();
					}
					else if(squares[3]==1)
					{
						squares[2]=2;
						option2=21;
						repaint();
					}
					else if(squares[5]==1)
					{
						squares[2]=2;
						option2=22;
						repaint();
					}
					else if(squares[6]==1)
					{
						squares[7]=2;
						option2=23;
						repaint();
					}
					else if(squares[7]==1)
					{
						squares[6]=2;
						option2=24;
						repaint();
					}
				}
			}

		}
		else if(turn==7)
		{
			turn++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			offenseTest();
			if(moved==false)
			{
				if(option2==1)
				{
					if(squares[6]==1)
					{
						squares[5]=2;
					}	
					else if(squares[5]==1)
					{
						squares[6]=2;
					}
					else if(squares[5]!=1&&squares[6]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[5]=2;
						}
						else if(test==1)
							squares[6]=2;
					}

				}
				if(option2==2)
				{
					if(squares[7]!=1)
					{
						squares[7]=2;
					}
					else if(squares[7]==1)
					{
						squares[5]=2;
					}
				}
				if(option2==3)
				{
					if(squares[2]==1)
					{
						squares[7]=2;
					}	
					else if(squares[7]==1)
					{
						squares[2]=2;
					}
					else if(squares[2]!=1&&squares[7]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[2]=2;
						}
						else if(test==1)
							squares[7]=2;
					}
				}
				if(option2==4)
				{
					if(squares[2]==1)
					{
						squares[7]=2;
					}	
					else if(squares[7]==1)
					{
						squares[2]=2;
					}
					else if(squares[2]!=1&&squares[7]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[2]=2;
						}
						else if(test==1)
							squares[7]=2;
					}

				}
				if(option2==5)
				{
					if(squares[5]!=1)
					{
						squares[5]=2;
					}
					else if(squares[5]==1)
					{
						squares[7]=2;
					}
				}
				if(option2==6)
				{
					if(squares[5]==1)
					{
						squares[6]=2;
					}	
					else if(squares[6]==1)
					{
						squares[5]=2;
					}
					else if(squares[5]!=1&&squares[6]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[5]=2;
						}
						else if(test==1)
							squares[6]=2;
					}
				}
				if(option2==7)
				{
					if(squares[7]!=1)
					{
						squares[7]=2;
					}
					else if(squares[7]==1)
					{
						squares[3]=2;
					}
				}
				if(option2==8)
				{
					if(squares[3]==1)
					{
						squares[8]=2;
					}	
					else if(squares[8]==1)
					{
						squares[3]=2;
					}
					else if(squares[3]!=1&&squares[8]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[8]=2;
						}
						else if(test==1)
							squares[3]=2;
					}
				}
				if(option2==9)
				{
					if(squares[0]==1)
					{
						squares[7]=2;
					}	
					else if(squares[7]==1)
					{
						squares[0]=2;
					}
					else if(squares[0]!=1&&squares[7]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[5]=0;
						}
						else if(test==1)
							squares[6]=7;
					}
				}
				if(option2==10)
				{
					if(squares[0]==1)
					{
						squares[7]=2;
					}	
					else if(squares[7]==1)
					{
						squares[0]=2;
					}
					else if(squares[0]!=1&&squares[7]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[0]=2;
						}
						else if(test==1)
							squares[7]=2;
					}
				}
				if(option2==11)
				{
					if(squares[3]==1)
					{
						squares[8]=2;
					}	
					else if(squares[8]==1)
					{
						squares[3]=2;
					}
					else if(squares[3]!=1&&squares[8]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[3]=2;
						}
						else if(test==1)
							squares[8]=2;
					}
				}
				if(option2==12)
				{
					if(squares[3]!=1)
					{
						squares[3]=2;
					}
					else if(squares[3]==1)
					{
						squares[7]=2;
					}
				}
				if(option2==13)
				{
					if(squares[5]!=1)
					{
						squares[5]=2;
					}
					else if(squares[5]==1)
					{
						squares[1]=2;
					}
				}
				if(option2==14)
				{
					if(squares[0]==1)
					{
						squares[8]=2;
					}	
					else if(squares[8]==1)
					{
						squares[0]=2;
					}
					else if(squares[0]!=1&&squares[8]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[0]=2;
						}
						else if(test==1)
							squares[8]=2;
					}
				}
				if(option2==15)
				{

					if(squares[1]==1)
					{
						squares[8]=2;
					}	
					else if(squares[8]==1)
					{
						squares[1]=2;
					}
					else if(squares[1]!=1&&squares[8]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[1]=2;
						}
						else if(test==1)
							squares[8]=2;
					}
				}
				if(option2==16)
				{
					if(squares[1]==1)
					{
						squares[8]=2;
					}	
					else if(squares[8]==1)
					{
						squares[1]=2;
					}
					else if(squares[1]!=1&&squares[8]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[1]=2;
						}
						else if(test==1)
							squares[8]=2;
					}
				}
				if(option2==17)
				{
					if(squares[0]==1)
					{
						squares[5]=2;
					}	
					else if(squares[5]==1)
					{
						squares[0]=2;
					}
					else if(squares[0]!=1&&squares[5]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[0]=2;
						}
						else if(test==1)
							squares[5]=2;
					}
				}
				if(option2==18)
				{
					if(squares[1]!=1)
					{
						squares[1]=2;
					}
					else if(squares[1]==1)
					{
						squares[5]=2;
					}
				}
				if(option2==19)
				{
					if(squares[2]==1)
					{
						squares[3]=2;
					}	
					else if(squares[3]==1)
					{
						squares[2]=2;
					}
					else if(squares[2]!=1&&squares[3]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[2]=2;
						}
						else if(test==1)
							squares[3]=2;
					}
				}
				if(option2==20)
				{
					if(squares[3]!=1)
					{
						squares[3]=2;
					}
					else if(squares[3]==1)
					{
						squares[1]=2;
					}
				}
				if(option2==21)
				{
					if(squares[1]==1)
					{
						squares[6]=2;
					}	
					else if(squares[6]==1)
					{
						squares[1]=2;
					}
					else if(squares[1]!=1&&squares[6]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[1]=2;
						}
						else if(test==1)
							squares[6]=2;
					}
				}
				if(option2==22)
				{
					if(squares[1]==1)
					{
						squares[6]=2;
					}	
					else if(squares[6]==1)
					{
						squares[1]=2;
					}
					else if(squares[1]!=1&&squares[6]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[1]=2;
						}
						else if(test==1)
							squares[6]=2;
					}
				}
				if(option2==23)
				{
					if(squares[1]!=1)
					{
						squares[1]=2;
					}
					else if(squares[1]==1)
					{
						squares[3]=2;
					}
				}
				if(option2==24)
				{
					if(squares[2]==1)
					{
						squares[3]=2;
					}	
					else if(squares[3]==1)
					{
						squares[2]=2;
					}
					else if(squares[2]!=1&&squares[3]!=1)
					{
						test=r.nextInt(2);
						if(test==0)
						{
							squares[2]=2;
						}
						else if(test==1)
							squares[3]=2;
					}
				}
			}
			repaint();
		}
		else if(turn==9)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			turn++;
			for(int i=0;i<squares.length;i++)
			{
				if(squares[i]==0)
				{
					squares[i]=2;
				}
			}
			repaint();
		}
	}



	public void winTest()//algorithms to see who won
	{
		if(state==1)//test to see if the computer won. O's
		{
			if(squares[0]==1&&squares[1]==1&&squares[2]==1)
			{
				win=true;

			}
			else if(squares[3]==1&&squares[4]==1&&squares[5]==1)
			{
				win=true;

			}
			else if(squares[6]==1&&squares[7]==1&&squares[8]==1)
			{
				win=true;

			}
			else if(squares[0]==1&&squares[3]==1&&squares[6]==1)
			{
				win=true;

			}
			else if(squares[1]==1&&squares[4]==1&&squares[7]==1)
			{
				win=true;

			}
			else if(squares[2]==1&&squares[5]==1&&squares[8]==1)
			{
				win=true;

			}
			else if(squares[0]==1&&squares[4]==1&&squares[8]==1)
			{
				win=true;

			}
			else if(squares[2]==1&&squares[4]==1&&squares[6]==1)
			{
				win=true;

			}
		}
		if(state==2)
		{//test for computer win cases. X's
			if(squares[0]==2&&squares[1]==2&&squares[2]==2)
			{
				win=true;

			}
			else if(squares[3]==2&&squares[4]==2&&squares[5]==2)
			{
				win=true;

			}
			else if(squares[6]==2&&squares[7]==2&&squares[8]==2)
			{
				win=true;

			}
			else if(squares[0]==2&&squares[3]==2&&squares[6]==2)
			{
				win=true;

			}
			else if(squares[1]==2&&squares[4]==2&&squares[7]==2)
			{
				win=true;

			}
			else if(squares[2]==2&&squares[5]==2&&squares[8]==2)
			{
				win=true;

			}
			else if(squares[0]==2&&squares[4]==2&&squares[8]==2)
			{
				win=true;

			}
			else if(squares[2]==2&&squares[4]==2&&squares[6]==2)
			{
				win=true;

			}
		}
		if(win!=true&&turn==10)//tests for a tie case
		{
			tie=true;
		}

	}





	public void offenseTest()//checks for winning move and takes if possible
	{
		if(state==1)
		{
			if(squares[0]==1&&squares[1]==1&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[2]==1&&squares[1]==1&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==1&&squares[2]==1&&squares[1]==0)		
			{
				squares[1]=1;
				moved=true;
				repaint();
			}
			else if(squares[3]==1&&squares[4]==1&&squares[5]==0)		
			{
				squares[5]=1;
				moved=true;
				repaint();
			}
			else if(squares[5]==1&&squares[4]==1&&squares[3]==0)		
			{
				squares[3]=1;
				moved=true;
				repaint();
			}
			else if(squares[3]==1&&squares[5]==1&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[7]==1&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==1&&squares[7]==1&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[8]==1&&squares[7]==0)		
			{
				squares[7]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==1&&squares[3]==1&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[3]==1&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[0]==1&&squares[3]==0)		
			{
				squares[3]=1;
				moved=true;
				repaint();
			}
			else if(squares[1]==1&&squares[4]==1&&squares[7]==0)		
			{
				squares[7]=1;
				moved=true;
				repaint();
			}
			else if(squares[1]==1&&squares[7]==1&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
			else if(squares[7]==1&&squares[4]==1&&squares[1]==0)		
			{
				squares[1]=1;
				moved=true;
				repaint();
			}
			else if(squares[2]==1&&squares[5]==1&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==1&&squares[5]==1&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==1&&squares[2]==1&&squares[5]==0)		
			{
				squares[5]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==1&&squares[4]==1&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==1&&squares[4]==1&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==1&&squares[8]==1&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
			else if(squares[2]==1&&squares[4]==1&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[4]==1&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==1&&squares[2]==1&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
		}
		if(state==2)
		{
			if(squares[0]==2&&squares[1]==2&&squares[2]==0)		
			{
				squares[2]=2;
				moved=true;
				repaint();
			}
			else if(squares[2]==2&&squares[1]==2&&squares[0]==0)		
			{
				squares[0]=2;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[2]==2&&squares[1]==0)		
			{
				squares[1]=2;
				moved=true;
				repaint();
			}
			else if(squares[3]==2&&squares[4]==2&&squares[5]==0)		
			{
				squares[5]=2;
				moved=true;
				repaint();
			}
			else if(squares[5]==2&&squares[4]==2&&squares[3]==0)		
			{
				squares[3]=2;
				moved=true;
				repaint();
			}
			else if(squares[3]==2&&squares[5]==2&&squares[4]==0)		
			{
				squares[4]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[7]==2&&squares[8]==0)		
			{
				squares[8]=2;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[7]==2&&squares[6]==0)		
			{
				squares[6]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[8]==2&&squares[7]==0)		
			{
				squares[7]=2;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[3]==2&&squares[6]==0)		
			{
				squares[6]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[3]==2&&squares[0]==0)		
			{
				squares[0]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[0]==2&&squares[3]==0)		
			{
				squares[3]=2;
				moved=true;
				repaint();
			}
			else if(squares[1]==2&&squares[4]==2&&squares[7]==0)		
			{
				squares[7]=2;
				moved=true;
				repaint();
			}
			else if(squares[1]==2&&squares[7]==2&&squares[4]==0)		
			{
				squares[4]=2;
				moved=true;
				repaint();
			}
			else if(squares[7]==2&&squares[4]==2&&squares[1]==0)		
			{
				squares[1]=2;
				moved=true;
				repaint();
			}
			else if(squares[2]==2&&squares[5]==2&&squares[8]==0)		
			{
				squares[8]=2;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[5]==2&&squares[2]==0)		
			{
				squares[2]=2;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[2]==2&&squares[5]==0)		
			{
				squares[5]=2;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[4]==2&&squares[8]==0)		
			{
				squares[8]=2;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[4]==2&&squares[0]==0)		
			{
				squares[0]=2;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[8]==2&&squares[4]==0)		
			{
				squares[4]=2;
				moved=true;
				repaint();
			}
			else if(squares[2]==2&&squares[4]==2&&squares[6]==0)		
			{
				squares[6]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[4]==2&&squares[2]==0)		
			{
				squares[2]=2;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[2]==2&&squares[4]==0)		
			{
				squares[4]=2;
				moved=true;
				repaint();
			}
		}
	}
	public void defenseTest()//checks for losing move and blocks
	{
		if(moved==false)
		{
			if(squares[0]==2&&squares[1]==2&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[2]==2&&squares[1]==2&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[2]==2&&squares[1]==0)		
			{
				squares[1]=1;
				moved=true;
				repaint();
			}
			else if(squares[3]==2&&squares[4]==2&&squares[5]==0)		
			{
				squares[5]=1;
				moved=true;
				repaint();
			}
			else if(squares[5]==2&&squares[4]==2&&squares[3]==0)		
			{
				squares[3]=1;
				moved=true;
				repaint();
			}
			else if(squares[3]==2&&squares[5]==2&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[7]==2&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[7]==2&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[8]==2&&squares[7]==0)		
			{
				squares[7]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[3]==2&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[3]==2&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[0]==2&&squares[3]==0)		
			{
				squares[3]=1;
				moved=true;
				repaint();
			}
			else if(squares[1]==2&&squares[4]==2&&squares[7]==0)		
			{
				squares[7]=1;
				moved=true;
				repaint();
			}
			else if(squares[1]==2&&squares[7]==2&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
			else if(squares[7]==2&&squares[4]==2&&squares[1]==0)		
			{
				squares[1]=1;
				moved=true;
				repaint();
			}
			else if(squares[2]==2&&squares[5]==2&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[5]==2&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[2]==2&&squares[5]==0)		
			{
				squares[5]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[4]==2&&squares[8]==0)		
			{
				squares[8]=1;
				moved=true;
				repaint();
			}
			else if(squares[8]==2&&squares[4]==2&&squares[0]==0)		
			{
				squares[0]=1;
				moved=true;
				repaint();
			}
			else if(squares[0]==2&&squares[8]==2&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
			}
			else if(squares[2]==2&&squares[4]==2&&squares[6]==0)		
			{
				squares[6]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[4]==2&&squares[2]==0)		
			{
				squares[2]=1;
				moved=true;
				repaint();
			}
			else if(squares[6]==2&&squares[2]==2&&squares[4]==0)		
			{
				squares[4]=1;
				moved=true;
				repaint();
			}
		}
	}

	public void mousePressed(MouseEvent arg0) {


		if(state==0)
		{	
			//activates the play button
			if((int) (arg0.getPoint().x) > 150 && arg0.getPoint().x<400 && arg0.getPoint().y > 250 &&arg0.getPoint().y<330)
			{
				test=r.nextInt(2);
				if(test==0)
				{
					state=1;
				}
				if(test==1)
				{
					state=2;
				}
				repaint();
			}//closes the code
			if((int) (arg0.getPoint().x) > 200 && arg0.getPoint().x<350 && arg0.getPoint().y > 380 &&arg0.getPoint().y<440)
			{
				System.exit(0);
			}
		}


		else if(state==1)
		{
			//adds to the score of the game
			if(win==true||tie==true)
			{
				if(win==true)
				{
					compScore++;

				}
				if(tie==true)
				{
					tieScore++;
				}
				/*try {
					save();
				} catch (IOException e) {
				}*/
				if((int) (arg0.getPoint().x) < 600 && arg0.getPoint().x > 0 && arg0.getPoint().y < 600 && arg0.getPoint().y > 0 )
				{
					state=3;
					repaint();
				}
			}
			if((turn==1||turn==3||turn==5||turn==7||turn==9)&&win==false)
			{	//does the moves according to the box area pressed as human
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().y < 100&&squares[0]==0)
				{
					turn++;
					squares[0]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 100&&squares[1]==0)
				{
					turn++;
					squares[1]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 100&&squares[2]==0)
				{
					turn++;
					squares[2]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().x > 0 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[3]==0)
				{
					turn++;
					squares[3]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[4]==0)
				{
					turn++;
					squares[4]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[5]==0)
				{
					turn++;
					squares[5]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().x > 0 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[6]==0)
				{
					turn++;
					squares[6]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[7]==0)
				{
					turn++;
					squares[7]=2;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[8]==0)
				{
					turn++;
					squares[8]=2;
					repaint();
				}
			}
		}
		else if(state==2)
		{
			if(win==true||tie==true)
			{
				//adds to the scores of the game
				if(win==true)
				{
					compScore++;

				}
				if(tie==true)
				{
					tieScore++;
				}
				/*try {
					save();
				} catch (IOException e) {
				}*/
				if((int) (arg0.getPoint().x) < 600 && arg0.getPoint().x > 0 && arg0.getPoint().y < 600 && arg0.getPoint().y > 0 )
				{
					state=3;
					repaint();
				}
			}
			if((turn==2||turn==4||turn==6||turn==8)&&win==false)
			{	//does the moves according to the box area pressed as human
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().y < 100&&squares[0]==0)
				{
					turn++;
					squares[0]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 100&&squares[1]==0)
				{
					turn++;
					squares[1]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 100&&squares[2]==0)
				{
					turn++;
					squares[2]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().x > 0 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[3]==0)
				{
					turn++;
					squares[3]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[4]==0)
				{
					turn++;
					squares[4]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 200 && arg0.getPoint().y > 100&&squares[5]==0)
				{
					turn++;
					squares[5]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 100 && arg0.getPoint().x > 0 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[6]==0)
				{
					turn++;
					squares[6]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 200 && arg0.getPoint().x > 100 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[7]==0)
				{
					turn++;
					squares[7]=1;
					repaint();
				}
				if((int) (arg0.getPoint().x) < 300 && arg0.getPoint().x > 200 && arg0.getPoint().y < 300 && arg0.getPoint().y > 200&&squares[8]==0)
				{
					turn++;
					squares[8]=1;
					repaint();
				}
			}
		}
		else if(state==3)//does action when in loss screen
		{
			if((int) (arg0.getPoint().x) >80 && arg0.getPoint().x < 280 && arg0.getPoint().y > 200 && arg0.getPoint().y < 280)
			{
				state=0;
				repaint();
			}
			if((int) (arg0.getPoint().x) >100 && arg0.getPoint().x < 250 && arg0.getPoint().y > 300&&arg0.getPoint().y < 360)
			{
				System.exit(0);
			}
		}
		else if(state==4)//moves to game title after clicked in story screen
		{
			if((int) (arg0.getPoint().x) < 600 && arg0.getPoint().x > 0 && arg0.getPoint().y < 600 && arg0.getPoint().y > 0 )
			{
				state=0;
				repaint();
			}
		}
	}


	public void mouseClicked(MouseEvent arg0) {

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}





	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}




}






