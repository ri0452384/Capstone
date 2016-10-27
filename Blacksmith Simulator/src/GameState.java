import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameState extends BasicGameState {

	private Player smith;
	private StaticLevel level;

	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		// TODO Auto-generated method stub
		level = new StaticLevel();
		smith = new Player(level);
		level.init(container,maingame);
		smith.init(container, maingame);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
		}
		if(level.visitWorkshop(smith.playerBox)){
			maingame.enterState(3, new FadeOutTransition(), new FadeInTransition());
			smith.vector.x = 525;
			smith.vector.y = 225;
		}
		if(level.visitMine(smith.playerBox)){
			maingame.enterState(4, new FadeOutTransition(), new FadeInTransition());
			smith.vector.x = 125;
			smith.vector.y = 125;
		}
			
		
		level.update(container, maingame, delta);
		smith.update(container, maingame, delta);
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		//container.sleep(100);
		//g.drawString("Game State",50,100);
		drawDebugLines(g, 50);
		g.setColor(Color.red);
		g.drawString("Workshop DOOR", 500, 150);
		g.setColor(Color.blue);
		g.drawString("To the Mines", 100,50);
		smith.render(container,maingame,g);
		level.render(container,maingame,g);
	}
	
	//helper method that generates the grid lines
	private void drawDebugLines(Graphics g, int size){
		int resolution = 800;
		g.setColor(Color.darkGray);
		for(int i=0; i<resolution; i+=size){
			g.drawLine(i,0,i,resolution);
			g.drawLine(0,i, resolution,i);
			g.drawString(Integer.toString(i), 0, i);
			g.drawString(Integer.toString(i), i, 0);
		}
	}
	
	

	
	@Override
	public int getID() {
		
		return 1;
	}

}
