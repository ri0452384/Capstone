import java.util.Random;

import org.newdawn.slick.Color;


public class Accuracy extends Suffix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of Calm"," of Steadiness"," of Accuracy"
		," of Precision"," of the Sniper"," of the Marksman"," of the Deadeye"," of the Ranger"};
	
	Accuracy(){
		super();
		this.setAffixName("Accuracy");
	}
	
	
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 5;
				maxAdd = 15;
				super.setMultiplier(0.03);
				break;
			}case 1:{
				minAdd = 16;
				maxAdd = 60;
				super.setMultiplier(0.06);
				break;
			}case 2:{
				minAdd = 61;
				maxAdd = 100;
				super.setMultiplier(0.09);
				break;
			}case 3:{
				minAdd = 101;
				maxAdd = 130;
				super.setMultiplier(0.12);
				break;
			}case 4:{
				minAdd = 131;
				maxAdd = 165;
				super.setMultiplier(0.13);
				break;
			}case 5:{
				minAdd = 166;
				maxAdd = 200;
				super.setMultiplier(0.13);
				break;
			}case 6:{
				minAdd = 201;
				maxAdd = 250;
				super.setMultiplier(0.14);
				break;
			}case 7:{
				minAdd = 251;
				maxAdd = 320;
				super.setMultiplier(0.17);
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		
		minAdd = minAdd + selector.nextInt(maxAdd-minAdd);
		
		this.setTooltip("\nAdds "+minAdd +" accuracy rating");
		wep.weaponText += this.getTooltip();
		if(! wep.isRare())
			renameWeapon(wep,fin);
		//wep.suffixCount++;
		System.out.println(wep);
	}

	void renameWeapon(Weapon wep,int fin){
			wep.setName(wep.getName()+ possibleSuffixes[fin]);
			wep.textColor = Color.blue;
	}
	
}
