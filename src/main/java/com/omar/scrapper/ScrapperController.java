package com.omar.scrapper;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class ScrapperController {
	
	private ScrapperService scrapperService;
	
	@Autowired
	public ScrapperController(ScrapperService scrapperService) {
	    this.scrapperService = scrapperService;	
	}

	@PostMapping("/scrape")
	public Recipe scrape(@RequestBody Url url) throws ResponseStatusException, URISyntaxException {
		// https://www.kingarthurbaking.com/recipes/chocolate-chip-cookies-recipe
		return scrapperService.scrape(url.getUrl());
	}

	@PostMapping(value = "/scrape2")
	public Recipe scrape2(@RequestBody Url url) throws ResponseStatusException, URISyntaxException {
		return scrapperService.scrape2(url.getUrl());
	}
}
