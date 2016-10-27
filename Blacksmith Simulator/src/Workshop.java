import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Workshop extends BasicGameState implements GameState {

	private Image craftButton;
	private Rectangle craftContainer;
	
	private Image imbueButton;
	private Rectangle imbueContainer;
	
	private Image imbueButton1;
	private Rectangle imbueContainer1;
	
	private Image imbueButton2;
	private Rectangle imbueContainer2;
	
	Weapon wep;
	private boolean renderWeapon;
	FlatPhysicalDamage flat;
	PercentDamage perc;
	ColdDamage cold;
	Accuracy accu;
	
	Random rand;
	
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		//craft button details and container for mouseclick checking
		craftButton = new Image("Images/craftButton.png");
		craftContainer = new Rectangle(300, 400, craftButton.getWidth(), craftButton.getHeight());
		//rune for the imbue method, which adds a random prefix/suffix to the weapon
		imbueButton = new Image("Images/imbueButton.png");
		imbueContainer = new Rectangle(50, 50, imbueButton.getWidth(), imbueButton.getHeight());
		//rune for removing all magical properties from the weapon
		imbueButton1 = new Image("Images/imbueButton1.png");
		imbueContainer1 = new Rectangle(50, 150, imbueButton1.getWidth(), imbueButton1.getHeight());
		//TODO implement a use for this button/rune
		imbueButton2 = new Image("Images/imbueButton2.png");
		imbueContainer2 = new Rectangle(50, 250, imbueButton2.getWidth(), imbueButton2.getHeight());
		
		//instantiate all magical properties here
		flat = new FlatPhysicalDamage();
		perc = new PercentDamage();
		cold = new ColdDamage();
		rand = new Random();
		accu = new Accuracy();
		
		//do not display the weapon at start
		renderWeapon = false;
		
		//instantiate a weapon, else it will return a NullPointerException after loading workshop screen
		wep = (Weapon) new ShortSword();
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		//get the coordinates of the mouse cursor every update
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		//invoke any updates to the weapon(after imbue, crafting, etc)
		wep.update(container, maingame, delta);
		//checks for the ESC key to go into the main screen
		//TODO create a door object to click to exit to go back to the main screen and use ESC to access menu instead
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		
		//TODO need to check for current inventory of the blacksmith for resources, then check the item about to be made
				if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					//fixed: multiple clicking by changing the input from isMouseButtonDown to isMousePressed event handling
					
					if(craftContainer.contains(mouseX, mouseY)){
						craft();
					}
					if(imbueContainer.contains(mouseX, mouseY)){
						imbue();
					}
					if(imbueContainer1.contains(mouseX, mouseY)){
						scour();
						wep.update(container, maingame, delta);
					}
					if(imbueContainer2.contains(mouseX, mouseY)){
						
						//TODO implement method call for the button here
					}
				}
				

	}
	
	//helper method called to remove properties from the weapon
	private void scour(){
		wep.removeAffixes();
	}
	
	
	//this helper method will add random affixes to an item which is on display
	private void imbue(){
		//initialize affix placeholder to prevent NullPointerException
		Affix af =(Affix) new NoPrefix();
		
		int choice = rand.nextInt(4);
		String choice_MASK="";
		
		//VERY IMPORTANT: keep in order to prevent our game from performing an infinite loop (do-while it has all weapon properties)
		//for now, suffix count check is set to '> 0' since we only have one suffix, increase to 1 or 2 once you add more suffix classes
		if(wep.prefixCount > 2 && wep.suffixCount > 0){
			return;
		}
		
		do{
			choice = rand.nextInt(4);
			
			switch(choice){
			
			case 0:{
				choice_MASK = "FlatPhysDamage";
				af = flat;
				break;
			}case 1:{
				choice_MASK = "PercentDamage";
				af = perc;
				break;
			}case 2:{
				choice_MASK = "ColdDamage";
				af = cold;
				break;
				
			}case 3:{
				choice_MASK = "Accuracy";
				af = accu;
				break;
			}
			default:
				break;
			}
		}while(wep.alreadyHas(choice_MASK));
		
		wep.addAffix(af);
				
	}
	
	//helper method that will create a new weapon
	//TODO implement resource cost checking before crafting, show an error message or a warning if cost is not met
	private void craft() {
		
		renderWeapon = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		//invokes drawing of debug lines for better button and image placement, to remove once program is live
		g.setColor(Color.gray);
		drawDebugLines(g,50);
		
		
		//draw all the buttons to the screen
		craftButton.draw(craftContainer.getX(),craftContainer.getY());
		imbueButton2.draw(imbueContainer.getX(),imbueContainer.getY());
		imbueButton1.draw(imbueContainer1.getX(),imbueContainer1.getY());
		imbueButton2.draw(imbueContainer2.getX(),imbueContainer2.getY());
		//draws depending if the renderWeapon variable is true, throws NullPointerException if not checked properly
		if(renderWeapon)
			wep.render(container, maingame, g);
	}

	private void drawDebugLines(Graphics g, int size){
		int resolution = 800;
		g.setColor(Color.darkGray);
		for(int i=0; i<resolution; i+=size){
			g.drawLine(i,0,i,resolution);
			g.drawLine(0,i, resolution,i);
			g.drawString(Integer.toString(i), 0, i);
			g.drawString(Integer.toString(i), i, 0);
		}
	}


	@Override
	public int getID() {
		
		return 3;
	}

}
