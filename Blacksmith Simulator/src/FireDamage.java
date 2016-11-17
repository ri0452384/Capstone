import java.util.Random;

import org.newdawn.slick.Color;


public class FireDamage extends Prefix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possiblePrefixes = new String[] {"Heated","Smoldering","Smoking"
		,"Burning","Flaming","Scorching","Incinerating","Blasting","Cremating"};
	
	FireDamage(){
		super();
		this.setAffixName("FireDamage");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possiblePrefixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 3;
				maxAdd = 6;
				break;
			}case 1:{
				minAdd = 12;
				maxAdd = 27;
				break;
			}case 2:{
				minAdd = 18;
				maxAdd = 42;
				break;
			}case 3:{
				minAdd = 25;
				maxAdd = 59;
				break;
			}case 4:{
				minAdd = 32;
				maxAdd = 74;
				break;
			}case 5:{
				minAdd = 40;
				maxAdd = 92;
				break;
			}case 6:{
				minAdd = 48;
				maxAdd = 111;
				break;
			}case 7:{
				minAdd = 58;
				maxAdd = 134;
				break;
			}case 8:{
				minAdd = 68;
				maxAdd = 160;
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		
		wep.setMinPhysDamage(wep.getMinEleDamage() + minAdd);
		wep.setMaxPhysDamage(wep.getMaxEleDamage() + maxAdd);
		
		
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" fire damage");
		wep.weaponText += this.getTooltip();
		if(! wep.isRare())
		renameWeapon(wep,fin);
		System.out.println(wep);
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
			wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
			wep.textColor = Color.blue;
	}
}