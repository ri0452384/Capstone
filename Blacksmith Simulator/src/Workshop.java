
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/*
 * 	The Workshop is where all the magic happens, literally. The Blacksmith has kept three runes from the great war that was passed 
 *  on from generations and	that is the secret of how he can make weapons 
 * of exceptionally high quality and enabling him great efficiency.
 * 
 * class created by Rayven Ingles
 */
public class Workshop extends BasicGameState implements GameState {
	
	int playSound;
	
	//private Image craftButton;
	private Rectangle craftContainer;
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
	ArrayList<Rectangle> craftRectangles;
	//weapon to be displayed in the workshop
	Weapon wep;
	private boolean renderWeapon;
	//number of times that the clear affixes button can be used
	private int scourCount;
	//choice of the weapon about to be crafted
	int choice;
	private Sound GOLD;
	Sound ANVIL;
	Sound SCOUR;
	Sound WIN;
	Sound BLANK;
	
	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		GOLD = new Sound("Images/gold.wav");
		ANVIL = new Sound("Images/anvil.wav");
		SCOUR = new Sound("Images/scour.wav");
		WIN = new Sound("Images/win.wav");
		BLANK = new Sound("Images/blank.wav");
		//now is located in the anvil object inside the workshop
		craftContainer = new Rectangle(665, 433, 170, 80);
		//rune for the imbue method, which adds a random prefix/suffix to the weapon
		imbueButton = new Image("Images/imbueButton.png");
		imbueContainer = new Rectangle(150, 400, imbueButton.getWidth(), imbueButton.getHeight());
		//rune for removing all magical properties from the weapon
		imbueButton1 = new Image("Images/imbueButton1.png");
		imbueContainer1 = new Rectangle(250, 400, imbueButton1.getWidth(), imbueButton1.getHeight());
		//sell button
		imbueButton2 = new Image("Images/imbueButton2.png");
		imbueContainer2 = new Rectangle(350, 400, imbueButton2.getWidth(), imbueButton2.getHeight());
		wsDoor = new Polygon(new float[]{571,338,	624,338,	624,476,	575,470});
		craftChoices = new ArrayList<Weapon>();
		background = new Image("Images/workshop.png");
		choice=-1;
		craftRectangles = new ArrayList<Rectangle>();
		//do not display the weapon at start
		renderWeapon = false;
		//you only have 5 chances to scour any crafted weapon, is set only after crafting
		scourCount = 0;
		playSound = 0;
	}

	protected void generateCraftRectangles() {
		if(craftRectangles.isEmpty()){
			int row=50;
			for(int i=0;i<5;i++){
				craftRectangles.add(new Rectangle(row,550,125, 25));
				row += 150;
			}
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		//unlimited resources cheat. remove comments to enable, recommended in developer mode/testing
		//((MainGame)maingame).menu.ironCount +=5;
		//((MainGame)maingame).menu.logCount +=5;
		craftChoices = ((MainGame)maingame).notice.requests;
		//get the coordinates of the mouse cursor every update
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		//invoke any updates to the weapon(after imbue, crafting, etc)
		if(wep!=null)
		wep.update(container, maingame, delta);
		
	
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.CHOICE.play();
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
		}
		
				if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					//input handling
					if(craftContainer.contains(mouseX, mouseY)){
						if(!craftRectangles.isEmpty() || wep!=null){
							craft((MainGame)maingame);
							scourCount = 5;
						}
						
					}
					if(imbueContainer.contains(mouseX, mouseY)){
						
						if(wep != null){
							imbue();
							wep.update(container,maingame,delta);
						}
					}
					if(imbueContainer1.contains(mouseX, mouseY)){
						
						if(wep != null){
							scour();
							wep.update(container, maingame, delta);
						}
							
					}
					if(imbueContainer2.contains(mouseX, mouseY)){
						
						if(wep != null){
							sell(maingame);
						}
					}
					//checks if the workshop door is clicked, exits back to the smith's house
					if(wsDoor.contains(mouseX, mouseY)){
						((MainGame)maingame).gs.DOOR.play();
						maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
					}
					
					for(Rectangle rec:craftRectangles)
					{	
						if(rec ==null){
							
						}else if(rec.contains(mouseX, mouseY) ){
							
								choice = craftRectangles.indexOf(rec);
								wep = craftChoices.get(craftRectangles.indexOf(rec));
						}
					}
				}
				
				//to keep the mine timer open
				((MainGame)maingame).gs.smith.mineTimer += delta;
				
				if(((MainGame)maingame).gs.smith.mineTimer >= 25000){
					((MainGame)maingame).gs.smith.level.mineOpen = true;
					((MainGame)maingame).gs.smith.level.mineDoor = new Polygon(new float[]{100,25,
							100,100,
							250,100,
							250,25});
				}

	}
	
	private void sell(StateBasedGame maingame) {
		if(renderWeapon){
		GOLD.play();
		((MainGame)maingame).menu.coins += wep.totalPrice;
		
		craftChoices.remove(choice);
		craftRectangles.remove(choice);
		renderWeapon = false;
		((MainGame)maingame).menu.weaponSold++;
		wep = null;
		choice = -1;
		
			
		}
	}

	

	//helper method called to remove properties from the weapon, only limited to 5 charges per weapon crafted
	private void scour(){
		if(scourCount > 0 && wep !=null){
			SCOUR.play();
			wep.removeAffixes();
			scourCount--;
		}
	}
	
	
	//this helper method will add random affixes to an item which is on display
	private void imbue(){
		if(wep.prefixes[2] != null && wep.suffixes[2] != null){
			return;
		}else{
			
			wep.addAffix();
			return;
		}
		
				
	}
	
	
	private void craft(MainGame maingame) {
		
		if(choice == -1 || choice >= craftChoices.size()){
			return;
		}else{
			
			wep=craftChoices.get(choice);
			if((maingame.menu.ironCount >= wep.ironCost) && (maingame.menu.logCount >= wep.logCost)){
				
					ANVIL.play();
					ANVIL.play();
					ANVIL.play();
				maingame.getContainer().sleep(500);
				wep=craftChoices.get(choice);
				renderWeapon = true;
				maingame.menu.ironCount -= wep.ironCost;
				maingame.menu.logCount -=wep.logCost;
				ANVIL.stop();
			}
		}
		
			
	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		
		if(((MainGame)maingame).menu.coins >=1){
			g.clear();
			g.setColor(Color.black);
			g.fill(new Rectangle(300,400,200,100));
			g.setColor(Color.white);
			g.drawString("You have won the game! \n press ENTER to continue", 320, 450);
			
			if(container.getInput().isKeyPressed(Input.KEY_ENTER))
				maingame.enterState(0,new FadeOutTransition(), new FadeInTransition());
			
		}
		
		String tip="Welcome to the Workshop!";
		background.draw();
		//drawDebugLines(g,50);
		//invokes drawing of debug lines for better button and image placement, to remove once program is live
		g.setColor(Color.white);
		if(craftChoices.isEmpty()){
			tip= "To get weapon crafting options,\n exit the workshop and visit the notice board.";
		}else{
			tip="Tip: Click the weapon name then the ANVIL to craft a weapon.";
		}
		int i=0;
		for(Weapon w: craftChoices){
			
			if(w!=null){
				if(!renderWeapon && craftRectangles.get(i) != null){
					int mouseX = container.getInput().getMouseX();
					int mouseY = container.getInput().getMouseY();
					g.drawString(w.BASE_NAME, craftRectangles.get(i).getMinX() + 10, 550);
					if(craftRectangles.get(i).contains(mouseX, mouseY)){
						g.draw(craftRectangles.get(i));
						if(((MainGame)maingame).menu.ironCount < w.ironCost || ((MainGame)maingame).menu.logCount < w.logCost){
							tip="Not enough resources. Please visit the Mine.";
						}
					}
					 i++;
				}
				
			}
		}
		//draw all the buttons to the screen
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		
		if(imbueContainer.contains(mouseX,mouseY)){
			imbueButton = new Image("Images/imbueButton_hover.png");
			g.setColor(Color.white);
			tip = "The Ignis rune\n This will imbue your weapon with a random magical property";
		}else{

			imbueButton = new Image("Images/imbueButton.png"); 
		}
		
		if(imbueContainer1.contains(mouseX,mouseY)){
			imbueButton1 = new Image("Images/imbueButton1_hover.png");
			tip = "The rune of Tir\n This will scour your weapon, removing all magical properties";
		}else{
			imbueButton1 = new Image("Images/imbueButton1.png"); 
		}
		if(imbueContainer2.contains(mouseX,mouseY)){
			imbueButton2 = new Image("Images/imbueButton2_hover.png");
			tip = "The rune of Plutus\n This will magically transport your weapon\n into the hands of the citizen who requested it,"
					+ "\nand grants you the reward for crafting the weapon";
		}else{

			imbueButton2 = new Image("Images/imbueButton2.png"); 
		}
		
		imbueButton.draw(imbueContainer.getX(),imbueContainer.getY());
		imbueButton1.draw(imbueContainer1.getX(),imbueContainer1.getY());
		imbueButton2.draw(imbueContainer2.getX(),imbueContainer2.getY());
		
		
		//draws depending if the renderWeapon variable is true, throws NullPointerException if not checked properly
		if(renderWeapon){
			wep.render(container, maingame, g);
		}
		g.drawString(tip,50,470);
		if(wsDoor.contains(mouseX, mouseY)){
			Image workDoor = new Image("Images/exit_glow.png");
			g.drawImage(workDoor, 535, 317);
			g.drawString("exit",wsDoor.getCenterX(),wsDoor.getCenterY());
		}
		if(craftContainer.contains(mouseX,mouseY)){
			Image anvil = new Image("Images/anvil_active.png");
			g.drawImage(anvil, 669, 315);
			g.drawString("Craft",craftContainer.getCenterX(),craftContainer.getCenterY());
		}
		
		
		
		if(((MainGame)maingame).menu.coins >=52000){
			((MainGame)maingame).gameover.story_completed = true;
			if(!(playSound==1)){
				WIN.play();
				playSound = 1;
			}
			g.setColor(Color.decode("#141326"));
			g.fill(new Rectangle(300,200,300,150));
			g.setColor(Color.white);
			g.drawString("       CONGRATULATIONS!!!"
					+ "\nYou have finally crafted enough"
					+ "\n     weapons to vanquish the "
					+ "\n        monster threat!\n"
					+ "	\n     press ENTER to continue", 310, 210);
			
			if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
				
				maingame.enterState(0,new FadeOutTransition(), new FadeInTransition());
				
			}
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