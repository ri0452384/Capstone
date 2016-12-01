import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Menu extends BasicGameState implements GameState,Serializable {
	
	private static final long serialVersionUID = -3105191768616713206L;
	protected int prevState;
	int ironCount;
	int logCount;
	int resources;
	//coin of the realm
	int coins;
	private Image background;
	private Image logIcon;
	private Image ironIcon;
	private Image goldIcon;
	String saveName;
	private Rectangle saveBox;
	private Rectangle loadBox;
	private Rectangle mainBox;
	ArrayList<Achievement> achievements;
	TenThousand tenk;
	HundredThousand hundredk;
	OneMillion million;
	
	public int weaponSold;
	
	public int mineVisits;
	
	private OneWeapon onew;
	private FiveWeapon fivew;
	private TwentyFive twentyfivew;
	private Fifty fiftyw;
	private OneHundred hundredw;
	private Once once;
	private FiveTimes fivet;
	private TenTimes tent;
	private FiftyTimes fiftyt;
	private FiftyResources fiftyr;
	private HundredResources hundredr;
	private FiveHundredResources fivehundredr;
	private OneThousandResources thousandr;
	
	Sound CHOICE;
	
	Menu(){
		mineVisits = 0;
		weaponSold = 0;
		prevState = 1;
		ironCount = 0;
		logCount = 0;
		coins = 0;
	}
	

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		try {
			CHOICE = new Sound("Images/choice.wav");
			logIcon = new Image("Images/log.png");
			ironIcon = new Image("Images/iron.png");
			background = new Image("Images/menu_background.png");
			goldIcon = new Image("Images/gold.png");
			saveName = "Will";
			saveBox = new Rectangle(42,406,150,49);
			loadBox = new Rectangle(40,465,152,44);
			mainBox = new Rectangle(40,520,157,42);
			achievements = new ArrayList<Achievement>();
			fillAchievements();
		} catch (SlickException e) {
			e.printStackTrace();
		}finally{
		
			mineVisits = 0;
			weaponSold = 0;
			prevState = 1;
			ironCount = 0;
			logCount = 0;
			coins = 0;
		}
	}
	
	void fillAchievements() {
		tenk = new TenThousand();
		hundredk = new HundredThousand();
		million = new OneMillion();
		
		achievements.add(tenk);
		achievements.add(hundredk);
		achievements.add(million);
		
		onew = 	new OneWeapon();
		fivew = new FiveWeapon();
		twentyfivew = new TwentyFive();
		fiftyw = new Fifty();
		hundredw = new OneHundred();
		
		achievements.add(onew);
		achievements.add(fivew);
		achievements.add(twentyfivew);
		achievements.add(fiftyw);
		achievements.add(hundredw);
		
		
		once = new Once();		
		fivet = new FiveTimes();
		tent = new TenTimes();
		fiftyt = new FiftyTimes();
		
		
		achievements.add(once);
		achievements.add(fivet);
		achievements.add(tent);
		achievements.add(fiftyt);
		
		
		fiftyr = new FiftyResources();
		hundredr = new HundredResources();
		fivehundredr = new FiveHundredResources();
		thousandr = new OneThousandResources();
		
		achievements.add(fiftyr);
		achievements.add(hundredr);
		achievements.add(fivehundredr);
		achievements.add(thousandr);
		
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.CHOICE.play();
			maingame.enterState(prevState,new FadeOutTransition(), new FadeInTransition());
		}
		
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			
			if(mainBox.contains(mouseX,mouseY)){
				((MainGame)maingame).title.SELECT.play();
				maingame.enterState(6,new FadeOutTransition(), new FadeInTransition());
			}
			
			if(saveBox.contains(mouseX,mouseY)){
				((MainGame)maingame).title.SELECT.play();
				save();
			}
			
			if(loadBox.contains(mouseX,mouseY)){
				((MainGame)maingame).title.SELECT.play();
				load((MainGame)maingame);
			}
		}
		
		
		if((coins >= 10000) && (coins < 99999)){
			unlock(tenk);
		}
		else if((coins >= 100000) && (coins < 999999)){
			unlock(hundredk);
		}
		else if(coins > 999999){
			unlock(million);
		}
		
		
		if(resources >= 50 &&  resources < 100){
			unlock(fiftyr);
		}
		else if(resources >=100 && resources < 500){
			unlock(hundredr);
		}
		else if(resources >=500 && resources < 1000){
			unlock(fivehundredr);
		}
		else if(resources >=1000){
			unlock(thousandr);
		}
		
		
		if(mineVisits >= 1 && mineVisits < 5){
			unlock(once);
		}
		else if(mineVisits >= 5 && mineVisits < 10){
			unlock(fivet);
		}else if(mineVisits >=10 && mineVisits < 50){
			unlock(tent);
			
		}else if(mineVisits >= 50){
			unlock(fiftyt);
		}
		
		if(weaponSold >=1 && weaponSold <5){
			unlock(onew);
		}else if(weaponSold >=5 && weaponSold <25){
			unlock(fivew);
		}else if(weaponSold >=25 && weaponSold <50){
			unlock(twentyfivew);
		}else if(weaponSold >=50 && weaponSold <100){
			unlock(fiftyw);
		}else if(weaponSold >=100){
			unlock(hundredw);
		}

	}

	public void load(MainGame maingame) {
		FileInputStream file = null;
		ObjectInputStream obj = null;
		
		try {
			file = new FileInputStream("save.bss");
			
			obj = new ObjectInputStream(file);
			
			this.coins = (int) obj.readObject();
			this.ironCount=(int) obj.readObject();
			this.logCount =(int) obj.readObject();
			this.mineVisits =(int) obj.readObject();
			this.resources =(int) obj.readObject();
			this.weaponSold = (int) obj.readObject();
			
		} catch (FileNotFoundException e) {
			maingame.getContainer().getGraphics().drawString("save.bss file not found! creating new game...", 390, 550);
			maingame.getContainer().sleep(1000);
			maingame.menu = new Menu();
			maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				obj.close();
				maingame.getContainer().getGraphics().clear();
				maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}


	private void save() {
		FileOutputStream file = null;
		ObjectOutputStream obj = null;
		
		try {
			file = new FileOutputStream("save.bss", false);
			obj = new ObjectOutputStream(file);
			obj.writeObject(this.coins);
			obj.writeObject(this.ironCount);
			obj.writeObject(this.logCount);
			obj.writeObject(this.mineVisits);
			obj.writeObject(this.resources);
			obj.writeObject(this.weaponSold);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				obj.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		
		g.setColor(Color.green);
		g.drawImage(background,0,0);
		g.drawString(saveName+ " the Blacksmith",50,50);
		g.drawString("Resources available:", 50, 100);
		g.drawImage(logIcon,50, 120);
		g.drawString("x "+logCount,100,124);
		g.drawImage(ironIcon,50, 150);
		g.drawString("x "+ironCount,100,154);
		
		
		
		if(saveBox.contains(mouseX, mouseY)){
			g.drawImage(new Image("Images/menu_save.png"),saveBox.getMinX(),saveBox.getMinY());
		}
		if(loadBox.contains(mouseX,mouseY)){
			g.drawImage(new Image("Images/menu_load.png"),loadBox.getMinX(),loadBox.getMinY());
		}
		if(mainBox.contains(mouseX,mouseY)){
			g.drawImage(new Image("Images/menu_main.png"),mainBox.getMinX(),mainBox.getMinY());
		}
		
		
		g.drawImage(goldIcon,50, 225);
		g.drawString("x "+coins,140,235);
		
		
		for(Achievement a : achievements){
			a.render(container,maingame,g);
		}
	}

	

	@Override
	public int getID() {
		return 2;
	}

	public void unlock(Achievement ach2) {
		ach2.unlocked = true;
	}

}
