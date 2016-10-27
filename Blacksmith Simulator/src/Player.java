import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class Player {

	//animation variables for the player
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private Animation currentAnimation;
	
	
	private SpriteSheet upSprite;
	private SpriteSheet downSprite;
	private SpriteSheet leftSprite;
	private SpriteSheet rightSprite;
	protected Vector2f vector;
	
	//used for collision testing
	protected Shape playerBox;
	private StaticLevel level;
	//private int iterations;
	protected float vX;
	protected float vY;
	
	public Player(StaticLevel lvl) throws SlickException{
		
		downSprite = new SpriteSheet("/Images/downSprite.png", 57, 59);
		upSprite = new SpriteSheet("Images/upSprite.png", 57,59);
		leftSprite = new SpriteSheet("Images/leftSprite.png", 57,56);
		rightSprite = new SpriteSheet("Images/rightSprite.png", 57,59);
		this.level = lvl;
	}
	
	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		
		// TODO Auto-generated method stub
		vector = new Vector2f();
		level = new StaticLevel();
		vector.set(400, 300);
		vX = 10;
		vY = 10;
		level.init(container,maingame);
		downAnimation = new Animation(downSprite,200);
		upAnimation = new Animation(upSprite,200);
		leftAnimation = new Animation(leftSprite, 200);
		rightAnimation = new Animation(rightSprite,200);
		currentAnimation = downAnimation;
		playerBox = new Rectangle(vector.x,vector.y,downAnimation.getCurrentFrame().getWidth(),downAnimation.getCurrentFrame().getHeight());
		
	}
	
	
	public void update(GameContainer container, StateBasedGame maingame, int delta)
			throws SlickException {
		
		
		
		if(container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_UP)){
			vector.y -= vY;
			playerBox.setY(playerBox.getY() - vY);
			if(level.collidesWith(playerBox)){
				playerBox.setY(playerBox.getY() + vY);
				vector.y += (vY + .1);
				vY = 0;
			}
			vY = 10;
			currentAnimation = upAnimation;
		}
		if(container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_DOWN)){
			vector.y += vY;
			playerBox.setY(playerBox.getY() + vY);
			if(level.collidesWith(playerBox)){
				playerBox.setY(playerBox.getY() - vY);
				vector.y -= (vY + .1);
				vY = 0;
			}
			vY = 10;
			currentAnimation = downAnimation;
		}
		
		if(container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_LEFT)){
			vector.x -= vX;
			playerBox.setX(playerBox.getX() - vX);
			if(level.collidesWith(playerBox)){
				playerBox.setX(playerBox.getX() + vX);
				vector.x += (vX + .1);
				vX = 0;
			}
			vX = 10;
			currentAnimation = leftAnimation;		
		}
		
		if(container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_RIGHT)){
			
			vector.x += vX;
			playerBox.setX(playerBox.getX() + vX);
			if(level.collidesWith(playerBox)){
				playerBox.setX(playerBox.getX() - vX);
				vector.x -= (vX + .1);
				vX = 0;
			}
			vX = 10;
			currentAnimation = rightAnimation;
		}
		
		playerBox.setLocation(vector);
		
	}

	
	public void render(GameContainer container, StateBasedGame maingame, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		container.sleep(50);
		g.setColor(Color.green);
		//g.draw(playerBox);
		currentAnimation.draw(playerBox.getX(), playerBox.getY());
	}

	
	
	
}
