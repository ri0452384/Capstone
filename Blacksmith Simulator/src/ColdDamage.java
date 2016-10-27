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
				break;
			}case 1:{
				minAdd = 6;
				maxAdd = 15;
				break;
			}case 2:{
				minAdd = 10;
				maxAdd = 23;
				break;
			}case 3:{
				minAdd = 14;
				maxAdd = 32;
				break;
			}case 4:{
				minAdd = 17;
				maxAdd = 40;
				break;
			}case 5:{
				minAdd = 22;
				maxAdd = 50;
				break;
			}case 6:{
				minAdd = 26;
				maxAdd = 60;
				break;
			}case 7:{
				minAdd = 31;
				maxAdd = 73;
				break;
			}case 8:{
				minAdd = 37;
				maxAdd = 87;
				break;
			}
			default:{
				minAdd = 0;
				maxAdd = 0;
				break;
			}
			
		}
		
		wep.setName(possiblePrefixes[fin] +" "+ wep.getName());
		
		this.setTooltip("\nAdds "+minAdd + " - " + maxAdd +" cold damage");
		
		wep.setMinPhysDamage(wep.getMinEleDamage() + minAdd);
		wep.setMaxPhysDamage(wep.getMaxEleDamage() + maxAdd);
		wep.textColor = Color.blue;
		//wep.weaponText = "\nDamage: " + wep.getMinPhysDamage() +" - " + wep.getMaxPhysDamage();
		wep.weaponText += this.getTooltip();
		System.out.println(wep);
	}
}
