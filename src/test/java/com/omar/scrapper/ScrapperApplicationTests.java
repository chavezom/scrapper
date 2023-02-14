package com.omar.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScrapperApplicationTests {

	@Autowired
	ScrapperService scrapperService;

	@Test
	void testScrape() throws IOException, URISyntaxException {
		Recipe recipe = scrapperService.scrape("https://www.kingarthurbaking.com/recipes/chocolate-chip-cookies-recipe");

		assertThat(recipe.getIngredients().size()).isGreaterThan(0);
		assertThat(recipe.getInstructions().size()).isGreaterThan(0);
	}

	@Test
	void testScrapeEmpty() throws IOException, URISyntaxException {
		Recipe recipe = scrapperService.scrape("https://www.google.com");

		assertEquals(recipe.getIngredients().size(), 0);
		assertEquals(recipe.getInstructions().size(), 0);
	}
}
