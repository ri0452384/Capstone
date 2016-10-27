import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public class StaticLevel {
private Shape levelBase;
private Shape workshopDoor;
private Shape mineDoor;
	
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		float[] polygonPoints = new float[]{
				//0,0,
				25,25,
				25,575,
				775,575,
				775,25,
				//600,350,
				//600,300,
				//750,300,
				//750,0,
				//800,0,
				//775,575,
				//0,575
		};
		levelBase = new Polygon(polygonPoints);
		polygonPoints = new float[]{100,25,
				100,100,
				250,100,
				250,25};
		mineDoor = new Polygon(polygonPoints);
		workshopDoor = new Rectangle(500, 100, 150, 100);
		
		}
	
	
	
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.cyan);
		g.draw(levelBase);
		g.draw(workshopDoor);
		g.draw(mineDoor);
	}
	
	public boolean visitWorkshop(Shape player){
		return workshopDoor.intersects(player);
	}
	
	public boolean collidesWith(Shape player){
		return levelBase.intersects(player);
	}
	
	public boolean visitMine(Shape player){
		return mineDoor.intersects(player);
	}
}
