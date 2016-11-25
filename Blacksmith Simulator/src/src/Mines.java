
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Mines extends BasicGameState {
	
	
	Miner smith;
	
	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		smith = new Miner();
		smith.init(container, maingame);
		
		
	}

	

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
			
		}
		if(smith.level.exitMine(smith.playerBox)){
			maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
			smith.vector.x = 700;
			smith.vector.y = 450;
		}
		smith.update(container, maingame, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		smith.render(container, maingame, g);
		g.setColor(Color.white);
		g.drawString("Tip: Use arrow keys to move around and pick up the resources in the mine.", 75, 580);
		
	}

	@Override
	public int getID() {
		return 4;
	}

}
