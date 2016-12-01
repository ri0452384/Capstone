import java.util.Random;

import org.newdawn.slick.Color;


public class FireResist extends Suffix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of the Whelpling"," of the Salamander"," of the Drake"
		," of the Ifrit"," of the Furnace"," of the Volcano"," of the Magma"," of Ragnaros"};
	
	FireResist(){
		super();
		this.setAffixName("FireResist");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 6;
				maxAdd = 11;
				super.setMultiplier(0.02);
				break;
			}case 1:{
				minAdd = 12;
				maxAdd = 17;
				super.setMultiplier(0.05);
				break;
			}case 2:{
				minAdd = 18;
				maxAdd = 23;
				super.setMultiplier(0.06);
				break;
			}case 3:{
				minAdd = 24;
				maxAdd = 29;
				super.setMultiplier(0.07);
				break;
			}case 4:{
				minAdd = 30;
				maxAdd = 35;
				super.setMultiplier(0.09);
				break;
			}case 5:{
				minAdd = 36;
				maxAdd = 41;
				super.setMultiplier(0.11);
				break;
			}case 6:{
				minAdd = 42;
				maxAdd = 45;
				super.setMultiplier(0.13);
				break;
			}case 7:{
				minAdd = 46;
				maxAdd = 48;
				super.setMultiplier(0.15);
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		
		minAdd = minAdd + selector.nextInt(maxAdd-minAdd);
		if(! wep.isRare())
			renameWeapon(wep,fin);
		this.setTooltip("\n"+minAdd +"% to fire resistance");
		wep.weaponText += this.getTooltip();
		 
		//wep.suffixCount++;
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
			wep.setName(wep.getName()+ possibleSuffixes[fin]);
			wep.textColor = Color.blue;
	}
	
	
}
