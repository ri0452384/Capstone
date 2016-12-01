
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Title extends BasicGameState {
	
	private Image background;
	private Rectangle newGame;
	private Rectangle cont;
	private Rectangle credits;
	private String tip;
	private boolean intro_done;
	Sound intro_music;
	Sound SELECT;
	private Image new_hover;
	private Image continue_hover;
	private Image credits_hover;
	int intro_y;
	String intro;
	private int playSound;
	Sound MOVE;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		intro_music = new Sound("Images/wallloop.wav");
		SELECT = new Sound("Images/select.wav");
		tip="";
		background = new Image("Images/title.png");
		newGame = new Rectangle(301,391,245,36);
		cont = new Rectangle(304,429,240,37);
		credits = new Rectangle(322,471,199,31);
		new_hover = new Image("Images/new_hover.png");
		continue_hover=new Image("Images/continue_hover.png");
		credits_hover = new Image("Images/credits_hover.png");
		
		intro = "In a world full of monsters, warriors strive to keep their countrymen safe."
				+ "\n One day, three eerie magical runes appeared in your forge."
				+ "\n These runes give power to your crafts."
				+ "\n On the same day, news started to spread that monsters have grown stronger"
				+ "\n and earned elemental properties.  "
				+ "\nIt is up to you to craft the finest of weapons and help fend off these beasts.";
		intro_y = 600;
		intro_done = false;
		intro_music.loop();
		MOVE = new Sound("Images/choice.wav");
		playSound = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		background.draw(0,0);
		if(newGame.contains(mouseX,mouseY)){
			if(!(playSound==1)){
				MOVE.play();
				playSound = 1;
			}
			
			g.drawImage(new_hover,newGame.getMinX(),newGame.getMinY());
			
		}
		
		if(cont.contains(mouseX, mouseY)){
			if(!(playSound==1)){
				MOVE.play();
				playSound = 1;
			}
			g.drawImage(continue_hover,cont.getMinX(),cont.getMinY());
			
		}
		if(credits.contains(mouseX, mouseY)){
			if(!(playSound==1)){
				MOVE.play();
				playSound = 1;
			}
			((MainGame)maingame).gameover.story_completed = false;
			g.drawImage(credits_hover,credits.getMinX(),credits.getMinY());
			
		}
	
		g.drawString(tip, 390, 550);
		
		if(!intro_done){
			g.drawImage(new Image("workshop.png"),0,0);
			g.drawString(intro, 50, intro_y);
			container.sleep(17);
			if(intro_y <=-200)
				intro_done = true;
		}
		
	}



	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		intro_y --;
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(newGame.contains(mouseX,mouseY)){
				SELECT.play();
				((MainGame)maingame).menu.coins= 0;
				((MainGame)maingame).menu.ironCount= 0;
				((MainGame)maingame).menu.logCount= 0;
				((MainGame)maingame).menu.mineVisits= 0;
				((MainGame)maingame).menu.resources= 0;
				((MainGame)maingame).menu.weaponSold= 0;
				((MainGame)maingame).menu.achievements.clear();
				((MainGame)maingame).menu.fillAchievements();
				
				((MainGame)maingame).notice.populateList(maingame);
				maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
			}
			if(credits.contains(mouseX,mouseY)){
				SELECT.play();
				maingame.enterState(0,new FadeOutTransition(),new FadeInTransition());
			}
			if(cont.contains(mouseX,mouseY)){
				try{
					SELECT.play();
				((MainGame)maingame).menu.load((MainGame)maingame);
				}catch(Exception e){
					tip="save.bss file not found! creating new game...";
					this.render(container, maingame, container.getGraphics());
					((MainGame)maingame).menu = new Menu();
					maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
				}
				maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
			}
		}

	}

	@Override
	public int getID() {
		return 6;
	}

}
