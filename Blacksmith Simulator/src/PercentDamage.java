import java.util.Random;

import org.newdawn.slick.Color;
/*	
 *  This class is one of the prefixes to be used for the game. It adds percent based increase to the physical damage of any weapon
 * 
 * Class created by Rayven Ingles
 * 	All wrongs reversed 2016
 * 
 * 
 */

public class PercentDamage extends Prefix {
	protected int multiplier;
	private int minAdd;
	private int maxAdd;
	private static final String[] possiblePrefixes = new String[] {"Heavy","Serrated","Wicked"
		,"Vicious","Bloodthirsty","Cruel","Tyrannical","Merciless"};
	
	
	PercentDamage(){
		super();
		this.setAffixName("PercentDamage");
	}

	
@Override
void imbue(Weapon wep) {
	
	Random selector = new Random();
	
	int fin = selector.nextInt(possiblePrefixes.length);
	
	switch(fin){
		case 0:{
			minAdd = 40;
			maxAdd = 49;
			break;
		}case 1:{
			minAdd = 50;
			maxAdd = 64;
			break;
		}case 2:{
			minAdd = 65;
			maxAdd = 84;
			break;
		}case 3:{
			minAdd = 85;
			maxAdd = 109;
			break;
		}case 4:{
			minAdd = 110;
			maxAdd = 134;
			break;
		}case 5:{
			minAdd = 135;
			maxAdd = 154;
			break;
		}case 6:{
			minAdd = 155;
			maxAdd = 169;
			break;
		}case 7:{
			minAdd = 170;
			maxAdd = 179;
			break;
		}default:{
			minAdd = 0;
			maxAdd = 0;
			break;
		}
		
	}
	wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
	
	multiplier = 100 + minAdd + selector.nextInt(maxAdd-minAdd);
	
	this.setTooltip("\n"+(multiplier - 100)+"% " + "increased physical damage");
	System.out.println(multiplier);
	wep.setMinPhysDamage((int)(wep.getMinPhysDamage() *(multiplier))/100);
	wep.setMaxPhysDamage((int)(wep.getMaxPhysDamage() *(multiplier))/100);
	wep.textColor = Color.blue;
	wep.damageText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
	wep.weaponText += this.getTooltip();
	System.out.println(wep);
	
	
}

}
