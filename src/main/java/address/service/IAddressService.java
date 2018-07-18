package address.service;

import java.util.LinkedHashMap;
import java.util.List;

import address.dto.AddressDTO;
import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.utils.AddressEnum;

public interface IAddressService {
	public List<AddressEntity> getAll();
	public List<AddressEntity> findAllCapitals();
	public List<MinMaxPopulationDTO> findMinMaxPopulationByState();
	public List<StateAndPopulationDTO> findPopulationByState();
	public AddressEntity findByIbgeId(Long ibgeId);
	public List<String> findCitiesByState(String uf);
	public void addAdress(AddressDTO dto);
	public void deleteAdress(Long id);
	public Long countRegiters();
	public List<LinkedHashMap<String, String>> getValuesCsvByColumn(AddressEnum column, String value);
}
