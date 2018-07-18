package address.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import address.dto.AddressDTO;
import address.dto.CsvRequestDTO;
import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.service.IAddressService;
import address.utils.AddressEnum;

@RestController
@RequestMapping("/api")
public class AddressController {

	    @Autowired
	    IAddressService service;
	    
	    @GetMapping("/all")
	    public List<AddressEntity> getAll() {
	        return service.getAll();
	    }
	    
	    @GetMapping("/all_capitals")
	    public List<AddressEntity> getAllCapitals() {
	        return service.findAllCapitals();
	    }
	    
	    @GetMapping("/population_by_state")
	    public List<StateAndPopulationDTO> findPopulationByState() {
	        return service.findPopulationByState();
	    }
	    
	    @GetMapping("/address_by_ibge_id/{ibge_id}")
	    public AddressEntity findByIbgeId(@PathVariable(value = "ibge_id") Long ibgeId) {
	        return service.findByIbgeId(ibgeId);
	    }
	    
	    @GetMapping("/cities_by_state/{uf}")
	    public List<String> findCitiesByState(@PathVariable(value = "uf") String uf) {
	        return service.findCitiesByState(uf);
	    }
	    
	    @PostMapping("/add")
	    public void add(@RequestBody AddressDTO dto) {
	        service.addAdress(dto);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public void delete(@PathVariable("id") Long id) {
	    	service.deleteAdress(id);
	    }
	    
	    @GetMapping("/count")
	    public Long countRegisters() {
	    	return service.countRegiters();
	    }
	    
	    @PostMapping("/find_in_csv")
	    public List<LinkedHashMap<String, String>> getRegister(@RequestBody CsvRequestDTO request) {
	    	return service.getValuesCsvByColumn(request.getColumn(), request.getValue());
	    }
}
