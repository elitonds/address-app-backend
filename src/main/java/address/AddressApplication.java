package address;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import address.model.AddressEntity;
import address.repository.AddressRepository;
import address.utils.AddressEnum;

@SpringBootApplication
@EnableJpaAuditing
public class AddressApplication {
	public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
    }
	
	@Bean
	CommandLineRunner init(AddressRepository repository) {
		
		List<AddressEntity> result = repository.findAll();
		if(result.size()<=0) {
			try {
		        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
		        CsvMapper mapper = new CsvMapper();
		        File file = new ClassPathResource("cidades.csv").getFile();
				MappingIterator<LinkedHashMap<String, String>> readValues = mapper.reader(LinkedHashMap.class).with(bootstrapSchema).readValues(file);
		        List<LinkedHashMap<String, String>> values = readValues.readAll();
		        for(LinkedHashMap<String, String> value : values){
		        	AddressEntity entity = new AddressEntity();
		        	entity.setIbgeId(Long.valueOf(value.get(AddressEnum.IBGE_ID.getKey())));
		        	entity.setUf(value.get(AddressEnum.UF.getKey()));
		        	entity.setName(value.get(AddressEnum.NAME.getKey()));
		        	entity.setCapital(value.get(AddressEnum.CAPITAL.getKey()).equals("true"));
		        	entity.setLon(Float.valueOf(value.get(AddressEnum.LON.getKey())));
		        	entity.setLat(Float.valueOf(value.get(AddressEnum.LAT.getKey())));
		        	entity.setNoAccents(value.get(AddressEnum.NO_ACCENTS.getKey()));
		        	entity.setAlternativeNames(value.get(AddressEnum.ALTERNATIVE_NAMES.getKey()));
		        	entity.setMicroregion(value.get(AddressEnum.MICRO_REGION.getKey()));
		        	entity.setMesoregion(value.get(AddressEnum.MESO_REGION.getKey()));
		        	repository.save(entity);
		        }
		    } catch (Exception e) {
		    	
		    }
		}
		return null;
	}
}
