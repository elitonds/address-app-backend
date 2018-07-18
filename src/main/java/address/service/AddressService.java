package address.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import address.dto.AddressDTO;
import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.repository.AddressRepository;
import address.utils.Utils;

@Service
public class AddressService implements IAddressService {

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

	@Override
	public AddressEntity findByIbgeId(Long ibgeId) {
		return addressRepository.findByIbgeId(ibgeId);
	}

	@Override
	public List<String> findCitiesByState(String uf) {
		return addressRepository.findCitiesByState(uf);
	}

	@Override
	public void addAdress(AddressDTO dto) {
		AddressEntity entity = new AddressEntity();
		entity.setIbgeId(dto.getIbgeId());
		entity.setCapital(dto.isCapital());
		entity.setAlternativeNames(dto.getAlternativeNames());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		entity.setMesoregion(dto.getMesoregion());
		entity.setMicroregion(dto.getMicroregion());
		entity.setName(dto.getName());
		entity.setUf(dto.getUf());
		entity.setNoAccents(Utils.removeAccents(dto.getName()));

		addressRepository.save(entity);
	}

	@Override
	public void deleteAdress(Long id) {
		Optional<AddressEntity> entity = addressRepository.findById(id);
		if(entity!=null && entity.get()!=null) {
			addressRepository.delete(entity.get());
		}
	}

}
