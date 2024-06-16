package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Service.AsientoService;
import com.example.demo.Service.EventoService;
import com.example.demo.model.Asiento;
import com.example.demo.model.Evento;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EventoService eventService;

    @Autowired
    private AsientoService asientoService;

    @Override
    public void run(String... args) throws Exception {
        if (eventService.findAllEvents().isEmpty()) {
            Evento event = new Evento();
            event.setName("Dallas Mavericks-Minnesota Timberwolves");
            event.setDescription("Game 5 Conference Finals West");
            event.setDate("30/05");
            event.setLocation("Target Center, Minneapolis (Minnesota)");
            eventService.saveEvent(event);
            createAsientosForEvent(event);
            
            Evento event1 = new Evento();
            event1.setName("Boston Celtics-Indiana Pacers");
            event1.setDescription("Game 4 Conference Finals East");
            event1.setDate("27/05");
            event1.setLocation("Gainbridge Fieldhouse, Indianapolis (Indiana)");
            eventService.saveEvent(event1);
            createAsientosForEvent(event1);
            
            Evento event2 = new Evento();
            event2.setName("Dallas Mavericks-Boston Celtics");
            event2.setDescription("Game 5 NBA Finals ");
            event2.setDate("18/06");
            event2.setLocation("TD Garden, Boston (Massachusetts)");
            eventService.saveEvent(event2);
            createAsientosForEvent(event2);

        }
    }

    private void createAsientosForEvent(Evento event) {
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int numeroAsiento = random.nextInt(1000) + 1;
            int sector = random.nextInt(12) + 1;
            double precio = calcularPrecioPorSector(sector);
            Asiento asiento = new Asiento(null, numeroAsiento, sector, precio, event);
            asientoService.saveAsiento(asiento);
        }
    }


    private double calcularPrecioPorSector(int sector) {
        // Ejemplo simple: Precio basado en el sector
        switch (sector) {
            case 1:
            	return 140.0;
            case 2:
                return 160.0;
            case 3:
            	return 160.0;
            case 4:
                return 140.0;
            case 5:
            	return 220.0;
            case 6:
                return 220.0;
            case 7:
                return 240.0;
            case 8:
                return 240.0;
            case 9:
                return 300.0;
            case 10:
                return 280.0;
            case 11:
                return 280.0;
            case 12:
                return 300.0;
            default:
                return 40.0;
        }
    }
   

   
}
