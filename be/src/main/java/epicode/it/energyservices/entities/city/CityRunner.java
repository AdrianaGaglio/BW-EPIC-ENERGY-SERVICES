package epicode.it.energyservices.entities.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CityRunner implements ApplicationRunner {
    @Autowired
    private CityImportSvc cityImportSvc;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String filePath = getClass().getClassLoader().getResource("comuni-italiani.csv").getPath();
        cityImportSvc.importCsv(filePath);
    }
}

