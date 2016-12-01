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

	Player smith;
	
	
	GameState(Player smith){
		this.smith = smith;
	}

	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		smith = new Player();
		smith.init(container, maingame);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
		}
		if(smith.level.visitWorkshop(smith.playerBox)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(3, new FadeOutTransition(), new FadeInTransition());
			smith.vector.x = 525;
			smith.vector.y = 225;
			smith.facedown();
			
		}
		if(smith.level.visitNotDoor(smith.playerBox)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(5, new FadeOutTransition(), new FadeInTransition());
			smith.vector.x = 525;
			smith.vector.y = 225;
			smith.facedown();
			
		}
		if(smith.level.visitMine(smith.playerBox,(MainGame) maingame)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(4, new FadeOutTransition(), new FadeInTransition());
			((MainGame)maingame).mine.smith.level.refillMetals();
			smith.vector.x = 265;
			smith.vector.y = 140;
			smith.facedown();
		}
		smith.update(container, maingame, delta);
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		
		String tip="Tip: Don't forget to visit the notice board!";
		
		if(smith.level.mineOpen){
			tip = "The Mines are now open! Go up the ladder and into the tunnel at the top left";
		}
		smith.level.render(container,maingame,g);
		
		smith.render(container,maingame,g);
		g.setColor(Color.white);
		g.drawString(tip, 75, 580);
		
	}
	
	//helper method that generates the grid lines
	@SuppressWarnings("unused")
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
