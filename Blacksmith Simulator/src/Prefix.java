
public abstract class Prefix extends Affix {

	@Override
	abstract void imbue(Weapon wep) ;
	

	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

}
