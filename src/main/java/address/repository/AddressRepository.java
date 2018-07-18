package address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import address.dto.StateAndPopulationDTO;
import address.model.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	@Query("SELECT a FROM AddressEntity a WHERE a.capital = true ORDER BY a.name")
    public List<AddressEntity> findAllCapitals();
	
	@Query("SELECT new address.dto.StateAndPopulationDTO(e.uf, COUNT(DISTINCT e.name)) FROM AddressEntity e GROUP BY e.uf" )
	public List<StateAndPopulationDTO> findPopulationByState();
	
	@Query("SELECT a FROM AddressEntity a WHERE a.ibgeId = :ibgeId")
    public AddressEntity findByIbgeId(@Param("ibgeId") Long ibgeId);
}