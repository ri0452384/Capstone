import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Mines extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}

	}
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("The Mines", 100,50);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
