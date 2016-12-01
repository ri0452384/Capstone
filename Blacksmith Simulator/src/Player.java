import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player {

	// animation variables for the player
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	protected Animation currentAnimation;

	private SpriteSheet upSprite;
	private SpriteSheet downSprite;
	private SpriteSheet leftSprite;
	private SpriteSheet rightSprite;
	protected Vector2f vector;

	// used for collision testing
	protected Shape playerBox;
	HouseLevel level;
	// private int iterations;
	protected float vX;
	protected float vY;
	
	int mineTimer;

	public Player() throws SlickException {

		downSprite = new SpriteSheet("Images/downSprite.png", 57, 59);
		upSprite = new SpriteSheet("Images/upSprite.png", 57, 59);
		leftSprite = new SpriteSheet("Images/leftSprite.png", 57, 56);
		rightSprite = new SpriteSheet("Images/rightSprite.png", 57, 59);
		level = new HouseLevel();

	}

	public void init(GameContainer container, StateBasedGame maingame)
			throws SlickException {

		vector = new Vector2f();
		vector.set(400, 300);
		vX = 10;
		vY = 10;
		level.init(container, maingame);
		downAnimation = new Animation(downSprite, 100);
		upAnimation = new Animation(upSprite, 100);
		leftAnimation = new Animation(leftSprite, 100);
		rightAnimation = new Animation(rightSprite, 100);
		currentAnimation = downAnimation;
		playerBox = new Rectangle(vector.x, vector.y, downAnimation
				.getCurrentFrame().getWidth(), downAnimation.getCurrentFrame()
				.getHeight());
		mineTimer = 0;
	}

	private void houseCollisionTest(GameContainer container, int delta) {
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
		
			mineTimer += delta;
		
		if(mineTimer >= 25000){
			level.mineOpen = true;
			level.mineDoor = new Polygon(new float[]{160,70,
					160,125,
					195,125,
					195,70});
		}
		
		houseCollisionTest(container, delta);

	}

	public void render(GameContainer container, StateBasedGame maingame,
			Graphics g) throws SlickException {

		container.sleep(50);
		g.setColor(Color.green);
		currentAnimation.draw(playerBox.getX(), playerBox.getY());
	}

	public void facedown() {
		currentAnimation = downAnimation;
		currentAnimation.start();
		currentAnimation.stop();

	}

}
