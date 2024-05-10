package com.example.ykm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.ykm.objects.Ship;
import com.example.ykm.objects.ShipDTO;
import com.example.ykm.repository.ShipsRepository;
import com.example.ykm.utils.ShipConstants;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class ShipServiceImpl implements ShipService {

	@Autowired
	public ShipsRepository shipsRepository;
	
	@Override
	public List<Ship> getAllShips(int pages){
		
		List<Ship> ships = new ArrayList<Ship>();
		Pageable pageable = PageRequest.of(0, pages, Sort.by(Order.asc("model")));
		
		List<ShipDTO> shipsDTO = shipsRepository.getAllShips(pageable);
		
		for(ShipDTO shipDTO : shipsDTO) {
			if(Objects.nonNull(shipDTO)) {
				Ship ship = shipDTOtoShip(shipDTO);
				ships.add(ship);
			} else {
				log.error("No se ha encontrado naves");
			}
		}
		
		return ships;
	}
	
	@Override
	public List<Ship> getShipsByFranchise(String franchise){

		List<Ship> ships = new ArrayList<Ship>();
		List<ShipDTO> shipsDTO = shipsRepository.getShipsByFranchise(franchise);
		
		for(ShipDTO shipDTO : shipsDTO) {
			if(Objects.nonNull(shipDTO)) {
				Ship ship = shipDTOtoShip(shipDTO);
				ships.add(ship);
			} else {
				log.error("No se han encontrado naves de esa franquicia");
			}
		}
		
		return ships;
	}
	
	@Override
	public Ship getShipById(int id) {
		
		ShipDTO shipDTO = shipsRepository.getShipById(id);
		Ship ship = null;
		if(Objects.nonNull(shipDTO)) {
			ship = shipDTOtoShip(shipDTO);
		} else {
			log.error("No se ha encontrado nave con ese ID");
		}
		
		return ship;
	}
	
	@Override
	public Ship getShipByName(String model) {
		
		ShipDTO shipDTO = shipsRepository.getShipByName(model);
		Ship ship = null;
		if(Objects.nonNull(shipDTO)) {
			ship = shipDTOtoShip(shipDTO);
		} else {
			log.error("No se ha encontrado nave por ese nombre");
		}
		
		return ship;
	}
	
	@Override
	public List<Ship> getShipLike(String modelKeyword) {
		
		List<Ship> ships = new ArrayList<Ship>();
		List<ShipDTO> shipsDTO = shipsRepository.getShipLike(modelKeyword);
		for(ShipDTO shipDTO : shipsDTO) {
			if(Objects.nonNull(shipDTO)) {
				Ship ship = shipDTOtoShip(shipDTO);
				ships.add(ship);
			} else {
				log.error("No se ha encontrado nave");
			}
		}
		return ships;
	}
	
	@Override
	public void insertShip(Ship ship) {
		
		ShipDTO shipDTO = shipToShipDTO(ship);
		
		shipsRepository.save(shipDTO);
	}
	
	@Override
	public void updateShip(Ship ship) {
		ShipDTO shipDTO = shipToShipDTO(ship);
		
		//shipsRepository.update(shipDTO);
		shipsRepository.update(shipDTO.getModel(), shipDTO.getPurpose(), 
				shipDTO.getSizeLength(), shipDTO.getSizeHeight(), shipDTO.getSizeWidth(), 
				shipDTO.getWeight(), shipDTO.getPilot(),shipDTO.getFranchise());
	}
	
	@Override
	public void deleteShip(String model) {
		ShipDTO shipDTO = shipsRepository.getShipByName(model);
		shipsRepository.delete(shipDTO);
	}
	
	private Ship shipDTOtoShip(ShipDTO shipDTO) {
		Ship ship = new Ship();
		ship.setId(shipDTO.getId());
		ship.setModel(shipDTO.getModel());
		if(Objects.nonNull(shipDTO.getPurpose())){
			ship.setPurpose(shipDTO.getPurpose());
		}
		if(Objects.nonNull(shipDTO.getPilot())){
			ship.setPilot(shipDTO.getPilot());
		}
		if(Objects.nonNull(shipDTO.getFranchise())){
			ship.setFranchise(shipDTO.getFranchise());
		}
		
		Map<String, Double> specs = new HashMap();
		if(Objects.nonNull(shipDTO.getSizeHeight())) {
			specs.put(ShipConstants.SPEC_SIZE_HEIGHT, shipDTO.getSizeLength());
		}
		if(Objects.nonNull(shipDTO.getSizeLength())) {
			specs.put(ShipConstants.SPEC_SIZE_LENGTH, shipDTO.getSizeLength());
		}
		if(Objects.nonNull(shipDTO.getSizeWidth())) {
			specs.put(ShipConstants.SPEC_SIZE_WIDTH, shipDTO.getSizeWidth());
		}
		if(Objects.nonNull(shipDTO.getWeight())) {
			specs.put(ShipConstants.SPEC_WEIGHT, shipDTO.getWeight());
		}
		if(!specs.isEmpty()) {
			ship.setSpecs(specs);
		}
		return ship;
	}
	
	private ShipDTO shipToShipDTO(Ship ship) {
		ShipDTO shipDTO = new ShipDTO();
		shipDTO.setModel(ship.getModel());
		if(Objects.nonNull(ship.getPurpose())){
			shipDTO.setPurpose(ship.getPurpose());
		}
		if(Objects.nonNull(ship.getPilot())){
			shipDTO.setPilot(ship.getPilot());
		}
		if(Objects.nonNull(ship.getFranchise())){
			shipDTO.setFranchise(ship.getFranchise());
		}
		
		if(Objects.nonNull(ship.getSpecs())) {
			if(ship.getSpecs().containsKey(ShipConstants.SPEC_SIZE_HEIGHT)) {
				shipDTO.setSizeHeight(ship.getSpecs().get(ShipConstants.SPEC_SIZE_HEIGHT));
			}
			if(ship.getSpecs().containsKey(ShipConstants.SPEC_SIZE_LENGTH)) {
				shipDTO.setSizeLength(ship.getSpecs().get(ShipConstants.SPEC_SIZE_LENGTH));
			}
			if(ship.getSpecs().containsKey(ShipConstants.SPEC_SIZE_WIDTH)) {
				shipDTO.setSizeWidth(ship.getSpecs().get(ShipConstants.SPEC_SIZE_WIDTH));
			}
			if(ship.getSpecs().containsKey(ShipConstants.SPEC_WEIGHT)) {
				shipDTO.setWeight(ship.getSpecs().get(ShipConstants.SPEC_WEIGHT));
			}
		}
		return shipDTO;
	}

}
