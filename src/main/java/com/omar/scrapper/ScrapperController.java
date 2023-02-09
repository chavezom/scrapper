package com.omar.scrapper;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Recipe scrape(@RequestBody Url url) throws ResponseStatusException {
		
		// https://www.kingarthurbaking.com/recipes/chocolate-chip-cookies-recipe
		return scrapperService.scrape(url.getUrl());
		
	}
}
