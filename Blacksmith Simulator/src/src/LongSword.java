import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class LongSword extends Weapon {

	LongSword(){
		
		super(9,21,"Long Sword");
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		possibleRareSuffixes  = new String[]{"Bane","Barb","Beak","Bite","Edge","Fang",
				"Gutter","Hunger","Impaler","Needle","Razor","Saw","Scalpel","Scratch","Sever","Skewer","Slicer",
				"Song","Spike","Spiker","Stinger","Thirst"};
		logCost = 1;
		ironCost = 3;
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
			
			g.drawImage(new Image("longsword.png"), x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame,
			int delta) throws SlickException {
		this.toString();

	}

}