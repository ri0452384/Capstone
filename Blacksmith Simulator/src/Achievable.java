import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public interface Achievable {
	public void setXY(int x, int y);
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException;
}
