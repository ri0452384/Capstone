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
				break;
			}case 1:{
				minAdd = 4;
				maxAdd = 9;
				break;
			}case 2:{
				minAdd = 6;
				maxAdd = 14;
				break;
			}case 3:{
				minAdd = 7;
				maxAdd = 18;
				break;
			}case 4:{
				minAdd = 9;
				maxAdd = 22;
				break;
			}case 5:{
				minAdd = 12;
				maxAdd = 28;
				break;
			}case 6:{
				minAdd = 13;
				maxAdd = 32;
				break;
			}case 7:{
				minAdd = 16;
				maxAdd = 38;
				break;
			}default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" damage");
		//only increase the base damage first
		wep.setMinPhysDamage(wep.BASE_MIN + minAdd);
		wep.setMaxPhysDamage(wep.BASE_MAX + maxAdd);
		
		wep.textColor = Color.blue;
		
		if(wep.alreadyHas("PercentDamage")){
			//assuming the weapon already has the percent damage modifier, apply the percent increase here
			int mult=1;
			for(Prefix pref: wep.getPrefixes()){
				if(pref !=null && pref.getAffixName().equals("PercentDamage"))
					mult = ((PercentDamage) pref).multiplier;
				break;
			}
			 
			wep.setMinPhysDamage((int)(wep.getMinPhysDamage() *(mult))/100);
			wep.setMaxPhysDamage((int)(wep.getMaxPhysDamage() *(mult))/100);
			
		}else{
			
			//this is the part where the new damage values are calculated w/o percent dmg modifier
			wep.setMinPhysDamage(wep.BASE_MIN + minAdd);
			wep.setMaxPhysDamage(wep.BASE_MAX + maxAdd);
		}
			
			wep.damageText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
			wep.weaponText += this.getTooltip();
		
		System.out.println(wep);
		
		
	}
	
	

}
