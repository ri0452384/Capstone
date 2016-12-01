import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
public class GameOverState extends BasicGameState {
@SuppressWarnings("unused")
private Player smith;
String credit;
String endingStory;
String crafters;
int y_story;
boolean story_completed;
private Image anvil;
private Image hammer;
// dunno what im doing, pero ang idea kay black ra nga background with the anvil and hammer as the design. cant run the new capstone;


@Override
public void init(GameContainer container, StateBasedGame arg1)
throws SlickException {
anvil = new Image("Images/anvil-gold.png");//changed
hammer = new Image("Images/Goldhammer.png"); // changed
story_completed = true;
y_story = 600;
}

@Override
public void update(GameContainer container, StateBasedGame maingame, int delta)
throws SlickException {
	y_story--;
if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
maingame.enterState(6,new FadeOutTransition(),new FadeInTransition());
}
}
@Override
public void render(GameContainer container, StateBasedGame maingame, Graphics g)
throws SlickException {
	
	anvil.draw(50,250);
	hammer.draw(150,150);
credit ="press ESC to go back to main menu.\n"
		+ "\ncredits to the following:"
+ "\nhttp://vignette2.wikia.nocookie.net/steamtradingcards/images"
+ "\n http://www.gold-stater.com/i…/medieval/IMG_0046charlesiv.JPG"
+ "\n https://mir-s3-cdn-cf.behance.net/project_modules/disp/5a6d327765417.560b13b412e67.jpg"
+ "\n Cryptic Studios for the weapon assets via the NeverWinter RPG"
+ "\n Blizzard North for sound files (Diablo I, 1997)";
endingStory = "The humans finally won the war against the monsters! \n "
+ "After providing weapons for countless soldiers, the blacksmith"
+ "\n got recognized across the land and was henceforth bestowed the title"
+ "\n \"The Hammer of Hope.\" \n"
+ "\n In his honor, the King himself decreed that his family be made into a banner,"
+ "\n and hung up the King's hall in the Palace as a reminder of his noble deeds."
+ "\n and so it came to pass, that the smith's deeds would be passed on from generations"
+ "\n and that songs would be sung in his honor for as long as time will tell.";
crafters = "Game Crafted by Codeneira, Ingles, Mañus, and Tolipas of OGTP Games "
		+ "\nAll rights reserved 2016"
		+ "\n OGTP is an independent software company formed only for CMSC 22.";
if(story_completed){
	
	g.setColor(Color.white);//decode("#73040F"));
	g.drawString(endingStory, 50,y_story);
	container.sleep(20);
	if(y_story <=-200){
		story_completed = false;
		((MainGame)maingame).ws.playSound = 0;
	}
}else{
	g.drawString(credit, 50, 30); // i dont know where it displays, change it;
	g.drawString(crafters, 100,520);



}

}
@Override
public int getID() {
// TODO Auto-generated method stub
return 0;
}
}