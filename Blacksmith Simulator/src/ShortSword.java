

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * one of the weapon classes to be implemented in the game. 
 * The Short Sword is the most basic of weapons carried by any hero or adventurer on the start of their adventure.
 * Without any magical properties, it hits for 3-6 damage.
 * 
 */

public class ShortSword extends Weapon {
	ShortSword(){
		
		super(3,6,"Short Sword",450,150);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		this.toString();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame,Graphics g) {
		try {
			g.setColor(textColor);
			g.drawString(this.toString(), x, y+50);
			g.drawImage(new Image("Images/ShortSword.png"), x+150, y-150);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	
		

}
