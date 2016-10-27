import org.newdawn.slick.Color;


public class NoPrefix extends Prefix {

	NoPrefix(){
		super();
	}
	
	@Override
	void imbue(Weapon wep) {
		wep.setName(wep.BASE_NAME);
		wep.textColor = Color.white;
		wep.weaponText = "\n-------------";
		wep.damageText = "\nDamage: " + wep.BASE_MIN +" - " + wep.BASE_MAX;
	}

}
