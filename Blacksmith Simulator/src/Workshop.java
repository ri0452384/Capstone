
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
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
	
	@SuppressWarnings("unused")
	private Player smith;
	//private Image craftButton;
	private Rectangle craftContainer;
	TrueTypeFont ttf;
	private Image imbueButton;
	private Rectangle imbueContainer;
	
	private Image imbueButton1;
	private Rectangle imbueContainer1;
	
	private Image imbueButton2;
	private Rectangle imbueContainer2;
	
	private Polygon wsDoor;
	UnicodeFont uni;
	private Image background;
	//weapons to be available for crafting
	ArrayList<Weapon> craftChoices;
	Shape[] craftRectangles;
	//weapon to be displayed in the workshop
	Weapon wep;
	private boolean renderWeapon;
	//number of times that the clear affixes button can be used
	private int scourCount;
	//choice of the weapon about to be crafted
	int choice;
	Workshop(Player smith){
		this.smith = smith;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		//now is located in the anvil object inside the workshop
		craftContainer = new Rectangle(665, 433, 170, 80);
		//rune for the imbue method, which adds a random prefix/suffix to the weapon
		imbueButton = new Image("imbueButton.png");
		imbueContainer = new Rectangle(50, 50, imbueButton.getWidth(), imbueButton.getHeight());
		//rune for removing all magical properties from the weapon
		imbueButton1 = new Image("imbueButton1.png");
		imbueContainer1 = new Rectangle(50, 150, imbueButton1.getWidth(), imbueButton1.getHeight());
		//TODO implement a use for this button/rune
		imbueButton2 = new Image("imbueButton2.png");
		imbueContainer2 = new Rectangle(50, 250, imbueButton2.getWidth(), imbueButton2.getHeight());
		wsDoor = new Polygon(new float[]{571,338,	624,338,	624,476,	575,470});
		craftChoices = new ArrayList<Weapon>();
		background = new Image("workshop.jpg");
		choice=-1;
		craftRectangles = new Rectangle[5];
		//do not display the weapon at start
		renderWeapon = false;
		//you only have 5 chances to scour any crafted weapon, is set only after crafting
		scourCount = 0;
		int row=50;
		for(int i=0;i<5;i++){
			craftRectangles[i] = new Rectangle(row,550,125, 25);
			row += 150;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		craftChoices = ((MainGame)maingame).notice.requests;
		//get the coordinates of the mouse cursor every update
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		//invoke any updates to the weapon(after imbue, crafting, etc)
		if(wep!=null)
		wep.update(container, maingame, delta);
		
	
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
		}
		
		
	
				if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					//fixed: multiple clicking by changing the input from isMouseButtonDown to isMousePressed event handling
					
					if(craftContainer.contains(mouseX, mouseY)){
						
							craft((MainGame)maingame);
							scourCount = 5;
						
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
					for(int i=0;i<5;i++)
					{	
						if(craftRectangles[i] ==null){
							
						}else if(craftRectangles[i].contains(mouseX, mouseY)){
							choice = i;
							if(((MainGame)maingame).menu.logCount >= craftChoices.get(i).logCost 
									&& ((MainGame)maingame).menu.ironCount >= craftChoices.get(i).ironCost){
								//renderWeapon = true;
								wep = craftChoices.get(i);
								//craftChoices.remove(i);
							}
						}
					}
				}
				((MainGame)maingame).gs.smith.mineTimer += delta;
				
				if(((MainGame)maingame).gs.smith.mineTimer >= 25000){
					((MainGame)maingame).gs.smith.level.mineOpen = true;
					((MainGame)maingame).gs.smith.level.mineDoor = new Polygon(new float[]{100,25,
							100,100,
							250,100,
							250,25});
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
	private void craft(MainGame maingame) {
		if(wep==null)
			return;
		if(choice == -1){
			//do nothing
			return;
		}else{
			wep=craftChoices.get(choice);
			if((maingame.menu.ironCount >= wep.ironCost) && (maingame.menu.logCount >= wep.logCost)){
				wep=craftChoices.get(choice);
				renderWeapon = true;
				maingame.menu.ironCount -= wep.ironCost;
				maingame.menu.logCount -=wep.logCost;
			}
		}
		
			
	}
	
	

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		background.draw();
		
		//invokes drawing of debug lines for better button and image placement, to remove once program is live
		g.setColor(Color.cyan);
		
		//int row=65;
		
		int i=0;
		for(Weapon w: craftChoices){
			
			if(w!=null){
				if(!renderWeapon){
					g.drawString(w.BASE_NAME, craftRectangles[i].getMinX() + 10, 550);
					// g.draw(craftRectangles[i]);
					 i++;
				}
				
			}
			//row += 150;
		}
		//draw all the buttons to the screen
		//craftButton.draw(craftContainer.getX(),craftContainer.getY());
		drawDebugLines(g,50);
		imbueButton2.draw(imbueContainer.getX(),imbueContainer.getY());
		imbueButton1.draw(imbueContainer1.getX(),imbueContainer1.getY());
		imbueButton2.draw(imbueContainer2.getX(),imbueContainer2.getY());
		
		
		//draws depending if the renderWeapon variable is true, throws NullPointerException if not checked properly
		if(renderWeapon){
			Rectangle shape = new Rectangle(425, 175, 325, 225);
			g.setColor(Color.decode("#141326"));
			g.fillRect(400, 175, 375, 225);
			g.draw(shape);
			wep.render(container, maingame, g);
		}
	}

	@SuppressWarnings("unused")
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
