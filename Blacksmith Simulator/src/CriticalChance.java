import java.util.Random;

import org.newdawn.slick.Color;


public class CriticalChance extends Suffix {

	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of Needling"," of Stinging"," of Piercing"
		," of Rupturing"," of Penetrating"," of Incision"};
	
	CriticalChance(){
		super();
		this.setAffixName("CritChance");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 10;
				maxAdd = 14;
				break;
			}case 1:{
				minAdd = 15;
				maxAdd = 19;
				break;
			}case 2:{
				minAdd = 20;
				maxAdd = 24;
				break;
			}case 3:{
				minAdd = 25;
				maxAdd = 29;
				break;
			}case 4:{
				minAdd = 30;
				maxAdd = 34;
				break;
			}case 5:{
				minAdd = 35;
				maxAdd = 38;
				break;
			}default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		
		minAdd = minAdd + selector.nextInt(maxAdd-minAdd);
		
		
		this.setTooltip("\nAdds "+minAdd +" % more critical strike chance");
		wep.weaponText += this.getTooltip();
		if(! wep.isRare())
			renameWeapon(wep,fin);
		System.out.println(wep);
		//wep.suffixCount++;
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
		
			wep.setName(wep.getName()+ possibleSuffixes[fin]);
			wep.textColor = Color.blue;
	}


}
