
import java.util.ArrayList;




import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class NoticeBoard extends BasicGameState {
	
	protected ArrayList<Weapon> requests;
	protected ArrayList<Weapon> possibleWeapons;
	private Image background;
	private Image scroll;
	Shape exitDoor;
	UnicodeFont uni;

	@Override
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		exitDoor = new Rectangle(400,500,240,20);
		requests = new ArrayList<Weapon>();
		possibleWeapons = new ArrayList<Weapon>();
		loadWeapons();
		//generates random weapon requests
		//populateList(maingame);
		background = new Image("noticeboard.png");
		scroll = new Image("scroll.png");
		
		
	}

	private void populateList(StateBasedGame maingame) {
		for(int i = 0;i<5;i++){	
			int choice = (int)(Math.random() * possibleWeapons.size());
			requests.add(possibleWeapons.get(choice));
			possibleWeapons.remove(choice);
		}
		//generates the craft rectangles from workshop again
		((MainGame)maingame).ws.generateCraftRectangles();
		
	}

	private void loadWeapons(){
		possibleWeapons.clear();
		possibleWeapons.add(new BattleAxe());
		possibleWeapons.add(new BattleStaff());
		possibleWeapons.add(new BattleSpear());
		possibleWeapons.add(new BroadAxe());
		possibleWeapons.add(new ConeLance());
		possibleWeapons.add(new DoubleAxe());
		possibleWeapons.add(new DoubleSpear());
		possibleWeapons.add(new EmeraldLance());
		possibleWeapons.add(new Javelin());
		possibleWeapons.add(new LongSword());
		possibleWeapons.add(new Pike());
		possibleWeapons.add(new Rapier());
		possibleWeapons.add(new ShortSword());
		possibleWeapons.add(new WarSpear());
		possibleWeapons.add(new WarStaff());
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		background.draw();
		
		g.setColor(Color.decode("#73040F"));
		g.drawImage(scroll, 50, 150);
		//g.scale(4.5f, 4.5f );
		//g.drawString("Requests by the citizens: ",200,150);
		int col=210;
		for(Weapon wep:requests){
			g.drawString(wep.name(),75,col);
			col +=30;
		}
		//weapon text details
		
		
		//exitdoor details
		g.drawString("*Step away from the board*",exitDoor.getMinX(),exitDoor.getMinY());
		g.draw(exitDoor);
		g.setColor(Color.cyan);
		g.drawString("Tip: To go to your house, step away from the noticeboard.", 75, 580);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			((MainGame)maingame).menu.prevState = getID();
			maingame.enterState(2,new FadeOutTransition(), new FadeInTransition());
		}
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(exitDoor.contains(mouseX, mouseY)){
				maingame.enterState(1,new FadeOutTransition(), new FadeInTransition());
				}
			}
		//to allow the mine timer to continue
		((MainGame)maingame).gs.smith.mineTimer += delta;
		
		if(((MainGame)maingame).gs.smith.mineTimer >= 25000){
			((MainGame)maingame).gs.smith.level.mineOpen = true;
			((MainGame)maingame).gs.smith.level.mineDoor = new Polygon(new float[]{100,25,
					100,100,
					250,100,
					250,25});
		}
		
		if(requests.isEmpty()){
			populateList(maingame);
		}
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

	

}
