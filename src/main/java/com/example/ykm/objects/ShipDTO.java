package com.example.ykm.objects;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Ships")
@Data
public class ShipDTO implements Serializable{
	
	private static final long serialVersionUID = 6635767425895032920L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="MODEL")
	private String model;

	@Column(name="PURPOSE")
	private String purpose;
	
	@Column(name="SIZE_LENGTH")
	private Double sizeLength;
	
	@Column(name="SIZE_HEIGHT")
	private Double sizeHeight;

	@Column(name="SIZE_WIDTH")
	private Double sizeWidth;
	
	@Column(name="WEIGHT")
	private Double weight;
	
	@Column(name="PILOT")
	private String pilot;
	
	@Column(name="FRANCHISE")
	private String franchise;
	
}
