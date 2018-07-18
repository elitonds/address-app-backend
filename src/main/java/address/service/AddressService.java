package address.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import address.dto.AddressDTO;
import address.dto.MinMaxPopulationDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.repository.AddressRepository;
import address.utils.AddressEnum;
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
		if(entity!=null && !entity.equals(Optional.empty())) {
			addressRepository.delete(entity.get());
		}
	}
	
	@Override
	public Long countRegiters() {
		return addressRepository.count();
	}
	
	public List<LinkedHashMap<String, String>> getValuesCsvByColumn(AddressEnum column, String value){
		try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource("cidades.csv").getFile();
			MappingIterator<LinkedHashMap<String, String>> readValues = mapper.reader(LinkedHashMap.class).with(bootstrapSchema).readValues(file);
	        List<LinkedHashMap<String, String>> values = readValues.readAll();
	        values = values.stream().filter(elements -> elements.get(column.getKey()).contains(value)).collect(Collectors.toList());
	        return values;
	    } catch (Exception e) {
	    	
	    }
		return null;
	}

}
