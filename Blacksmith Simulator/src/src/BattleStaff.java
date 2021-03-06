import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BattleStaff extends Weapon {

	BattleStaff(){
		
		super(6,37,"Battle Staff",570);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Beam","Branch","Call","Chant","Cry",
				"Gnarl","Goad","Mast","Pile","Pillar","Pole","Post","Roar","Song","Spell","Spire",
				"Weaver"};
		logCost = 5;
		ironCost = 1;
		flavorText = "The battle staff is the ideal weapon to use by healers.\n"
				+ " It is able to heal entire troops from their injuries"
				+ "\n while casting a shield that protects them, too.";
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
	}
	
	
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame,Graphics g) {
		super.render(container,maingame,g);
		try {
			
			g.drawImage(new Image("battlestaff.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
