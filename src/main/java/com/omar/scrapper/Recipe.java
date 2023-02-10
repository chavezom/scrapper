package com.omar.scrapper;

import java.util.List;

public class Recipe {
	private List<Instruction> instructions;
	private List<String> ingredients;
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	public List<String> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
}
