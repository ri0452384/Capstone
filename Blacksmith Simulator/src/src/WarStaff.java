import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class WarStaff extends Weapon {

	WarStaff(){
		
		super(37,49,"War Staff",1980);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Beam","Branch","Call","Chant","Cry",
				"Gnarl","Goad","Mast","Pile","Pillar","Pole","Post","Roar","Song","Spell","Spire",
				"Weaver"};
		logCost = 5;
		ironCost = 2;
		flavorText = "The war staff is a legendary weapon used by extremely skilled warlocks."
				+ "\n It is able to control all types of elements but is extremely difficult to use."
				+ "\n Legend says that it takes a hundred years to gain this weapon’s full power. ";
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame,Graphics g) {
		super.render(container,maingame,g);
		try {
			
			g.drawImage(new Image("warstaff.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
