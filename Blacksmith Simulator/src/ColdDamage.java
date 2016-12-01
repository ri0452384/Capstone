import java.util.Random;

import org.newdawn.slick.Color;


public class ColdDamage extends Prefix {
	private int minAdd;
	private int maxAdd;
	private static final String[] possiblePrefixes = new String[] {"Frosted","Chilled","Icy"
		,"Frigid","Freezing","Frozen","Glaciated","Polar","Entombing"};
	
	ColdDamage(){
		super();
		this.setAffixName("ColdDamage");
	}
	
	@Override
	void imbue(Weapon wep) {
		
		Random selector = new Random();
		int fin = selector.nextInt(possiblePrefixes.length);
		
		
		switch(fin){
			case 0:{
				minAdd = 1;
				maxAdd = 3;
				super.setMultiplier(0.05);
				break;
			}case 1:{
				minAdd = 6;
				maxAdd = 15;
				super.setMultiplier(0.07);
				break;
			}case 2:{
				minAdd = 10;
				maxAdd = 23;
				super.setMultiplier(0.08);
				break;
			}case 3:{
				minAdd = 14;
				maxAdd = 32;
				super.setMultiplier(0.09);
				break;
			}case 4:{
				minAdd = 17;
				maxAdd = 40;
				super.setMultiplier(0.12);
				break;
			}case 5:{
				minAdd = 22;
				maxAdd = 50;
				super.setMultiplier(0.15);
				break;
			}case 6:{
				minAdd = 26;
				maxAdd = 60;
				super.setMultiplier(0.18);
				break;
			}case 7:{
				minAdd = 31;
				maxAdd = 73;
				super.setMultiplier(0.22);
				break;
			}case 8:{
				minAdd = 37;
				maxAdd = 87;
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
		
		
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" cold damage");
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
