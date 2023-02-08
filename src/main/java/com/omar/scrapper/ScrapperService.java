package com.omar.scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ScrapperService {
	
	public Recipe scrape(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.title());
		
		Elements ingredients = doc.getElementsByClass("ingredients-list").first().select("li");
		List<String> ingredientList = new ArrayList<String>();
		System.out.println("Ingredients:");
		for (Element ingredient : ingredients) {
			System.out.println(ingredient.text());
			ingredientList.add(ingredient.text());
		}
		
		Elements instructions = doc.getElementsByClass("recipe__instructions").first().select("li");
		List<String> instructionsList = new ArrayList<String>();
		System.out.println("Instructions:");
		for (Element instruction : instructions) {
			System.out.println(instruction.text());
			instructionsList.add(instruction.text());
		}
		
		Recipe recipe = new Recipe();
		recipe.setIngredients(ingredientList);
		recipe.setInstructions(instructionsList);
		
		return recipe;
	}

}
