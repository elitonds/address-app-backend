package address.service;

import java.util.List;

import address.dto.AddressDTO;
import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;

public interface IAddressService {
	public List<AddressEntity> getAll();
	public List<AddressEntity> findAllCapitals();
	public List<MinMaxPopulationDTO> findMinMaxPopulationByState();
	public List<StateAndPopulationDTO> findPopulationByState();
	public AddressEntity findByIbgeId(Long ibgeId);
	public List<String> findCitiesByState(String uf);
	public void addAdress(AddressDTO dto);
	public void deleteAdress(Long id);
}
