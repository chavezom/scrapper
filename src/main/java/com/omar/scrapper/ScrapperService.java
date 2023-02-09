package com.omar.scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScrapperService {
	
	public Recipe scrape(String url){
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}

		List<String> ingredientList = new ArrayList<String>();
		List<String> instructionsList = new ArrayList<String>();

		System.out.println(doc.title());

		Elements elements = doc.getElementsByClass("ingredients-list");
		if (elements.size() > 0){
			Elements ingredients = elements.first().select("li");
			System.out.println("Ingredients:");
			for (Element ingredient : ingredients) {
				System.out.println(ingredient.text());
				ingredientList.add(ingredient.text());
			}
		}

		elements = doc.getElementsByClass("recipe__instructions");
		if (elements.size() > 0) {
			Elements instructions = elements.first().select("li");
			System.out.println("Instructions:");
			for (Element instruction : instructions) {
				System.out.println(instruction.text());
				instructionsList.add(instruction.text());
			}
		}
		
		Recipe recipe = new Recipe();
		recipe.setIngredients(ingredientList);
		recipe.setInstructions(instructionsList);
		
		return recipe;
	}
}
