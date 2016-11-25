import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class DoubleAxe extends Weapon {
	
	DoubleAxe(){
		super(11,19,"Double Axe",385);
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Beak","Bite","Butcher","Edge","Etcher","Gnash",
			"Hunger","Mangler","Rend","Roar","Sever","Slayer","Song","Spawn","Splitter","Sunder",
			"Thirst"};
		logCost = 3;
		ironCost = 2;
		flavorText = "The double axe is the standard weapon used by Vikings and dwarves. \n"
				+ "This can allow them to wield the axe as if it were a sword, "
				+ "\nclearing a path through the battlefield.";	}

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
			
			g.drawImage(new Image("doubleaxe.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
