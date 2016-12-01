import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class MineLevel extends StaticLevel {

	private Image background;
	private Shape mineExit;
	private Shape lowerTunnel;
	private Shape upperTunnel;
	private Shape pool;
	private Shape rock;
	private Shape pillar;
	
	
	ArrayList<Resource> metals;
	private int maxResource;

	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		float[] polygonPoints = new float[] {
					150, 0,
					150, 190,
					75,195,
					75, 120,
					30, 120, 
					35,540,
					385,540,
					740,540,
					735,315,
					660,310,
					635,280,
					580,270,
					593,198,
					636,194,
					675,165,
					735,165,
					735,120,
					450,125,
					450,85,
					515,85,
					520,100,
					775,95,
					770,30,
					750,30,
					750,0,
					700,0,
					700,30,
					395,35,
					395,75,
					400,75,
					405,125,
					385,125,
					375,110,
					360,105,
					350,40,
					290,40,
					290,55,
					265,55,
					260,80,
					205,85,
					200,195,
					390,195,
					400,540,
					390,540,
					385,210,
					200,210,
					200,75,
					255,75,
					255,30,
					205,30,
					205,0
				};
		levelBase = new Polygon(polygonPoints);
		polygonPoints = new float[]{
				85,240,
				85,375,
				255,375,
				255,400,
				240,410,
				240,455,
				290,455,
				295,415,
				285,390,
				310,385,
				310,295,
				155,290,
				150,240
		};
		pool = new Polygon(polygonPoints);
		rock = new Polygon(new float[]{291,110,	289,148,	315,148,	314,104});
		pillar = new Polygon(new float[]{499,489,	554,489,	550,374,	503,376});
		background = new Image("Images/mines.png");
		mineExit = new Rectangle(670, 520, 32, 64);
		lowerTunnel = new Rectangle(710,0,32,16);
		upperTunnel = new Rectangle(165,0,32,16);
		//maximum number of possible resource nodes in the mines during the start
		maxResource = 15;
		refillMetals();
	}
	
	void refillMetals(){
		if(metals !=null)
			metals.clear();
		
			metals = new ArrayList<Resource>();
		
		int x,y;
		for(int i=0;i<maxResource;){
			Resource met;
			x =(int) (Math.random()*800);
			y = (int) (Math.random()*600);
			int choice = (int)(Math.random()*2);
			if(choice == 1){
				met = (Resource) new Iron(x,y);
			}else{
				met = (Resource) new Log(x,y);
			}
			
			if(! collidesWith(met.rect) && levelBase.contains(met.rect) && ! pool.contains(met.rect) && ! rock.contains(met.rect) && ! pillar.contains(met.rect)){			
				metals.add(met);
				i++;
			}
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {

	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		g.setColor(Color.cyan);
		for(Resource m:metals){
			m.render(container, sbg, g);
		}
		//only draw the following when performing level collision testing(dev phase)
		//g.draw(levelBase);
		//g.draw(pool);
		//g.draw(rock);
		//g.draw(pillar);
		//g.draw(lowerTunnel);
		//g.draw(upperTunnel);

	}

	public boolean collidesWith(Shape player) {
		//if map gets edited, make sure to redraw the shapes and add them on this logic to maintain proper collision
		return levelBase.intersects(player) || pool.intersects(player) || rock.intersects(player) || pillar.intersects(player);
	}
	
	public boolean metalCollisionTest(Shape player,MainGame main){
		for(Resource met: metals){
			if(met.rect.intersects(player)){
				if(met instanceof Iron){
					((MainGame)main).menu.ironCount ++;
					((MainGame)main).menu.resources++;
					}
				if(met instanceof Log){
					((MainGame)main).menu.logCount ++;
					((MainGame)main).menu.resources++;
				}
				metals.remove(met);
				return true;
			}
		}
		return false;
	}
	
	

	public boolean exitMine(Shape player) {

		return mineExit.intersects(player);

	}
	
	public boolean atLowerTunnel(Shape player){
		return lowerTunnel.intersects(player);
	}
	public boolean atUpperTunnel(Shape player){
		return upperTunnel.intersects(player);
	}

}
