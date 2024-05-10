package com.example.ykm.objects;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Ship implements Serializable{

	private static final long serialVersionUID = -7840614685901105735L;

	private int id;
	
	private String model;

	private String purpose;
	
	// Size (length, height, width), weight
	private Map<String, Double> specs;

	private String pilot;

	private String franchise;
	
	
}
