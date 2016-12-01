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

public class Miner {

	// animation variables for the player
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private Animation currentAnimation;

	MineLevel level;
	private SpriteSheet downSprite;
	private SpriteSheet upSprite;
	private SpriteSheet leftSprite;
	private SpriteSheet rightSprite;

	protected Shape playerBox;

	protected float vX;
	protected float vY;

	protected Vector2f vector;

	public Miner() throws SlickException {
		downSprite = new SpriteSheet("Images/downMineSprite.png", 32, 32);
		upSprite = new SpriteSheet("Images/downMineSprite.png", 32, 32);
		leftSprite = new SpriteSheet("Images/downMineSprite.png", 32, 32);
		rightSprite = new SpriteSheet("Images/downMineSprite.png", 32, 32);

		level = new MineLevel();
		vector = new Vector2f();

		vector.x = 700;
		vector.y = 450;

		downAnimation = new Animation(downSprite, 100);
		upAnimation = new Animation(upSprite, 100);
		leftAnimation = new Animation(leftSprite, 100);
		rightAnimation = new Animation(rightSprite, 100);
		currentAnimation = downAnimation;
		playerBox = new Rectangle((int) vector.x, (int) vector.y, downAnimation
				.getCurrentFrame().getWidth(), downAnimation.getCurrentFrame()
				.getHeight());

	}

	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {
		level.init(container, maingame);
	}

	public void render(GameContainer container, StateBasedGame maingame,
			Graphics g) throws SlickException {
		level.render(container,maingame,g);
		container.sleep(50);
		g.setColor(Color.green);
		//currentAnimation.draw(playerBox.getX(), playerBox.getY());
		g.draw(playerBox);
		g.drawAnimation(downAnimation, vector.x, vector.y);//(new Image("miner.png"), vector.x, vector.y);
	}

	private void mineCollisionTest(GameContainer container, int delta) {
		if (container.getInput().isKeyDown(Input.KEY_UP)
				|| container.getInput().isKeyDown(Input.KEY_UP)) {
			vector.y -= vY;
			playerBox.setY(playerBox.getY() - vY);
			if (level.collidesWith(playerBox)) {
				playerBox.setY(playerBox.getY() + vY);
				vector.y += (vY + .1);
				vY = 0;
			}
			vY = 10;
			currentAnimation = upAnimation;
			currentAnimation.start();
			currentAnimation.update(delta);
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)
				|| container.getInput().isKeyDown(Input.KEY_DOWN)) {
			vector.y += vY;
			playerBox.setY(playerBox.getY() + vY);
			if (level.collidesWith(playerBox)) {
				playerBox.setY(playerBox.getY() - vY);
				vector.y -= (vY + .1);
				vY = 0;
			}
			vY = 10;
			currentAnimation = downAnimation;
			currentAnimation.start();
			currentAnimation.update(delta);
		}

		if (container.getInput().isKeyDown(Input.KEY_LEFT)
				|| container.getInput().isKeyDown(Input.KEY_LEFT)) {
			vector.x -= vX;
			playerBox.setX(playerBox.getX() - vX);
			if (level.collidesWith(playerBox)) {
				playerBox.setX(playerBox.getX() + vX);
				vector.x += (vX + .1);
				vX = 0;
			}
			vX = 10;
			currentAnimation = leftAnimation;
			currentAnimation.start();
			currentAnimation.update(delta);
		}

		if (container.getInput().isKeyDown(Input.KEY_RIGHT)
				|| container.getInput().isKeyDown(Input.KEY_RIGHT)) {

			vector.x += vX;
			playerBox.setX(playerBox.getX() + vX);
			if (level.collidesWith(playerBox)) {
				playerBox.setX(playerBox.getX() - vX);
				vector.x -= (vX + .1);
				vX = 0;
			}
			vX = 10;
			currentAnimation = rightAnimation;
			currentAnimation.start();
			currentAnimation.update(delta);
		} else {

			currentAnimation.stop();
		}

		playerBox.setLocation(vector);

	}

	public void update(GameContainer container, StateBasedGame maingame,
			int delta) throws SlickException {
		level.update(container,maingame,delta);
		mineCollisionTest(container, delta);
		tunnelCollisionTest(container,delta);
		this.level.metalCollisionTest(playerBox,(MainGame) maingame);
	}
	
	
	
	private void tunnelCollisionTest(GameContainer container,int delta){
		//TODO insert super mario tunnel sound or something for the lulz
		if(level.atLowerTunnel(playerBox)){
			vector.x = 165;
			vector.y = 	40;
			container.sleep(1000);
		}
		else if(level.atUpperTunnel(playerBox)){
			vector.x = 710;
			vector.y = 45;
			container.sleep(1000);
			
		}
		
		playerBox.setLocation(vector);
		
	}
	
	

}
