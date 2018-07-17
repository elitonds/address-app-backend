package address.service;

import java.util.List;

import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;

public interface IAddressService {
	public List<AddressEntity> getAll();
	public List<AddressEntity> findAllCapitals();
	public List<MinMaxPopulationDTO> findMinMaxPopulationByState();
	public List<StateAndPopulationDTO> findPopulationByState();
}
