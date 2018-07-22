package address.dto;

import java.math.BigInteger;


public class MinMaxPopulationDTO {
	private String type;
	private String name;
	private Long populationAmount;

	public MinMaxPopulationDTO(String type, String name, Long populationAmount) {
		this.type = type;
		this.name = name;
		this.populationAmount = populationAmount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPopulationAmount() {
		return populationAmount;
	}

	public void setPopulationAmount(Long populationAmount) {
		this.populationAmount = populationAmount;
	}
}
