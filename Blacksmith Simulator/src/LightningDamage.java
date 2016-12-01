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
				super.setMultiplier(0.05);
				break;
			}case 1:{
				minAdd = 1;
				maxAdd = 43;
				super.setMultiplier(0.07);
				break;
			}case 2:{
				minAdd = 2;
				maxAdd = 66;
				super.setMultiplier(0.09);
				break;
			}case 3:{
				minAdd = 2;
				maxAdd = 92;
				super.setMultiplier(0.12);
				break;
			}case 4:{
				minAdd = 3;
				maxAdd = 115;
				super.setMultiplier(0.15);
				break;
			}case 5:{
				minAdd = 4;
				maxAdd = 145;
				super.setMultiplier(0.19);
				break;
			}case 6:{
				minAdd = 4;
				maxAdd = 174;
				super.setMultiplier(0.22);
				break;
			}case 7:{
				minAdd = 5;
				maxAdd = 211;
				super.setMultiplier(0.23);
				break;
			}case 8:{
				minAdd = 6;
				maxAdd = 250;
				super.setMultiplier(0.25);
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		
		wep.setMinEleDamage(wep.getMinEleDamage() + minAdd);
		wep.setMaxEleDamage(wep.getMaxEleDamage() + maxAdd);
		
		
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" lightning damage");
		wep.weaponText += this.getTooltip();
		if(! wep.isRare())
		renameWeapon(wep,fin);
		
	}

	@Override
	void renameWeapon(Weapon wep, int fin) {
			wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
			wep.textColor = Color.blue;
	}
}
