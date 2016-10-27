import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//TODO need to restrict Affixes to only one type of affix per weapon.
//code should be implemented here.

public abstract class Weapon {
	
	protected Color textColor;
	//base values of a weapon, useful for scouring and percent based damage
	//only set once, as soon as the concrete weapon type is instantiated
	protected String BASE_NAME;
	protected  int BASE_MIN ;
	protected  int BASE_MAX ;
	//weapons can have physical damage, set to the base values at start
	private int minPhysDamage;
	private int maxPhysDamage;
	//weapons can also have elemental damage, not shown in damage text
	private int minEleDamage;
	private int maxEleDamage;
	//array of prefixes inherent to all weapons
	protected Prefix[] prefixes;
	protected int prefixCount;
	//array of suffixes inherent to all weapons
	protected Suffix[] suffixes;
	protected int suffixCount;
	//x and y where the weapon is displayed on the screen
	protected int x;
	protected int y;
	//this is for the weapon tool tips below the damage text
	protected String weaponText;
	//displayed immediately below the weapon name
	protected String damageText;
	//base name of a weapon
	private String name;
	
	//crafting cost of the weapon will be in terms of metal ores
	Metal craftingCost;
	
	//selling price of the weapon must be dependent on damage, and other magical properties
	double sellPrice;
	
	//constructor requires minimum dmg, max dmg, weapon name, and x, y coordinates where they can be displayed once crafted
	Weapon(int minimum, int maximum,String name,int x, int y){
		BASE_NAME = name;
		BASE_MIN = minimum;
		BASE_MAX = maximum;
		this.setMinPhysDamage(minimum);
		this.setMaxPhysDamage(maximum);
		setMinEleDamage(0);
		setMaxEleDamage(0);
		this.name = name;
		this.weaponText = "\n-------------";
		this.damageText = "";
		this.x = x;
		this.y = y;
		prefixes = new Prefix[3];
		suffixes = new Suffix[3];
		
		textColor = Color.white;
		prefixCount = 0;
		suffixCount = 0;
	}
	
	public Prefix[] getPrefixes(){
		return prefixes;
	} 
	
	public Suffix[] getSuffixes(){
		return suffixes;
	} 
	
	public abstract void init(GameContainer arg0, StateBasedGame arg1)	throws SlickException;
	
	public abstract void render(GameContainer container, StateBasedGame maingame, Graphics g);
	
	public abstract void update(GameContainer container, StateBasedGame maingame, int delta)throws SlickException;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	

	public int getMinPhysDamage() {
		return minPhysDamage;
	}

	public void setMinPhysDamage(int minPhysDamage) {
		this.minPhysDamage = minPhysDamage;
	}

	public int getMaxPhysDamage() {
		return maxPhysDamage;
	}

	public void setMaxPhysDamage(int maxPhysDamage) {
		this.maxPhysDamage = maxPhysDamage;
	}
	
	

	public int getMinEleDamage() {
		return minEleDamage;
	}

	public void setMinEleDamage(int minEleDamage) {
		this.minEleDamage = minEleDamage;
	}

	public int getMaxEleDamage() {
		return maxEleDamage;
	}

	public void setMaxEleDamage(int maxEleDamage) {
		this.maxEleDamage = maxEleDamage;
	}
	
	public String toString(){
		return this.getName() + this.damageText +  this.weaponText;
	}

	
	boolean alreadyHas(String affixName) {
		//checks whether the item has an existing prefix
		boolean ans = false;
		for(Affix af: prefixes){
			
			if(af == null){
				
				//do nothing
			}
			else if(af.getAffixName().equals(affixName)){
				
				return true;
			}
			
		}
		//checking for the same in suffixes
		for(Affix af: suffixes){
			
			if(af == null){
				
				//do nothing
			}
			else if(af.getAffixName().equals(affixName)){
				
				return true;
			}
			
		}
		return ans;
	}
	
	void removeAffixes(){
		//empties the array of prefixes and suffixes and resets their counts to 0
		for(int i=0;i<prefixes.length;i++){
			prefixes[i] = new NoPrefix();
			prefixes[i].imbue(this);
		}
		prefixCount = 0;
		for(int i = 0; i< suffixes.length;i++){
			suffixes[i] = new NoSuffix();
			suffixes[i].imbue(this);
		}
		suffixCount = 0;
	}



	void addAffix(Affix af) {
		
		if(af instanceof Prefix){
			
			if(! this.alreadyHas(af.getAffixName())){
				prefixes[prefixCount] = (Prefix) af;
				prefixes[prefixCount].imbue(this);
				prefixCount ++;
			}else if(prefixCount >2){
				//do nothing, as the as the weapon has full prefix properties
			}
		}else if(af instanceof Suffix){
			if(! this.alreadyHas(af.getAffixName())){
				suffixes[suffixCount] = (Suffix) af;
				suffixes[suffixCount].imbue(this);
				suffixCount ++;
			}else if(suffixCount >2){
				//do nothing, as the weapon has full suffix properties
			}
		}
	}
}
