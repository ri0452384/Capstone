import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/*
 * 	
 * 
 * 
 * class created by Rayven Ingles
 */
public class Workshop extends BasicGameState implements GameState {

	private Player smith;
	
	//private Image craftButton;
	private Rectangle craftContainer;
	
	private Image imbueButton;
	private Rectangle imbueContainer;
	
	private Image imbueButton1;
	private Rectangle imbueContainer1;
	
	private Image imbueButton2;
	private Rectangle imbueContainer2;
	
	private Polygon wsDoor;
	
	private Image background;
	
	Weapon wep;
	private boolean renderWeapon;
	

	private int scourCount;
	
	Workshop(Player smith){
		this.smith = smith;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		//now is located in the anvil object inside the workshop
		craftContainer = new Rectangle(665, 433, 170, 80);
		//rune for the imbue method, which adds a random prefix/suffix to the weapon
		imbueButton = new Image("Images/imbueButton.png");
		imbueContainer = new Rectangle(50, 50, imbueButton.getWidth(), imbueButton.getHeight());
		//rune for removing all magical properties from the weapon
		imbueButton1 = new Image("Images/imbueButton1.png");
		imbueContainer1 = new Rectangle(50, 150, imbueButton1.getWidth(), imbueButton1.getHeight());
		//TODO implement a use for this button/rune
		imbueButton2 = new Image("Images/imbueButton2.png");
		imbueContainer2 = new Rectangle(50, 250, imbueButton2.getWidth(), imbueButton2.getHeight());
		wsDoor = new Polygon(new float[]{571,338,	624,338,	624,476,	575,470});
		
		background = new Image("Images/workshop.jpg");
		
		
		//do not display the weapon at start
		renderWeapon = false;
		//you only have 5 chances to scour any crafted weapon, is set only after crafting
		scourCount = 0;
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		//get the coordinates of the mouse cursor every update
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		//invoke any updates to the weapon(after imbue, crafting, etc)
		if(wep!=null)
		wep.update(container, maingame, delta);
		
		//TODO create a door object to click to exit to go back to the main screen and use ESC to access menu instead
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
			
		}
		
		//TODO need to check for current inventory of the blacksmith for resources, then check the item about to be made
				if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					//fixed: multiple clicking by changing the input from isMouseButtonDown to isMousePressed event handling
					
					if(craftContainer.contains(mouseX, mouseY)){
						if(wep==null){
							craft();
							scourCount = 5;
						}
					}
					if(imbueContainer.contains(mouseX, mouseY)){
						
						if(wep != null)
							imbue();
					}
					if(imbueContainer1.contains(mouseX, mouseY)){
						
						if(wep != null){
							scour();
							wep.update(container, maingame, delta);
						}
							
					}
					if(imbueContainer2.contains(mouseX, mouseY)){
						
						//TODO implement method call for the button here
					}
					//checks if the workshop door is clicked, exits back to the smith's house
					if(wsDoor.contains(mouseX, mouseY)){
						maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
					}
				}
				

	}
	
	//helper method called to remove properties from the weapon, only limited to 5 charges per weapon crafted
	private void scour(){
		if(scourCount > 0){
			wep.removeAffixes();
			scourCount--;
		}
			
	}
	
	
	//this helper method will add random affixes to an item which is on display
	private void imbue(){
		wep.addAffix();
				
	}
	
	//helper method that will create a new weapon
	//TODO implement resource cost checking before crafting, show an error message or a warning if cost is not met
	private void craft() {
		wep = null;
		wep = new Rapier();
		renderWeapon = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		background.draw();
		//invokes drawing of debug lines for better button and image placement, to remove once program is live
		g.setColor(Color.gray);
		drawDebugLines(g,50);
		
		
		
		//draw all the buttons to the screen
		//craftButton.draw(craftContainer.getX(),craftContainer.getY());
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
