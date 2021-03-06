import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ConeLance extends Weapon {

	ConeLance(){
		
		super(19,27,"Cone Lance",610);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Barb","Beak","Bite","Edge","Fang",
				"Gutter","Hunger","Impaler","Needle","Razor","Saw","Scalpel","Scratch","Sever","Skewer","Slicer",
				"Song","Spike","Spiker","Stinger","Thirst"};
		logCost = 2;
		ironCost = 3;
		flavorText = "The Cone Lance is the weapon that fits"
				+ "\n those who wish to engage in battle on"
				+ "\n  horseback. This weapon can lash out"
				+ "\n  great damage and destroy enemy shields,"
				+ "\n leaving them defenseless.";
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	
	
	public void render(GameContainer container, StateBasedGame maingame,Graphics g) {
		super.render(container,maingame,g);
		try {
			
			g.drawImage(new Image("Images/conelance.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
