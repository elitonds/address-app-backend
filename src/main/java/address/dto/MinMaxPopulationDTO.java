package address.dto;

public class MinMaxPopulationDTO {
	
	private StateAndPopulationDTO maxPopulation;
	private StateAndPopulationDTO minPopulation;
	public StateAndPopulationDTO getMaxPopulation() {
		return maxPopulation;
	}
	public void setMaxPopulation(StateAndPopulationDTO maxPopulation) {
		this.maxPopulation = maxPopulation;
	}
	public StateAndPopulationDTO getMinPopulation() {
		return minPopulation;
	}
	public void setMinPopulation(StateAndPopulationDTO minPopulation) {
		this.minPopulation = minPopulation;
	}

}
