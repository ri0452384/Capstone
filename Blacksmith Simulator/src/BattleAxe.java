
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BattleAxe extends Weapon {

	BattleAxe(){
		super(19,34,"Battle Axe",875);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Beak","Bite","Butcher","Edge","Etcher","Gnash",
			"Hunger","Mangler","Rend","Roar","Sever","Slayer","Song","Spawn","Splitter","Sunder",
			"Thirst"};
		logCost = 3;
		ironCost = 4;
		flavorText = "The battle axe is a weapon of high damage."
				+ "\nIt is able to deal massive damage to enemy"
				+ "\n lines, and therefore must be wielded by"
				+ "\n a warrior of great skill and strength.";
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
			
			g.drawImage(new Image("Images/battleaxe.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
