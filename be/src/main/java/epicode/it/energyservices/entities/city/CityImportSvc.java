package epicode.it.energyservices.entities.city;

import epicode.it.energyservices.entities.district.District;
import epicode.it.energyservices.entities.district.DistrictRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@AllArgsConstructor
@Service
public class CityImportSvc {
    private final DistrictRepo districtRepo;
    private final CityRepo cityRepo;


    public void importCsv(String filePath) {
        // Dichiarazione e inizializzazione di risorse
        // Questa sintassi garantisce che le risorse vengano chiuse automaticamente anche in caso di eccezioni.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //buffer reader viene utilizzato per leggere il file riga per riga
            String line;
            while ((line = br.readLine()) != null) {
                // Ignora l'intestazione
                if (line.startsWith("Codice Provincia")) {
                    continue;
                }
                String[] fields = line.split(";");
                String cityName = fields[2];
                String districtName = fields[3];

                // Recupera o crea il District
                District district = districtRepo.findByName(districtName)
                        .orElseGet(() -> {
                            District newDistrict = new District();
                            newDistrict.setName(districtName);
                            return districtRepo.save(newDistrict);
                        });

                // Crea e salva la City
                City city = new City();
                city.setName(cityName);
                city.setDistrict(district);
                cityRepo.save(city);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
