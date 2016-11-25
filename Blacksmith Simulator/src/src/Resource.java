import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public abstract class Resource {
	
	String name;

	
	Shape rect;
	
	Image resourceForm;

	
	public abstract void init(GameContainer container, StateBasedGame maingame);
	
	public abstract void update(GameContainer container, StateBasedGame maingame, int delta);
	
	public abstract void render(GameContainer container, StateBasedGame maingame, Graphics g);
	
}
