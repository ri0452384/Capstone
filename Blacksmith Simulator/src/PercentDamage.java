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
	multiplier = 100;
	Random selector = new Random();
	
	int fin = selector.nextInt(possiblePrefixes.length);
	
	switch(fin){
		case 0:{
			minAdd = 40;
			maxAdd = 49;
			super.setMultiplier(0.10);
			break;
		}case 1:{
			minAdd = 50;
			maxAdd = 64;
			super.setMultiplier(0.15);
			break;
		}case 2:{
			minAdd = 65;
			maxAdd = 84;
			super.setMultiplier(0.17);
			break;
		}case 3:{
			minAdd = 85;
			maxAdd = 109;
			super.setMultiplier(0.20);
			break;
		}case 4:{
			minAdd = 110;
			maxAdd = 134;
			super.setMultiplier(0.22);
			break;
		}case 5:{
			minAdd = 135;
			maxAdd = 154;
			super.setMultiplier(0.24);
			break;
		}case 6:{
			minAdd = 155;
			maxAdd = 169;
			super.setMultiplier(0.25);
			break;
		}case 7:{
			minAdd = 170;
			maxAdd = 179;
			super.setMultiplier(0.30);
			break;
		}default:{
			minAdd = 0;
			maxAdd = 0;
			break;
		}
		
	}
	
	
	multiplier += minAdd + selector.nextInt(maxAdd-minAdd);
	
	double tmp = multiplier / 100;
	
	wep.setMinPhysDamage((int) (wep.getMinPhysDamage()*tmp));
	wep.setMaxPhysDamage((int) (wep.getMaxPhysDamage()*tmp));
	
	wep.damageText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
	this.setTooltip("\n"+(multiplier - 100)+"% " + "increased physical damage");
	wep.weaponText += this.getTooltip();
	if(! wep.isRare())
		renameWeapon(wep,fin);
	
	//wep.prefixCount++;
}


@Override
void renameWeapon(Weapon wep, int fin) {
	
		wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
		wep.textColor = Color.blue;
}

}
