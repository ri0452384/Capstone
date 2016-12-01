import java.util.Random;

import org.newdawn.slick.Color;
/*	
 *  This class is one of the prefixes to be used for the game. It adds flat increase to the physical damage of any weapon
 * 
 * Class created by Rayven Ingles
 * 	All wrongs reversed 2016
 * 
 */

public class FlatPhysicalDamage extends Prefix {
	
	private int minAdd;
	private int maxAdd;
	private static final String[] possiblePrefixes = new String[] {"Glinting","Burnished","Polished"
		,"Honed","Gleaming","Razor Sharp","Tempered","Flaring"};
	
	FlatPhysicalDamage(){
		super();
		this.setAffixName("FlatPhysDamage");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possiblePrefixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 1;
				maxAdd = 3;
				super.setMultiplier(.09);
				break;
			}case 1:{
				minAdd = 4;
				maxAdd = 9;
				super.setMultiplier(.11);
				break;
			}case 2:{
				minAdd = 6;
				maxAdd = 14;
				super.setMultiplier(.13);
				break;
			}case 3:{
				minAdd = 7;
				maxAdd = 18;
				super.setMultiplier(.15);
				break;
			}case 4:{
				minAdd = 9;
				maxAdd = 22;
				super.setMultiplier(.17);
				break;
			}case 5:{
				minAdd = 12;
				maxAdd = 28;
				super.setMultiplier(.19);
				break;
			}case 6:{
				minAdd = 13;
				maxAdd = 32;
				super.setMultiplier(.21);
				break;
			}case 7:{
				minAdd = 16;
				maxAdd = 38;
				super.setMultiplier(.23);
				break;
			}default:{
				minAdd = 0;
				maxAdd = 0;
				super.setMultiplier(.00);
				break;
			}
			
		}
		
		//only increase the base damage first
		wep.setMinPhysDamage(wep.BASE_MIN + minAdd);
		wep.setMaxPhysDamage(wep.BASE_MAX + maxAdd);
		
		
		
		if(wep.alreadyHas("PercentDamage")){
			//assuming the weapon already has the percent damage modifier, apply the percent increase here
			int mult=100;
			for(Prefix pref: wep.getPrefixes()){
				if(pref == null){
					//do nothing
				}
				else if(pref.getAffixName().equals("PercentDamage")){
					mult = ((PercentDamage) pref).multiplier-100;
				}
			}
			 
			wep.setMinPhysDamage((int)(wep.getMinPhysDamage() *mult/100));
			wep.setMaxPhysDamage((int)(wep.getMaxPhysDamage() *mult/100));			
		}else{
			//do nothing, base dmg already set before the alreadyHas condition			
		}
		if(! wep.isRare())
			renameWeapon(wep,fin);
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" damage");
		wep.damageText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
		wep.weaponText += this.getTooltip();
		
		//wep.prefixCount++;
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
		
			wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
			wep.textColor = Color.blue;
	}
	
	

}
