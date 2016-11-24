
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Menu extends BasicGameState implements GameState {
	
	
	@SuppressWarnings("unused")
	private Player smith;
	protected int prevState;
	int ironCount;
	int logCount;
	//coin of the realm
	int coins;
	
	
	Menu(Player smith){
		this.smith = smith;
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
			prevState = 1;
			ironCount = 0;
			logCount = 0;
			coins = 0;
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			maingame.enterState(prevState,new FadeOutTransition(), new FadeInTransition());
		}

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawString("Iron: "+ironCount,50,200);
		g.drawString("Logs: "+logCount,50,100);
		g.drawString("made by Codeneira, Ingles, Mañus, and Tolipas of OGTP Games \nAll rights reserved 2016\n OGTP is an independent software company formed only for CMSC 22.", 200,520);
		g.drawString("Coins: "+coins,50,300);
	}

	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
