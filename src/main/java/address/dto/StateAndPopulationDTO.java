package address.dto;

public class StateAndPopulationDTO {
	private String name;
	private Long populationAmount;
	
	public StateAndPopulationDTO(String name, Long populationAmount) {
		this.name = name;
		this.populationAmount = populationAmount;
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
