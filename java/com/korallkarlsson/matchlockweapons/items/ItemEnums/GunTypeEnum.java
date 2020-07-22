package com.korallkarlsson.matchlockweapons.items.ItemEnums;

public enum GunTypeEnum {

	Matchlock("Matchlock"),
	Flintlock("Flintlock"),
	Wheellock("Wheellock"),
	Caplock("Caplock");
	
	String name;
	
	GunTypeEnum(String typeName)
	{
		name = typeName;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
