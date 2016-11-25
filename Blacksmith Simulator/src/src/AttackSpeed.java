import java.util.Random;

import org.newdawn.slick.Color;


public class AttackSpeed extends Suffix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of Skill"," of Ease"," of Mastery"
		," of Renown"," of Acclaim"," of Fame"," of Infamy"," of Celebration"};
	
	AttackSpeed(){
		super();
		this.setAffixName("AttackSpeed");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 5;
				maxAdd = 7;
				super.setMultiplier(0.07);
				break;
			}case 1:{
				minAdd = 8;
				maxAdd = 10;
				super.setMultiplier(0.07);
				break;
			}case 2:{
				minAdd = 11;
				maxAdd = 13;
				super.setMultiplier(0.09);
				break;
			}case 3:{
				minAdd = 14;
				maxAdd = 16;
				super.setMultiplier(0.10);
				break;
			}case 4:{
				minAdd = 17;
				maxAdd = 19;
				super.setMultiplier(0.10);
				break;
			}case 5:{
				minAdd = 20;
				maxAdd = 22;
				super.setMultiplier(0.10);
				break;
			}case 6:{
				minAdd = 23;
				maxAdd = 25;
				super.setMultiplier(0.15);
				break;
			}case 7:{
				minAdd = 26;
				maxAdd = 29;
				super.setMultiplier(0.19);
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
		this.setTooltip("\n"+minAdd +"% faster attack speed");
		wep.weaponText += this.getTooltip();
		System.out.println(wep);
		//wep.suffixCount++;
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
			wep.setName(wep.getName()+ possibleSuffixes[fin]);
			wep.textColor = Color.blue;
	}
	
	
}
