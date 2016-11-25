import java.util.Random;

import org.newdawn.slick.Color;


public class LightningResist extends Suffix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possibleSuffixes = new String[] {" of the Cloud"," of the Squall"," of the Storm"
		," of the Thunderhead"," of the Tempest"," of the Maelstrom"," of the Lightning"," of Thorim"};
	
	LightningResist(){
		super();
		this.setAffixName("LightningResist");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possibleSuffixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 6;
				maxAdd = 11;
				break;
			}case 1:{
				minAdd = 12;
				maxAdd = 17;
				break;
			}case 2:{
				minAdd = 18;
				maxAdd = 23;
				break;
			}case 3:{
				minAdd = 24;
				maxAdd = 29;
				break;
			}case 4:{
				minAdd = 30;
				maxAdd = 35;
				break;
			}case 5:{
				minAdd = 36;
				maxAdd = 41;
				break;
			}case 6:{
				minAdd = 42;
				maxAdd = 45;
				break;
			}case 7:{
				minAdd = 46;
				maxAdd = 48;
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
		this.setTooltip("\n"+minAdd +"% to lightning resistance");
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
