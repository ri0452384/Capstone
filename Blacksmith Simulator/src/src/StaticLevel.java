import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public abstract class StaticLevel {
	Shape levelBase;

	abstract void init(GameContainer container, StateBasedGame sbg)
			throws SlickException;

	public abstract void update(GameContainer container, StateBasedGame sbg,
			int delta) throws SlickException;

	public abstract void render(GameContainer container, StateBasedGame sbg,
			Graphics g) throws SlickException;

	public boolean collidesWith(Shape player) {
		if (levelBase != null)
			return levelBase.intersects(player);

		return false;
	}

}
