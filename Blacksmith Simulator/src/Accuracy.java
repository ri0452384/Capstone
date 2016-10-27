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
				break;
			}case 1:{
				minAdd = 16;
				maxAdd = 60;
				break;
			}case 2:{
				minAdd = 61;
				maxAdd = 100;
				break;
			}case 3:{
				minAdd = 101;
				maxAdd = 130;
				break;
			}case 4:{
				minAdd = 131;
				maxAdd = 165;
				break;
			}case 5:{
				minAdd = 166;
				maxAdd = 200;
				break;
			}case 6:{
				minAdd = 201;
				maxAdd = 250;
				break;
			}case 7:{
				minAdd = 251;
				maxAdd = 320;
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		wep.setName(wep.getName()+ possibleSuffixes[fin]);
		minAdd = minAdd + selector.nextInt(maxAdd-minAdd);
		this.setTooltip("\nAdds "+minAdd +" accuracy rating");
		
		wep.textColor = Color.blue;
		//wep.weaponText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
		wep.weaponText += this.getTooltip();
		System.out.println(wep);
	}

}
