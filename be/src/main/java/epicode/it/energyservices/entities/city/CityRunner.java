package epicode.it.energyservices.entities.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

public class CityRunner implements ApplicationRunner {
    @Autowired
    private CityImportSvc cityImportSvc;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cityImportSvc.importCsv("src/main/resources/comuni-italiani.csv");
    }
}

