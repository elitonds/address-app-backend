package address.utils;

import org.springframework.data.jpa.repository.Query;

import address.model.AddressEntity;

public enum AddressEnum {
	IBGE_ID ("ibge_id"),
	UF("uf"),
	NAME("name"),
	CAPITAL("capital"),
	LON ("lon"),
	LAT("lat"),
	NO_ACCENTS("no_accents"),
	ALTERNATIVE_NAMES("alternative_names"),
	MICRO_REGION("microregion"),
	MESO_REGION("mesoregion");

	
	private String key;

	AddressEnum(String key){
		this.key = key;
	}
	public String getKey() {
		return key;
	}
}
