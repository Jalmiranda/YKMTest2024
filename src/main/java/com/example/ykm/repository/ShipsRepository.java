package com.example.ykm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ykm.objects.ShipDTO;

@Repository
public interface ShipsRepository extends JpaRepository<ShipDTO, Integer> {

	// Consulta paginada
	@Query(value = "SELECT s from ShipDTO s")	
    public List<ShipDTO> getAllShips(Pageable pageable);	
	
	// Consulta por id (modelo)
	@Query(value = "SELECT s from ShipDTO s"
    		+ " where s.id = ?1")	
    public ShipDTO getShipById(int id);
	
	// Consulta por id (modelo)
	@Query(value = "SELECT s from ShipDTO s"
    		+ " where s.model = ?1")	
    public ShipDTO getShipByName(String model);
	
	@Query(value = "SELECT s from ShipDTO s"
    		+ " where s.franchise = ?1")	
    public List<ShipDTO> getShipsByFranchise(String franchise);	
	
	// Consulta like (por texto)
	@Query(value = "SELECT s from ShipDTO s"
    		+ " where s.model ilike %:modelKeyword%")	
    public List<ShipDTO> getShipLike(@Param("modelKeyword") String modelKeyword);	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE ShipDTO s SET"
			+ " s.purpose = (CASE :purpose WHEN NULL THEN s.purpose ELSE :purpose END),"
			+ " s.sizeLength = (CASE :sizeLength WHEN NULL THEN s.sizeLength ELSE :sizeLength END),"
			+ " s.sizeHeight = (CASE :sizeHeight WHEN NULL THEN s.sizeHeight ELSE :sizeHeight END),"
			+ " s.sizeWidth = (CASE :sizeWidth WHEN NULL THEN s.sizeWidth ELSE :sizeWidth END),"
			+ " s.weight = (CASE :weight WHEN NULL THEN s.weight ELSE :weight END),"
			+ " s.pilot = (CASE :pilot WHEN NULL THEN s.pilot ELSE :pilot END),"
			+ " s.franchise = (CASE :franchise WHEN NULL THEN s.franchise ELSE :franchise END)"
    		+ " where s.model =:model")	
    public void update(@Param("model") String model, @Param("purpose") String purpose, 
    		@Param("sizeLength") Double sizeLength, @Param("sizeHeight") Double sizeHeight,
    		@Param("sizeWidth") Double sizeWidth, @Param("weight") Double weight,
    		@Param("pilot") String pilot, @Param("franchise") String franchise);	
//	@Query(value = "UPDATE ShipDTO s SET s.purpose =: #{#ship.purpose}, "
//			+ " s.sizeLength =: #{#ship.sizeLength}, s.sizeHeight =: #{#ship.sizeHeight},"
//			+ " s.sizeWidth =: #{#ship.sizeWidth},  s.weight =: #{#ship.weight}, "
//			+ " s.pilot =: #{#ship.pilot},  s.franchise =: #{#ship.franchise}"
//    		+ " where s.model =: #{#ship.model}")	
//    public void update(ShipDTO ship);	


}