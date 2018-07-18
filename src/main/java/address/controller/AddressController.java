package address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;
import address.service.IAddressService;

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
}
