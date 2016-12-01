import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public abstract class Achievement implements Achievable{
	

	Rectangle box;
	
	Image icon;
	
	String achievementText;
	
	boolean unlocked;
	
	int x;
	int y;
	
	
	Achievement(){
		achievementText = "";
		unlocked = false;
	}
	//to override the display of the achievement rectangle
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//user should use Set XY method first to set coordinates of rectangle
	public abstract void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException;

}
