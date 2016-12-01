import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class EmeraldLance extends Weapon {

	EmeraldLance(){
		
		super(25,44,"Emerald Lance",2085);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Barb","Beak","Bite","Edge","Fang",
				"Gutter","Hunger","Impaler","Needle","Razor","Saw","Scalpel","Scratch","Sever","Skewer","Slicer",
				"Song","Spike","Spiker","Stinger","Thirst"};
		logCost = 2;
		ironCost = 3;
		flavorText = "The Emerald Lance is a legendary weapon"
				+ "\n meant only for those who have mastered "
				+ "\n weapon handling, and experienced battle"
				+ "\n  a thousand times.This weapon is able to deal"
				+ "\n great damage and dispose hordes of enemies "
				+ "\n due to its magical properties.";
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame,Graphics g) {
		super.render(container,maingame,g);
		try {
			
			g.drawImage(new Image("Images/emeraldlance.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
