import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;




public class MainGame extends StateBasedGame {
	
	
	
	public MainGame(String title) {
		super(title);
		
	}

	

	public static void main(String[] args) throws SlickException {
		
		//initialize the application window here
		AppGameContainer app = new AppGameContainer(new MainGame("Blacksmith Simulator 1.0 prototype build 00001"));
		app.setDisplayMode(800, 600, false);
		app.start();

	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		this.addState(new GameState());//screen id: 1
		
		this.addState(new GameOverState());//screen id: 0
		
		this.addState(new Menu()); //screen id: 2
		
		this.addState(new Workshop()); //screen id: 3
		 
		this.addState(new Mines()); //screen id: 4
	}

}
