import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameOverState extends BasicGameState {
	
	@SuppressWarnings("unused")
	private Player smith;
	String credit;
	
	GameOverState(Player smith){
		this.smith = smith;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		

	}

	
	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			maingame.enterState(6,new FadeOutTransition(),new FadeInTransition());
		}

	}
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		credit ="press ESC to go back to main menu."
				+ "\nhttp://vignette2.wikia.nocookie.net/steamtradingcards/images"
				+ "\n http://www.gold-stater.com/images/medieval/IMG_0046charlesiv.JPG"
				+ "\nhttps://mir-s3-cdn-cf.behance.net/project_modules/disp/5a6d327765417.560b13b412e67.jpg";
		g.drawString(credit, 50,200);

	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
