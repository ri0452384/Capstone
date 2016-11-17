import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;


public class Log extends Resource {
	
	int x;
	int y;
	
	boolean visibility;
	
	Log(int x, int y){
		super.name = "log";
		visibility = true;
		try {
			resourceForm = new Image("Images/log.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y=y;
		rect = new Rectangle(this.x, this.y, resourceForm.getWidth(), resourceForm.getHeight());
		
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame maingame) {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame,
			int delta) {
		

	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame,
			Graphics g) {
			resourceForm.draw(x,y);

	}

}
