import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


public abstract class Metal {

	private int contents;

	public int getContents() {
		return contents;
	}

	public void setContents(int contents) {
		this.contents = contents;
	}
	
	public abstract void init(GameContainer container, StateBasedGame maingame);
	
	public abstract void update(GameContainer container, StateBasedGame maingame, int delta);
	
	public abstract void render(GameContainer container, StateBasedGame maingame, Graphics g);
	
}
