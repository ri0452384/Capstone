import java.util.Random;

import org.newdawn.slick.Color;


public class ReduceArmor extends Suffix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of the Pugilist"," of the Brawler"," of Boxer"
		," of Combatant"," of the Gladiator"," of the Duelist"};
	
	ReduceArmor(){
		super();
		this.setAffixName("ReduceArmor");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 5;
				maxAdd = 7;
				break;
			}case 1:{
				minAdd = 8;
				maxAdd = 9;
				break;
			}case 2:{
				minAdd = 10;
				maxAdd = 11;
				break;
			}case 3:{
				minAdd = 12;
				maxAdd = 13;
				break;
			}case 4:{
				minAdd = 14;
				maxAdd = 15;
				break;
			}case 5:{
				minAdd =16;
				maxAdd = 17;
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
		this.setTooltip("\n"+minAdd +"% reduced enemy armor");
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
