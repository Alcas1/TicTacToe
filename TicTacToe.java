package tictactoegame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JApplet;
@SuppressWarnings("serial")
public class TicTacToe extends JApplet implements MouseListener{
	public int[] box= new int[9];//array for each square of the board
	Font TitleText=new Font("Title",Font.BOLD,40);//All the fonts used in the text of the game
	Font NameText=new Font("Name",Font.ITALIC,25);
	Font PlayText=new Font("Play",Font.BOLD,35);
	Font QuitText=new Font("Quit",Font.ITALIC,20);
	public int turn=1; 
	public int randomTest=0;//A universal variable to test random integers with
	public int option=0;//for the options of turn 2
	public int option2=0;//for the options of turns after that
	public int gameState=0;
	public boolean moved=false;//to check if the computer has moved
	public boolean win=false;//to check for computer's win
	public boolean tie=false;//to check for a tie
	Random r = new Random();//universal random generator
	public File ticTacToeSave= new File("TicTacToeSave.txt");//creates a text file stored in the bin
	public int yourScore;//allows use of the your score
	public int compScore;//allows use of the computer's score
	public int tieScore;//allows use of tie scores
	public boolean firstGame;//checks if it is the first time played
	public void createSaveFile() throws IOException//checks for and handles ioexception
	{			
		if(!ticTacToeSave.exists())//Checks if the file exists. If not create a new save
		{
			ticTacToeSave.createNewFile();//save creation
			String path = ticTacToeSave.getAbsolutePath();//gets the file path
			PrintWriter outside = new PrintWriter(new BufferedWriter(new FileWriter(path)));//uses the path to edit variables
			outside.println("0");//yourscore
			outside.println("0");//compscore
			outside.println("0");//tiescore
			outside.println("true");//starting story
			outside.close();
			//reads the code
			BufferedReader g = new BufferedReader(new FileReader(ticTacToeSave.getAbsolutePath()));
			yourScore=Integer.parseInt(g.readLine());
			compScore=Integer.parseInt(g.readLine());
			tieScore=Integer.parseInt(g.readLine());
			firstGame=Boolean.parseBoolean(g.readLine());
			g.close();
		}
		if(ticTacToeSave.exists())//sets the variables saved to ones usable in program
		{
			BufferedReader g = new BufferedReader(new FileReader(ticTacToeSave.getAbsolutePath()));
			yourScore=Integer.parseInt(g.readLine());
			compScore=Integer.parseInt(g.readLine());
			tieScore=Integer.parseInt(g.readLine());
			firstGame=Boolean.parseBoolean(g.readLine());
			g.close();
		}	
	}
	public void save() throws IOException
	{
		PrintWriter outside = new PrintWriter(new BufferedWriter(new FileWriter(ticTacToeSave.getAbsolutePath())));
		outside.println(yourScore);
		outside.println(compScore);
		outside.println(tieScore);
		outside.println(firstGame);
		outside.close();
	}
	public void saveRun()
	{
		try {
			save();
		} catch (IOException e) {
		}
	}
	public void init()//sets up the applet,mouse, and savefile
	{
		try {
			createSaveFile();
		} catch (IOException e) {
		}
		setSize(600,600);
		addMouseListener(this);
	}
	public void paint(Graphics page)//all the graphics
	{

		page.clearRect(0,0,600,600);//clear screen
		page.drawString(Integer.toString(turn),200,500);
		page.drawString(Integer.toString(option2),200,520);
		if(firstGame)//checks to see if its the first time played
		{
			gameState=4;
			firstGame=false;
			saveRun();
			repaint();
		}
		if(gameState==4)//story screen
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
		if(gameState==0)//Menu Screen
		{
			turn=1;
			win=false;
			tie=false;
			for(int i=0;i<box.length;i++) //clears board
			{
				box[i]=0;
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
		if(gameState==1||gameState==2)
		{
			moved=false;//resets if the player has moved
			page.drawLine(0, 100, 300, 100);//draws board
			page.drawLine(0, 200, 300, 200);
			page.drawLine(100, 0, 100, 300);
			page.drawLine(200,0, 200, 300);
			page.setColor(Color.blue);
			if(box[0]==1)//draws circle box 1
			{
				page.drawOval(0,0,100,100);
			}
			if(box[1]==1)//draws circle box 2
			{
				page.drawOval(100,0,100,100);
			}
			if(box[2]==1)//draws circle box 3
			{
				page.drawOval(200,0,100,100);
			}
			if(box[3]==1)//draws circle box 4
			{
				page.drawOval(0,100,100,100);
			}
			if(box[4]==1)//draws circle box 5
			{
				page.drawOval(100,100,100,100);
			}
			if(box[5]==1)//draws circle box 6
			{
				page.drawOval(200,100,100,100);
			}
			if(box[6]==1)//draws circle box 7
			{
				page.drawOval(0,200,100,100);
			}
			if(box[7]==1)//draws circle box 8
			{
				page.drawOval(100,200,100,100);
			}
			if(box[8]==1)//draws circle box 9
			{
				page.drawOval(200,200,100,100);
			}
			page.setColor(Color.red);//sets color to red
			if(box[0]==2)//draws Square box 1
			{
				page.drawLine(0,0,100,100);
				page.drawLine(100,0,0,100);	
			}
			if(box[1]==2)//draws Sqaure box 2
			{
				page.drawLine(100,0,200,100);
				page.drawLine(200,0,100,100);	
			}
			if(box[2]==2)//draws Sqaure box 3
			{
				page.drawLine(200,0,300,100);
				page.drawLine(300,0,200,100);	
			}
			if(box[3]==2)//draws Sqaure box 4
			{
				page.drawLine(0,100,100,200);
				page.drawLine(100,100,0,200);	
			}
			if(box[4]==2)//draws Sqaure box 5
			{
				page.drawLine(100,100,200,200);
				page.drawLine(200,100,100,200);	
			}
			if(box[5]==2)//draws Sqaure box 6
			{
				page.drawLine(200,100,300,200);
				page.drawLine(300,100,200,200);	
			}
			if(box[6]==2)//draws Sqaure box 7
			{
				page.drawLine(0,200,100,300);
				page.drawLine(100,200,0,300);	
			}
			if(box[7]==2)//draws Sqaure box 8
			{
				page.drawLine(100,200,200,300);
				page.drawLine(200,200,100,300);	
			}
			if(box[8]==2)//draws Sqaure box 9
			{
				page.drawLine(200,200,300,300);
				page.drawLine(300,200,200,300);	
			}
			winTest();//test for winning
		}
		if(gameState==1)//game when player moves first 
		{
			page.setColor(Color.green);
			page.setFont(NameText);
			page.drawString("You First",350,100);
			if(win)
			{
				page.setColor(Color.red);
				page.setFont(PlayText);
				page.drawString("Computer Wins!",100,350);
			}
			if(tie)
			{
				page.setColor(Color.YELLOW);
				page.setFont(PlayText);
				page.drawString("Tie!",150,350);
			}
			page.setFont(QuitText);
			page.setColor(Color.blue);
			page.drawString("Computer Thinking...",100,400);
			//tests through the moves of the computer if player goes first

			if(turn==2)
			{
				turn2();
			}
			if(turn==4)
			{
				turn4();
			}
			if(turn==6)
			{
				turn6();	
			}
			if(turn==8)
			{
				turn8();
			}
			page.clearRect(80,380,300,100);
		}
		if(gameState==2)//game when player moves second
		{
			page.setColor(Color.red);
			page.setFont(NameText);
			page.drawString("Computer First",350,100);
			if(win)
			{
				page.setColor(Color.red);
				page.setFont(PlayText);
				page.drawString("Computer Wins!",100,350);
			}
			if(tie)
			{
				page.setColor(Color.yellow);
				page.setFont(PlayText);
				page.drawString("Tie!",150,350);
			}
			page.setFont(QuitText);
			page.setColor(Color.blue);
			page.drawString("Computer Thinking...",100,400);
			//tests through the moves of the computer if player goes second
			if(turn==1)
			{
				turn1();
			}
			if(turn==3)
			{
				turn3();
			}
			if(turn==5)
			{
				turn5();
			}
			if(turn==7)
			{
				turn7();
			}
			if(turn==9)
			{
				turn9();
			}
			page.clearRect(80,380,300,100);
		}
		if(gameState==3)//the loss screen
		{
			if(win)
			{
				page.setColor(Color.red);
				page.setFont(TitleText);
				page.drawString("You Lost",100,100);
			}
			if(tie)
			{
				page.setColor(Color.yellow);
				page.setFont(TitleText);
				page.drawString("You Tied",100,100);
			}//graphics
			page.setColor(Color.black);
			page.setFont(QuitText);
			page.drawString("Wins",340,180);
			page.drawString("Ties",390,180);
			page.drawString("Losses",440,180);
			page.drawRoundRect(80,200,200,80,20,100);
			page.setFont(TitleText);
			page.drawString("SCORES",320,100);
			page.setFont(PlayText);
			page.setColor(Color.green);
			page.drawString(Integer.toString(yourScore),350,150);
			page.setFont(PlayText);
			page.drawString("Try Again?",95,250);
			page.setColor(Color.yellow);
			page.drawString(Integer.toString(tieScore),400,150);
			page.setColor(Color.red);
			page.drawString(Integer.toString(compScore),450,150);
			page.drawRoundRect(100,300,150,60,100,20);
			page.setFont(QuitText);
			page.drawString("It's Hopeless",118,333);
		}
	}
	public void turn2()
	{
		turn++;
		sleep(1000);
		if(box[4]==0)
		{
			box[4]=1;
			option=0;
			repaint();
		}
		else if(box[4]==2)
		{
			randomTest=r.nextInt(4);
			if(randomTest==0)
			{
				box[0]=1;
				option=1;
				repaint();
			}
			else if(randomTest==1)
			{
				box[2]=1;
				option=2;
				repaint();
			}
			else if(randomTest==2)
			{
				box[6]=1;
				option=3;
				repaint();
			}
			else if(randomTest==3)
			{
				box[8]=1;
				option=4;
				repaint();
			}	
		}
	}
	public void turn4()
	{
		turn++;
		sleep(1000);
		defenseTest();
		if(moved==false)
		{
			if(option==0)
			{
				if(box[3]==2&&box[2]==2)
				{
					box[0]=1;
					moveSet();
				}
				else if(box[1]==2&&box[6]==2)
				{
					box[0]=1;
					moveSet();
				}
				else if(box[3]==2&&box[8]==2)
				{
					box[0]=1;
					moveSet();
				}
				else if(box[1]==2&&box[8]==2)
				{
					box[0]=1;
					moveSet();
				}
				else if(box[0]==2&&box[8]==2)
				{
					randomTest=r.nextInt(4);
					if(randomTest==0)
					{
						box[1]=1;
						moveSet();
					}
					else if(randomTest==1)
					{						
						box[3]=1;
						moveSet();
					}
					else if(randomTest==2)
					{
						box[5]=1;
						repaint();
					}
					else if(randomTest==3)
					{					
						box[7]=1;
						moveSet();
					}	
				}
				else if(box[2]==2&&box[6]==2)
				{
					randomTest=r.nextInt(4);
					if(randomTest==0)
					{
						box[1]=1;
						moveSet();
					}
					else if(randomTest==1)
					{					
						box[3]=1;
						moveSet();
					}
					else if(randomTest==2)
					{
						box[5]=1;
						moveSet();
					}
					else if(randomTest==3)
					{
						
						box[7]=1;
						moveSet();
					}	
				}
				else if(box[1]==2&&box[3]==2)
				{
				box[0]=1;
				moveSet();
				}
				else if(box[1]==2&&box[5]==2)
				{
				box[2]=1;
				moveSet();
				}
				else if(box[3]==2&&box[7]==2)
				{
				box[6]=1;
				moveSet();
				}
				else if(box[7]==2&&box[5]==2)
				{
				box[8]=1;
				moveSet();
				}
			}
			if(option==1)
			{
				if(box[8]==2)
				{
					box[2]=1;
					moveSet();
				}
			}
			if(option==2)
			{
				if(box[6]==2)
				{
					box[0]=1;
					moveSet();
				}
			}
			if(option==3)
			{
				if(box[2]==2)
				{
					box[8]=1;
					moveSet();
				}
			}
			if(option==4)
			{
				if(box[0]==2)
				{					
					box[6]=1;
					moveSet();
				}
			}
			if(moved==false)
			{
				randomTest=0;
				for(int i=0;i<box.length;i++)
				{
					if(box[i]==0)
					{
						randomTest=i;
					}
				}
				box[randomTest]=1;
				repaint();
			}
		}
	}
	public void turn6()
	{
		turn++;
		sleep(1000);
		offenseTest();
		defenseTest();
		if(moved==false)
		{
			randomTest=0;
			for(int i=0;i<box.length;i++)
			{
				if(box[i]==0)
				{
					randomTest=i;
				}
			}
			box[randomTest]=1;
			repaint();
		}
	}
	public void turn8()
	{
		turn++;
		sleep(1000);
		offenseTest();
		defenseTest();
		if(moved==false)
		{
			randomTest=0;
			for(int i=0;i<box.length;i++)
			{
				if(box[i]==0)
				{
					randomTest=i;
				}
			}
			box[randomTest]=1;
			repaint();
		}
	}
	public void turn1()
	{
		sleep(1000);
		box[4]=2;
		turnSet();
	}
	public void turn3()
	{
		sleep(1000);
		turn++;
		if(box[0]==1)
		{
			box[8]=2;
			option=0;
			repaint();
		}
		else if(box[2]==1)
		{
			box[6]=2;
			option=1;
			repaint();
		}
		else if(box[6]==1)
		{
			box[2]=2;
			option=2;
			repaint();
		}
		else if(box[8]==1)
		{
			box[0]=2;
			option=3;
			repaint();
		}
		else
		{
			randomTest=r.nextInt(4);
			if(randomTest==0)
			{
				box[8]=2;
				option=0;
				repaint();
			}
			else if(randomTest==1)
			{
				box[6]=2;
				option=1;
				repaint();
			}
			else if(randomTest==2)
			{
				box[2]=2;
				option=2;
				repaint();
			}
			else if(randomTest==3)
			{
				box[0]=2;
				option=3;
				repaint();
			}
		}
	}
	public void turn5()
	{
		sleep(1000);
		offenseTest();
		turn++;
		if(moved==false)
		{
			if(option==0)
			{

				if(box[1]==1)
				{
					box[2]=2;
					option2=1;
					repaint();
				}
				else if(box[2]==1)
				{
					box[1]=2;
					option2=2;
					repaint();
				}
				else if(box[3]==1)
				{
					box[6]=2;
					option2=3;
					repaint();
				}
				else if(box[5]==1)
				{
					box[1]=2;
					option2=4;
					repaint();
				}
				else if(box[6]==1)
				{
					box[3]=2;
					option2=5;
					repaint();
				}

				else if(box[7]==1)
				{
					box[2]=2;
					option2=6;
					repaint();
				}
			}
			if(option==1)
			{
				if(box[0]==1)
				{
					box[1]=2;
					option2=7;
					repaint();
				}
				else if(box[1]==1)
				{
					box[0]=2;
					option2=8;
					repaint();
				}
				else if(box[3]==1)
				{
					box[8]=2;
					option2=9;
					repaint();
				}
				else if(box[5]==1)
				{
					box[8]=2;
					option2=10;
					repaint();
				}
				else if(box[7]==1)
				{
					box[0]=2;
					option2=11;
					repaint();
				}
				else if(box[8]==1)
				{
					box[5]=2;
					option2=12;
					repaint();
				}
			}
			if(option==2)
			{
				if(box[0]==1)
				{
					box[3]=2;
					option2=13;
					repaint();
				}
				if(box[1]==1)
				{
					box[8]=2;
					option2=14;
					repaint();
				}
				if(box[3]==1)
				{
					box[0]=2;
					option2=15;
					repaint();
				}
				if(box[5]==1)
				{
					box[0]=2;
					option2=16;
					repaint();
				}
				if(box[7]==1)
				{
					box[8]=2;
					option2=17;
					repaint();
				}
				if(box[8]==1)
				{
					box[7]=2;
					option2=18;
					repaint();
				}
			}
			if(option==3)
			{
				if(box[1]==1)
				{
					box[6]=2;
					option2=19;
					repaint();
				}
				if(box[2]==1)
				{
					box[5]=2;
					option2=20;
					repaint();
				}
				else if(box[3]==1)
				{
					box[2]=2;
					option2=21;
					repaint();
				}
				else if(box[5]==1)
				{
					box[2]=2;
					option2=22;
					repaint();
				}
				else if(box[6]==1)
				{
					box[7]=2;
					option2=23;
					repaint();
				}
				else if(box[7]==1)
				{
					box[6]=2;
					option2=24;
					repaint();
				}
			}
		}

	}
	public void turn7()
	{
		turn++;
		sleep(1000);
		offenseTest();
		if(moved==false)
		{
			if(option2==1)
			{
				if(box[6]==1)
				{
					box[5]=2;
				}	
				else if(box[5]==1)
				{
					box[6]=2;
				}
				else if(box[5]!=1&&box[6]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[5]=2;
					}
					else if(randomTest==1)
						box[6]=2;
				}
			}
			if(option2==2)
			{
				if(box[7]!=1)
				{
					box[7]=2;
				}
				else if(box[7]==1)
				{
					box[5]=2;
				}
			}
			if(option2==3)
			{
				if(box[2]==1)
				{
					box[7]=2;
				}	
				else if(box[7]==1)
				{
					box[2]=2;
				}
				else if(box[2]!=1&&box[7]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[2]=2;
					}
					else if(randomTest==1)
						box[7]=2;
				}
			}
			if(option2==4)
			{
				if(box[2]==1)
				{
					box[7]=2;
				}	
				else if(box[7]==1)
				{
					box[2]=2;
				}
				else if(box[2]!=1&&box[7]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[2]=2;
					}
					else if(randomTest==1)
						box[7]=2;
				}
			}
			if(option2==5)
			{
				if(box[5]!=1)
				{
					box[5]=2;
				}
				else if(box[5]==1)
				{
					box[7]=2;
				}
			}
			if(option2==6)
			{
				if(box[5]==1)
				{
					box[6]=2;
				}	
				else if(box[6]==1)
				{
					box[5]=2;
				}
				else if(box[5]!=1&&box[6]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[5]=2;
					}
					else if(randomTest==1)
						box[6]=2;
				}
			}
			if(option2==7)
			{
				if(box[7]!=1)
				{
					box[7]=2;
				}
				else if(box[7]==1)
				{
					box[3]=2;
				}
			}
			if(option2==8)
			{
				if(box[3]==1)
				{
					box[8]=2;
				}	
				else if(box[8]==1)
				{
					box[3]=2;
				}
				else if(box[3]!=1&&box[8]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[8]=2;
					}
					else if(randomTest==1)
						box[3]=2;
				}
			}
			if(option2==9)
			{
				if(box[0]==1)
				{
					box[7]=2;
				}	
				else if(box[7]==1)
				{
					box[0]=2;
				}
				else if(box[0]!=1&&box[7]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[5]=0;
					}
					else if(randomTest==1)
						box[6]=7;
				}
			}
			if(option2==10)
			{
				if(box[0]==1)
				{
					box[7]=2;
				}	
				else if(box[7]==1)
				{
					box[0]=2;
				}
				else if(box[0]!=1&&box[7]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[0]=2;
					}
					else if(randomTest==1)
						box[7]=2;
				}
			}
			if(option2==11)
			{
				if(box[3]==1)
				{
					box[8]=2;
				}	
				else if(box[8]==1)
				{
					box[3]=2;
				}
				else if(box[3]!=1&&box[8]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[3]=2;
					}
					else if(randomTest==1)
						box[8]=2;
				}
			}
			if(option2==12)
			{
				if(box[3]!=1)
				{
					box[3]=2;
				}
				else if(box[3]==1)
				{
					box[7]=2;
				}
			}
			if(option2==13)
			{
				if(box[5]!=1)
				{
					box[5]=2;
				}
				else if(box[5]==1)
				{
					box[1]=2;
				}
			}
			if(option2==14)
			{
				if(box[0]==1)
				{
					box[8]=2;
				}	
				else if(box[8]==1)
				{
					box[0]=2;
				}
				else if(box[0]!=1&&box[8]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[0]=2;
					}
					else if(randomTest==1)
						box[8]=2;
				}
			}
			if(option2==15)
			{

				if(box[1]==1)
				{
					box[8]=2;
				}	
				else if(box[8]==1)
				{
					box[1]=2;
				}
				else if(box[1]!=1&&box[8]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[1]=2;
					}
					else if(randomTest==1)
						box[8]=2;
				}
			}
			if(option2==16)
			{
				if(box[1]==1)
				{
					box[8]=2;
				}	
				else if(box[8]==1)
				{
					box[1]=2;
				}
				else if(box[1]!=1&&box[8]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[1]=2;
					}
					else if(randomTest==1)
						box[8]=2;
				}
			}
			if(option2==17)
			{
				if(box[0]==1)
				{
					box[5]=2;
				}	
				else if(box[5]==1)
				{
					box[0]=2;
				}
				else if(box[0]!=1&&box[5]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[0]=2;
					}
					else if(randomTest==1)
						box[5]=2;
				}
			}
			if(option2==18)
			{
				if(box[1]!=1)
				{
					box[1]=2;
				}
				else if(box[1]==1)
				{
					box[5]=2;
				}
			}
			if(option2==19)
			{
				if(box[2]==1)
				{
					box[3]=2;
				}	
				else if(box[3]==1)
				{
					box[2]=2;
				}
				else if(box[2]!=1&&box[3]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[2]=2;
					}
					else if(randomTest==1)
						box[3]=2;
				}
			}
			if(option2==20)
			{
				if(box[3]!=1)
				{
					box[3]=2;
				}
				else if(box[3]==1)
				{
					box[1]=2;
				}
			}
			if(option2==21)
			{
				if(box[1]==1)
				{
					box[6]=2;
				}	
				else if(box[6]==1)
				{
					box[1]=2;
				}
				else if(box[1]!=1&&box[6]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[1]=2;
					}
					else if(randomTest==1)
						box[6]=2;
				}
			}
			if(option2==22)
			{
				if(box[1]==1)
				{
					box[6]=2;
				}	
				else if(box[6]==1)
				{
					box[1]=2;
				}
				else if(box[1]!=1&&box[6]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[1]=2;
					}
					else if(randomTest==1)
						box[6]=2;
				}
			}
			if(option2==23)
			{
				if(box[1]!=1)
				{
					box[1]=2;
				}
				else if(box[1]==1)
				{
					box[3]=2;
				}
			}
			if(option2==24)
			{
				if(box[2]==1)
				{
					box[3]=2;
				}	
				else if(box[3]==1)
				{
					box[2]=2;
				}
				else if(box[2]!=1&&box[3]!=1)
				{
					randomTest=r.nextInt(2);
					if(randomTest==0)
					{
						box[2]=2;
					}
					else if(randomTest==1)
						box[3]=2;
				}
			}
		}
		repaint();
	}
	public void turn9()
	{
		sleep(1000);
		turn++;
		for(int i=0;i<box.length;i++)
		{
			if(box[i]==0)
			{
				box[i]=2;
			}
		}
		repaint();
	}
	
	public void winTest()
	{
		{
			for(int i=0;i<7;i+=3)
			{
				if(box[i]==gameState&&box[i+1]==gameState&&box[i+2]==gameState)
				{
				win=true;	
				}
			}
			for(int i=0;i<3;i++)
			{
				if(box[i]==gameState&&box[i+3]==gameState&&box[i+6]==gameState)
				{
				win=true;	
				}
			}
			if(box[0]==gameState&&box[4]==gameState&&box[8]==gameState)
			{
				win=true;
			}
			else if(box[2]==gameState&&box[4]==gameState&&box[6]==gameState)
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

		if(box[0]==gameState&&box[1]==gameState&&box[2]==0)		
		{
			box[2]=gameState;
			moveSet();
		}
		else if(box[2]==gameState&&box[1]==gameState&&box[0]==0)		
		{
			box[0]=gameState;
			moveSet();
		}
		else if(box[0]==gameState&&box[2]==gameState&&box[1]==0)		
		{
			box[1]=gameState;
			moveSet();
		}
		else if(box[3]==gameState&&box[4]==gameState&&box[5]==0)		
		{
			box[5]=gameState;
			moveSet();
		}
		else if(box[5]==gameState&&box[4]==gameState&&box[3]==0)		
		{
			box[3]=gameState;
			moveSet();
		}
		else if(box[3]==gameState&&box[5]==gameState&&box[4]==0)		
		{
			box[4]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[7]==gameState&&box[8]==0)		
		{
			box[8]=gameState;
			moveSet();
		}
		else if(box[8]==gameState&&box[7]==gameState&&box[6]==0)		
		{
			box[6]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[8]==gameState&&box[7]==0)		
		{
			box[7]=gameState;
			moveSet();
		}
		else if(box[0]==gameState&&box[3]==gameState&&box[6]==0)		
		{
			box[6]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[3]==gameState&&box[0]==0)		
		{
			box[0]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[0]==gameState&&box[3]==0)		
		{
			box[3]=gameState;
			moveSet();
		}
		else if(box[1]==gameState&&box[4]==gameState&&box[7]==0)		
		{
			box[7]=gameState;
			moveSet();
		}
		else if(box[1]==gameState&&box[7]==gameState&&box[4]==0)		
		{
			box[4]=gameState;
			moveSet();
		}
		else if(box[7]==gameState&&box[4]==gameState&&box[1]==0)		
		{
			box[1]=gameState;
			moveSet();
		}
		else if(box[2]==gameState&&box[5]==gameState&&box[8]==0)		
		{
			box[8]=gameState;
			moveSet();
		}
		else if(box[8]==gameState&&box[5]==gameState&&box[2]==0)		
		{
			box[2]=gameState;
			moveSet();
		}
		else if(box[8]==gameState&&box[2]==gameState&&box[5]==0)		
		{
			box[5]=gameState;
			moveSet();
		}
		else if(box[0]==gameState&&box[4]==gameState&&box[8]==0)		
		{
			box[8]=gameState;
			moveSet();
		}
		else if(box[8]==gameState&&box[4]==gameState&&box[0]==0)		
		{
			box[0]=gameState;
			moveSet();
		}
		else if(box[0]==gameState&&box[8]==gameState&&box[4]==0)		
		{
			box[4]=gameState;
			moveSet();
		}
		else if(box[2]==gameState&&box[4]==gameState&&box[6]==0)		
		{
			box[6]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[4]==gameState&&box[2]==0)		
		{
			box[2]=gameState;
			moveSet();
		}
		else if(box[6]==gameState&&box[2]==gameState&&box[4]==0)		
		{
			box[4]=gameState;
			moveSet();
		}
	}
	public void defenseTest()//checks for losing move and blocks
	{
		if(moved==false)
		{
			if(box[0]==2&&box[1]==2&&box[2]==0)		
			{
				box[2]=1;
				moveSet();
			}
			else if(box[2]==2&&box[1]==2&&box[0]==0)		
			{
				box[0]=1;
				moveSet();
			}
			else if(box[0]==2&&box[2]==2&&box[1]==0)		
			{
				box[1]=1;
				moveSet();
			}
			else if(box[3]==2&&box[4]==2&&box[5]==0)		
			{
				box[5]=1;
				moveSet();
			}
			else if(box[5]==2&&box[4]==2&&box[3]==0)		
			{
				box[3]=1;
				moveSet();
			}
			else if(box[3]==2&&box[5]==2&&box[4]==0)		
			{
				box[4]=1;
				moveSet();
			}
			else if(box[6]==2&&box[7]==2&&box[8]==0)		
			{
				box[8]=1;
				moveSet();
			}
			else if(box[8]==2&&box[7]==2&&box[6]==0)		
			{
				box[6]=1;
				moveSet();
			}
			else if(box[6]==2&&box[8]==2&&box[7]==0)		
			{
				box[7]=1;
				moveSet();
			}
			else if(box[0]==2&&box[3]==2&&box[6]==0)		
			{
				box[6]=1;
				moveSet();
			}
			else if(box[6]==2&&box[3]==2&&box[0]==0)		
			{
				box[0]=1;
				moveSet();
			}
			else if(box[6]==2&&box[0]==2&&box[3]==0)		
			{
				box[3]=1;
				moveSet();
			}
			else if(box[1]==2&&box[4]==2&&box[7]==0)		
			{
				box[7]=1;
				moveSet();
			}
			else if(box[1]==2&&box[7]==2&&box[4]==0)		
			{
				box[4]=1;
				moveSet();
			}
			else if(box[7]==2&&box[4]==2&&box[1]==0)		
			{
				box[1]=1;
				moveSet();
			}
			else if(box[2]==2&&box[5]==2&&box[8]==0)		
			{
				box[8]=1;
				moveSet();
			}
			else if(box[8]==2&&box[5]==2&&box[2]==0)		
			{
				box[2]=1;
				moveSet();
			}
			else if(box[8]==2&&box[2]==2&&box[5]==0)		
			{
				box[5]=1;
				moveSet();
			}
			else if(box[0]==2&&box[4]==2&&box[8]==0)		
			{
				box[8]=1;
				moveSet();
			}
			else if(box[8]==2&&box[4]==2&&box[0]==0)		
			{
				box[0]=1;
				moveSet();
			}
			else if(box[0]==2&&box[8]==2&&box[4]==0)		
			{
				box[4]=1;
				moveSet();
			}
			else if(box[2]==2&&box[4]==2&&box[6]==0)		
			{
				box[6]=1;
				moveSet();
			}
			else if(box[6]==2&&box[4]==2&&box[2]==0)		
			{
				box[2]=1;
				moveSet();
			}
			else if(box[6]==2&&box[2]==2&&box[4]==0)		
			{
				box[4]=1;
				moveSet();
			}
		}
	}
	public void moveSet()
	{
		moved=true;
		repaint();
	}
	public void turnSet()
	{
		turn++;
		repaint();
	}
	public void sleep(int sleepTime)
	{
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
		}
	}
	public void mousePressed(MouseEvent cursor) {
		if(gameState==0)
		{	
			if((int) (cursor.getPoint().x) > 150 && cursor.getPoint().x<400 && cursor.getPoint().y > 250 &&cursor.getPoint().y<330)
			{
				randomTest=r.nextInt(2);
				if(randomTest==0)
				{
					gameState=1;
				}
				if(randomTest==1)
				{
					gameState=2;
				}
				repaint();
			}
			if((int) (cursor.getPoint().x) > 200 && cursor.getPoint().x<350 && cursor.getPoint().y > 380 &&cursor.getPoint().y<440)
			{
				System.exit(0);
			}
		}
		else if(gameState==1)
		{
			if(win||tie)
			{
				if(win)
				{
					compScore++;
				}
				if(tie)
				{
					tieScore++;
				}
					saveRun();				
				if((int) (cursor.getPoint().x) < 600 && cursor.getPoint().x > 0 && cursor.getPoint().y < 600 && cursor.getPoint().y > 0 )
				{
					gameState=3;
					repaint();
				}
			}
			if(turn%2==1&&win==false)
			{	//does the moves according to the box area pressed as human
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().y < 100&&box[0]==0)
				{
					box[0]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 100&&box[1]==0)
				{
					box[1]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 100&&box[2]==0)
				{
					box[2]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().x > 0 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[3]==0)
				{
					
					box[3]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[4]==0)
				{
					box[4]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[5]==0)
				{
					box[5]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().x > 0 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[6]==0)
				{
					box[6]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[7]==0)
				{
					box[7]=2;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[8]==0)
				{
					box[8]=2;
					turnSet();
				}
			}
		}
		else if(gameState==2)
		{
			if(win||tie)
			{
				if(win)
				{
					compScore++;

				}
				if(tie)
				{
					tieScore++;
				}
					saveRun();
				if((int) (cursor.getPoint().x) < 600 && cursor.getPoint().x > 0 && cursor.getPoint().y < 600 && cursor.getPoint().y > 0 )
				{
					gameState=3;
					repaint();
				}
			}
			if(turn%2==0&&win==false)
			{	//does the moves according to the box area pressed as human
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().y < 100&&box[0]==0)
				{
					box[0]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 100&&box[1]==0)
				{
					box[1]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 100&&box[2]==0)
				{
					box[2]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().x > 0 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[3]==0)
				{
					box[3]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[4]==0)
				{
					box[4]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 200 && cursor.getPoint().y > 100&&box[5]==0)
				{
					box[5]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 100 && cursor.getPoint().x > 0 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[6]==0)
				{
					box[6]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 200 && cursor.getPoint().x > 100 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[7]==0)
				{
					box[7]=1;
					turnSet();
				}
				if((int) (cursor.getPoint().x) < 300 && cursor.getPoint().x > 200 && cursor.getPoint().y < 300 && cursor.getPoint().y > 200&&box[8]==0)
				{
					box[8]=1;
					turnSet();
				}
			}
		}
		else if(gameState==3)//does action when in loss screen
		{
			if((int) (cursor.getPoint().x) >80 && cursor.getPoint().x < 280 && cursor.getPoint().y > 200 && cursor.getPoint().y < 280)
			{
				gameState=0;
				repaint();
			}
			if((int) (cursor.getPoint().x) >100 && cursor.getPoint().x < 250 && cursor.getPoint().y > 300&&cursor.getPoint().y < 360)
			{
				System.exit(0);
			}
		}
		else if(gameState==4)//moves to game title after clicked in story screen
		{
			if((int) (cursor.getPoint().x) < 600 && cursor.getPoint().x > 0 && cursor.getPoint().y < 600 && cursor.getPoint().y > 0 )
			{
				gameState=0;
				repaint();
			}
		}
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}