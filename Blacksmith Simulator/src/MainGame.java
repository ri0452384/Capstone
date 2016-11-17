import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;




public class MainGame extends StateBasedGame {
	
	Player smith;
	
	GameState gs;
	Menu menu;
	Mines mine;
	Workshop ws;
	
	
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
		
		smith = new Player();
		gs = new GameState(smith);
		menu = new Menu(smith);
		mine = new Mines();
		ws = new Workshop(smith);
		
		this.addState(gs);//screen id: 1
		
		this.addState(new GameOverState(smith));//screen id: 0
		
		this.addState(menu); //screen id: 2
		
		this.addState(ws); //screen id: 3
		 
		this.addState(mine); //screen id: 4
	}

}
