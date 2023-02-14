package com.omar.scrapper;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
	private List<Instruction> instructions;
	private List<String> ingredients;
}
