import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BroadAxe extends Weapon {
	
	BroadAxe(){
		super(19,34,"Broad Axe",470);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Beak","Bite","Butcher","Edge","Etcher","Gnash",
			"Hunger","Mangler","Rend","Roar","Sever","Slayer","Song","Spawn","Splitter","Sunder",
			"Thirst"};
		logCost = 3;
		ironCost = 3;
		flavorText = "The broad axe is the perfect weapon"
				+ "\n for those who seek to fight in a "
				+ "\nbarbaric manner. Disregarding prim "
				+ "\nand proper behavior in the battlefield,"
				+ "\n the owner of this weapon must risk it all"
				+ "\n in order to annihilate the enemy.";
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame,
			Graphics g) {
		super.render(container,maingame,g);
		try {
			
			g.drawImage(new Image("Images/broadaxe.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
