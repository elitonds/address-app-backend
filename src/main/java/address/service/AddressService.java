package address.service;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	@PersistenceContext
	private EntityManager manager;

	public List<AddressEntity> getAll() {
		return addressRepository.findAll();
	}

	@Override
	public List<AddressEntity> findAllCapitals() {
		return addressRepository.findAllCapitals();
	}

	@Override
	public List<MinMaxPopulationDTO> findMinMaxPopulationByState() {
		String sql = "select * from(" + 
				"SELECT 'max' as type, uf as name,count(distinct(name)) as populationAmount " + 
				"FROM address " + 
				"group by uf  order by 3 desc limit 1) a " + 
				"union " + 
				"select * from( " + 
				"SELECT 'min' as type, uf as name,count(distinct(name)) as populationAmount " + 
				"FROM address " + 
				"group by uf  order by 3 asc limit 1) a";
		List<Object[]> result = manager.createNativeQuery(sql).getResultList();
		List<MinMaxPopulationDTO> list = new ArrayList<MinMaxPopulationDTO>();
		for(Object[] value : result) {
			MinMaxPopulationDTO dto = new MinMaxPopulationDTO(
					value[0].toString(), value[1].toString(), Long.valueOf(value[2].toString()));
			list.add(dto);
		}
		return list;
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

	@Override
	public Long countRegitersByColumn(String column) {
		String sql = "SELECT COUNT(DISTINCT ("+column+")) FROM address";
		Query query  = manager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		return Long.valueOf(result.toString());
	}

}
