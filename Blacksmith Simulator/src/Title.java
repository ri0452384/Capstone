import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		tip="";
		background = new Image("Images/title.png");
		newGame = new Rectangle(301,391,245,36);
		cont = new Rectangle(304,429,240,37);
		credits = new Rectangle(322,471,199,31);

	}

	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		background.draw(0,0);
		if(newGame.contains(mouseX,mouseY)){
			g.drawImage(new Image("Images/new_hover.png"),newGame.getMinX(),newGame.getMinY());
		}
		
		if(cont.contains(mouseX, mouseY)){
			g.drawImage(new Image("Images/continue_hover.png"),cont.getMinX(),cont.getMinY());
		}
		if(credits.contains(mouseX, mouseY)){
			g.drawImage(new Image("Images/credits_hover.png"),credits.getMinX(),credits.getMinY());
		}
		g.drawString(tip, 390, 550);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(newGame.contains(mouseX,mouseY)){
				
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
				maingame.enterState(0,new FadeOutTransition(),new FadeInTransition());
			}
			if(cont.contains(mouseX,mouseY)){
				try{
				((MainGame)maingame).menu.load((MainGame)maingame);
				}catch(Exception e){
					tip="save.bss file not found! creating new game...";
					this.render(container, maingame, container.getGraphics());
					maingame.getContainer().sleep(300);
					((MainGame)maingame).menu = new Menu();
					maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
				}
				maingame.enterState(1,new FadeOutTransition(),new FadeInTransition());
			}
		}
		
		

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

}
