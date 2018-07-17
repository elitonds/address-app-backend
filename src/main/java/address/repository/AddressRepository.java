package address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	@Query("SELECT a FROM AddressEntity a WHERE a.capital = true ORDER BY a.name")
    public List<AddressEntity> findAllCapitals();
	
	@Query("SELECT e.uf as name, COUNT(DISTINCT e.name) as populationAmount FROM AddressEntity e GROUP BY e.uf" )
	public List<StateAndPopulationDTO> findPopulationByState();
}