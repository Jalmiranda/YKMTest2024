package com.example.ykm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ykm.objects.Ship;

@Service
public interface ShipService {

	public List<Ship> getAllShips(int pages);
	
	public List<Ship> getShipsByFranchise(String franchise);
	
	public Ship getShipById(int id);
	
	public Ship getShipByName(String model);
	
	public List<Ship> getShipLike(String modelKeyword);
	
	public void insertShip (Ship ship);
	
	public void updateShip(Ship ship);
	
	public void deleteShip(String model);

}
