import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//TODO need to restrict Affixes to only one type of affix per weapon.
//code should be implemented here.

public abstract class Weapon {
	//all weapons share the same prefixes but suffixes may vary depending on weapon type
	 final String[] possibleRarePrefixes = new String[] {"Agony", "Apocalypse", "Armageddon", "Beast",
		"Behemoth", "Blight", "Blood", "Bramble", "Brimstone", "Brood", "Carrion", "Cataclysm", "Chimeric", 
		"Corpse", "Corruption", "Damnation", "Death", "Demon", "Dire", "Dragon", "Dread", "Doom", "Dusk", 
		"Eagle", "Empyrean", "Fate", "Foe", "Gale", "Ghoul", "Gloom", "Glyph", "Golem", "Grim", "Hate", 
		"Havoc", "Honour", "Horror", "Hypnotic", "Kraken", "Loath", "Maelstrom", "Mind", "Miracle", "Morbid", 
		"Oblivion", "Onslaught", "Pain", "Pandemonium", "Phoenix", "Plague", "Rage", "Rapture", "Rune", "Skull", 
		"Sol", "Soul", "Sorrow", "Spirit", "Storm", "Tempest", "Torment", "Vengeance", "Victory", "Viper", "Vortex", 
		"Woe", "Wrath"};
	 boolean renamed;
	 boolean isRare;
	 String[] possibleRareSuffixes;
	 
	 //all possible weapon affixes initialized here
	 ArrayList<Prefix> pre;
	 ArrayList<Suffix> suf;
	 
	 	Accuracy accu;
	 	AttackSpeed atkspd;
	 	ColdDamage cold;
	 	ColdResist cores;
	 	CriticalChance crit;
	 	FireDamage fire;
	 	FireResist fires;
	 	FlatPhysicalDamage flat;
	 	LightningDamage light;
	 	LightningResist lires;
	 	PercentDamage perc;
		ReduceArmor redarm;
		
		
		
		Random rand;
	 
	 
	
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
	Resource craftingCost;
	
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
		
		prefixes = new Prefix[3];
		suffixes = new Suffix[3];
		isRare = false;
		renamed = false;
		textColor = Color.white;
		prefixCount = 0;
		suffixCount = 0;
		
		this.x = x;
		this.y = y;
		pre = new ArrayList<Prefix>();
		suf = new ArrayList<Suffix>();
		resetPossibleAffixes();
	}
	
	void resetPossibleAffixes(){
		//instantiate all possible magical properties here
				flat = new FlatPhysicalDamage();
				perc = new PercentDamage();
				cold = new ColdDamage();
				crit = new CriticalChance();
				fire = new FireDamage();
				light = new LightningDamage();
				rand = new Random();
				accu = new Accuracy();
				atkspd = new AttackSpeed();
				redarm = new ReduceArmor();
				fires = new FireResist();
				cores = new ColdResist();
				lires = new LightningResist();
				
				suf.add(accu);
				suf.add(atkspd);
				suf.add(cores);
				suf.add(crit);
				suf.add(fires);
				suf.add(lires);
				suf.add(redarm);
				
				
				pre.add(cold);
				pre.add(fire);
				pre.add(flat);
				pre.add(light);
				pre.add(perc);				
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
		return this.damageText +  this.weaponText;
	}

	public String name(){
		return this.getName();
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
		this.setName(BASE_NAME);
		weaponText = "\n-------------";
		damageText = "\nDamage: " + BASE_MIN +" - " + BASE_MAX;
		renamed = false;
		isRare = false;
		textColor = Color.white;
		prefixCount = 0;
		suffixCount = 0;
		prefixes = new Prefix[3];
		suffixes = new Suffix[3];
		this.setMinPhysDamage(BASE_MIN);
		this.setMaxPhysDamage(BASE_MAX);
		resetPossibleAffixes();
	
	}



	void addAffix() {
		
		if(this.prefixCount > 2 && this.suffixCount > 2){
			return;
		}
		// 0 = suffix 	1 = prefix
		int choice = rand.nextInt(2);
		Prefix p = null;
		Suffix s = null;
		//if item already has 3 prefixes but has a room for a suffix
		if(prefixCount ==3){
			choice = 0;
		}
		//if item already has 3 suffixes but has a room for a prefix
		if (suffixCount == 3){
			choice = 1;
		}
		
		
		if(choice == 1){
			if(prefixCount > 0){
				isRare = true;
				makeRare();
				}
			if(prefixCount < 3){
				int i = rand.nextInt(pre.size());
				p = pre.get(i);
				prefixes[prefixCount] = p;
				prefixes[prefixCount++].imbue(this);
				pre.remove(i);
			}
		}else{
			if(suffixCount > 0){
				isRare = true;
				makeRare();
				}
			if(suffixCount <3){
				int i = rand.nextInt(suf.size());
				s = suf.get(i);
				suffixes[suffixCount] = s;
				suffixes[suffixCount++].imbue(this);
				suf.remove(i);
			}
			
		}		
	}
	
	private void makeRare() {
		//guarantees that this is invoked only once
		if(! this.renamed){
			int pre = (int)(Math.random()*this.possibleRarePrefixes.length);
			this.textColor = Color.orange;
			int suf = (int)(Math.random()*this.possibleRareSuffixes.length);
			this.setName(this.possibleRarePrefixes[pre] + " "+ this.possibleRareSuffixes[suf] );
			this.renamed = true;
		}
		
	}

	boolean isRare()
	{
		return isRare;
	}
}
