import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public class HouseLevel extends StaticLevel {
	
private Shape workshopDoor;
Shape mineDoor;
private Image background;
boolean mineOpen;

	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		float[] polygonPoints = new float[]{
				//0,0,
				25,25,
				25,575,
				775,575,
				775,25,
		};
		levelBase = new Polygon(polygonPoints);
		
		mineDoor = new Polygon(new float[]{100,25,	250,25});
		workshopDoor = new Rectangle(500, 100, 150, 100);
		background = new Image("Images/house.png");
		mineOpen=false;
		}
	
	
	
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		g.setColor(Color.cyan);
		//g.draw(levelBase);
		g.draw(workshopDoor);
		if(mineOpen){
			g.draw(mineDoor);
		}
	}
	
	public boolean visitWorkshop(Shape player){
		return workshopDoor.intersects(player);
	}
	
	public boolean collidesWith(Shape player){
		return levelBase.intersects(player);
	}
	
	public boolean visitMine(Shape player,MainGame main){
		if(mineDoor.intersects(player)){
			mineOpen = false;
			mineDoor = new Polygon(new float[]{100,25,	250,25});
			((MainGame)main).gs.smith.mineTimer = 0;
			return true;
		}
		else return false;
	}
}
