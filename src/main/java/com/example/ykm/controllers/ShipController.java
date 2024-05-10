package com.example.ykm.controllers;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ykm.objects.Ship;
import com.example.ykm.service.ShipService;

@RestController
@RequestMapping("ships")
public class ShipController {
	
		/*
		 * Ejemplos:
		 * 		http://localhost:8080/ships/find/all?pages=3
		 * 		http://localhost:8080/ships/find/franchise/BIONICLE
		 * 		http://localhost:8080/ships/find/model/X-Wing
		 * 		http://localhost:8080/ships/find/Axalara
		 * 		http://localhost:8080/ships/delete/Halberd
		 */

		@Autowired
		private ShipService shipService;
		
		// Consulta paginada
		@GetMapping(value="/find/all")
		public List<Ship> getAllShips(@RequestParam(value = "pages", required=false) Integer pages) {
			// si no se provee un numero concreto de paginas, se devuelve solo una
			pages = (Objects.isNull(pages)) ? 1 : pages;
			return shipService.getAllShips(pages);
		}
		
		@GetMapping(value="/find/franchise/{franchise}")
		public List<Ship> getShipsByFranchise(@PathVariable(name = "franchise") String franchise){
			return shipService.getShipsByFranchise(franchise);
		}
		
		// Consulta por id (modelo)		
		@GetMapping(value="/find/id/{id}")
		public Ship getShipById(@PathVariable(name = "id") int id) {
			return shipService.getShipById(id);
		}
		
		// Consulta por modelo	
		@GetMapping(value="/find/model/{model}")
		public Ship getShipByName(@PathVariable(name = "model") String model) {
			return shipService.getShipByName(model);
		}
		
		// Consulta like (por texto)
		@GetMapping(value="/find/{modelKeyword}")
		public List<Ship> getShipLike(@PathVariable(name = "modelKeyword") String modelKeyword){
			return shipService.getShipLike(modelKeyword);
		}
		
		@PostMapping(value="/post", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void insertShip (@RequestBody Ship ship){
			shipService.insertShip(ship);
		}
		
		@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateShip (@RequestBody Ship ship){
			shipService.updateShip(ship);
		}
		
		@DeleteMapping(value="/delete/{model}")
		public void deleteShip(@PathVariable(name = "model") String model){
			shipService.deleteShip(model);
		}
		
}
