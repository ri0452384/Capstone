
public abstract class Affix{

	private String properties;
	private String affixName;
	protected String tooltip;
	private double multiplier;
	
	public Affix(){
		properties = "";
		affixName = "";
		tooltip = "";
		multiplier = 0.0;
	}
	
	public String getAffixName() {
		return affixName;
	}
	public void setAffixName(String affix) {
		this.affixName = affix;
	}
	
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
	public double getMultiplier(){
		return this.multiplier;
	}

	public void setMultiplier( double m){
		this.multiplier = m;
	}
	
	abstract void imbue(Weapon wep);
	
	abstract void renameWeapon(Weapon wep,int fin);
	
	abstract String getTooltip();
	
	abstract void setTooltip(String tooltip) ;
}
