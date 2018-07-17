package address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	    // Get All Notes
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
}
