import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class TwentyFive extends Achievement {

	TwentyFive(){
		super();
		try {
			super.icon = new Image("Images/25weapons.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}finally{
			super.achievementText = "Successfully sold"
					+ "\n twenty-five weapons!";
		}
	}

	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException{

		box = new Rectangle(400,165,300,75);
		if(box != null && unlocked){
			
			g.setColor(Color.decode("#141326"));
			g.fill(box);
			g.drawImage(icon,box.getMinX() + 25,box.getMinY()+10);
			g.setColor(Color.white);
			g.drawString(achievementText,box.getMinX() + 75 , box.getMinY()+25);
		}
		
	}
	

}
