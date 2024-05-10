package com.example.ykm.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ykm.ShipApplication;
import com.example.ykm.objects.Ship;
import com.example.ykm.objects.ShipDTO;
import com.example.ykm.repository.ShipsRepository;
import com.example.ykm.service.ShipServiceImpl;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=ShipApplication.class)
class ShipTest {

    @Mock
    ShipsRepository shipsRepository;
    
    @InjectMocks
    ShipServiceImpl shipService;
    
    @Captor
    ArgumentCaptor<ShipDTO> shipCaptor;
    
    @Captor
    ArgumentCaptor<Double> lengthCaptor;
    
    @Captor
    ArgumentCaptor<Double> heightCaptor;
    
    @Captor
    ArgumentCaptor<Double> widthCaptor;
    
    @Captor
    ArgumentCaptor<Double> weightCaptor;
    
    @Captor
    ArgumentCaptor<String> modelCaptor;
    
    @Captor
    ArgumentCaptor<String> franchiseCaptor;
    
    @Captor
    ArgumentCaptor<String> purposeCaptor;
    
    @Captor
    ArgumentCaptor<String> pilotCaptor;
    
    
	@Test
    public void getTest(){
		
		ShipDTO shipDTO = new ShipDTO();
		shipDTO.setModel("Default");
		
		when(shipsRepository.getShipByName(shipDTO.getModel()))
        .thenReturn(shipDTO);
		
		Ship recoveredShip = shipService.getShipByName("Default");
		verify(shipsRepository).getShipByName(modelCaptor.capture());
		Assertions.assertNotNull(recoveredShip);
		Assertions.assertEquals(shipDTO.getModel(), modelCaptor.getValue());
		
	}
	
	@Test
    public void insertTest(){

		Ship ship = new Ship();
		ship.setModel("Trial");	
		ship.setFranchise("Test ships");
		shipService.insertShip(ship);
		verify(shipsRepository).save(shipCaptor.capture());
		ShipDTO insertedShip = shipCaptor.getValue();
		Assertions.assertNotNull(insertedShip);				
		Assertions.assertEquals(ship.getFranchise(), insertedShip.getFranchise());
		
	}
	
	@Test
    public void updateTest(){

		Ship ship = new Ship();
		ship.setModel("Trial");	
		ship.setFranchise("Test ships");
		ship.setPurpose("For testing");
		ship.setPilot("Testimillian McTestington");
		Map<String, Double> specs = new HashMap<String, Double>();
		specs.put("Length", 20.3);
		specs.put("Height", 20.3);
		specs.put("Width", 20.3);
		specs.put("Weight", 20.3);
		ship.setSpecs(specs);
		shipService.updateShip(ship);
		verify(shipsRepository).update(modelCaptor.capture(), purposeCaptor.capture(),
				lengthCaptor.capture(), heightCaptor.capture(), widthCaptor.capture(), 
				weightCaptor.capture(), pilotCaptor.capture(), franchiseCaptor.capture());
				
//				ship.getModel(), 
//				ship.getPurpose(), null, null, null, 
//				null, ship.getPilot(), 
//				ship.getFranchise());
		Assertions.assertEquals(franchiseCaptor.getValue(), ship.getFranchise());
		Assertions.assertEquals(lengthCaptor.getValue(), ship.getSpecs().get("Length"));
		
	}
    
	@Test
    public void deleteTest(){
		
		insertTest();
		shipService.deleteShip("Trial");
	    verify(shipsRepository).delete(shipCaptor.capture());
	    
    }
	
}