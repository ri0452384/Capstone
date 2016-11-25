import java.util.Random;

import org.newdawn.slick.Color;


public class LightningDamage extends Prefix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possiblePrefixes = new String[] {"Heated","Smoldering","Smoking"
		,"Burning","Flaming","Scorching","Incinerating","Blasting","Cremating"};
	
	LightningDamage(){
		super();
		this.setAffixName("LightningDamage");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possiblePrefixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 1;
				maxAdd = 10;
				break;
			}case 1:{
				minAdd = 1;
				maxAdd = 43;
				break;
			}case 2:{
				minAdd = 2;
				maxAdd = 66;
				break;
			}case 3:{
				minAdd = 2;
				maxAdd = 92;
				break;
			}case 4:{
				minAdd = 3;
				maxAdd = 115;
				break;
			}case 5:{
				minAdd = 4;
				maxAdd = 145;
				break;
			}case 6:{
				minAdd = 4;
				maxAdd = 174;
				break;
			}case 7:{
				minAdd = 5;
				maxAdd = 211;
				break;
			}case 8:{
				minAdd = 6;
				maxAdd = 250;
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
		
		
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" lightning damage");
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
