package com.omar.scrapper;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Kingarthurbaking implements Scrapper{
    public Recipe scrape(String url){
        Document doc = urlConnect(url);

        List<String> ingredientList = new ArrayList<String>();
        List<Instruction> instructionsList = new ArrayList<Instruction>();

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
            for (Element instruction_element : instructions) {
                System.out.println(instruction_element.text());
                System.out.println(instruction_element.select("img").attr("src"));
                Instruction instruction = new Instruction();
                instruction.setInstruction(instruction_element.text());
                instruction.setImage(instruction_element.select("img").attr("src"));
                instructionsList.add(instruction);
            }
        }

        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredientList);
        recipe.setInstructions(instructionsList);

        return recipe;
    }

    public Recipe scrape2(String url){
        Document doc = urlConnect(url);

        List<String> ingredientList = new ArrayList<String>();
        List<Instruction> instructionsList = new ArrayList<Instruction>();

        String json = doc.head().select("script[type*=application/ld+json]").html();

        // https://goessner.net/articles/JsonPath/
        ReadContext ctx = JsonPath.parse(json);
        List<String> ingredients = ctx.read("$['@graph'][0]['recipeIngredient']");

        System.out.println("Size: " + ingredients.size());

        Iterator iter = ingredients.iterator();

        while (iter.hasNext()){
            String ingredient = iter.next().toString();
            System.out.println(ingredient);
            ingredientList.add(ingredient);
        }

        String[] instructions = ctx.read("$['@graph'][0]['recipeInstructions']").toString().split("[.][,]");

        for (int i=0; i<instructions.length; i++){
            System.out.println(instructions[i]);
            Instruction instruction = new Instruction();
            instruction.setInstruction(instructions[i].trim());
            instructionsList.add(instruction);
        }

        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredientList);
        recipe.setInstructions(instructionsList);

        return recipe;
    }

    private Document urlConnect(String url){
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return doc;
    }
}
