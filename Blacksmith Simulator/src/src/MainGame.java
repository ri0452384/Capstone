import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;




public class MainGame extends StateBasedGame {
	
	Player smith;
	
	GameState gs;
	Menu menu;
	Mines mine;
	Workshop ws;
	NoticeBoard notice;
	
	UnicodeFont uni;
	
	public MainGame(String title) {
		super(title);
		//setting the fonts in slick
	
		
	}

	

	public static void main(String[] args) throws SlickException {
		//This will tell java to use the relative path to the directory libs for loading the libraries. 
		//You may also use absolute paths here.
		System.setProperty("java.library.path", "libraries/jar");
		//Extracted from Distributing Your LWJGL Application
		System.setProperty("org.lwjgl.librarypath", new File("libraries/native/windows").getAbsolutePath());
		
		
		
		
		//initialize the application window here
		AppGameContainer app = new AppGameContainer(new MainGame("Blacksmith Simulator 0.90 build 1.0.0.0"));
		app.setDisplayMode(800, 600, false);
		app.setShowFPS(false);
		app.start();

	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		smith = new Player();
		gs = new GameState(smith);
		menu = new Menu(smith);
		mine = new Mines();
		ws = new Workshop(smith);
		notice = new NoticeBoard();
		
		this.addState(gs);//screen id: 1
		
		this.addState(new GameOverState(smith));//screen id: 0
		
		this.addState(menu); //screen id: 2
		
		this.addState(ws); //screen id: 3
		 
		this.addState(mine); //screen id: 4
		
		this.addState(notice); // screen id: 5
		try{
			InputStream is = new FileInputStream("diablo.ttf");
			Font diablo = Font.createFont(Font.PLAIN, is);
			diablo = diablo.deriveFont(1, 500);
			uni = new UnicodeFont(diablo, 12, false, false);
			
		}catch(IOException e){
			e.printStackTrace();
		}catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.getContainer().getGraphics().setFont(uni);
		}
		
	}

}
