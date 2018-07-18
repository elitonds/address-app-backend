package address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.repository.AddressRepository;

@Service
public class AddressService implements IAddressService{

	@Autowired
	AddressRepository addressRepository;

	public List<AddressEntity> getAll() {
		return addressRepository.findAll();
	}

	@Override
	public List<AddressEntity> findAllCapitals() {
		return addressRepository.findAllCapitals();
	}

	@Override
	public List<MinMaxPopulationDTO> findMinMaxPopulationByState() {		
		return null;
	}

	@Override
	public List<StateAndPopulationDTO> findPopulationByState() {
		return addressRepository.findPopulationByState();
	}
	
	public AddressEntity findByIbgeId(Long ibgeId) {
		return addressRepository.findByIbgeId(ibgeId);
	}

}
