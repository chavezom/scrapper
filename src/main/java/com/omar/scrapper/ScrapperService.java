package com.omar.scrapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScrapperService {

	public Recipe scrape(String url) throws URISyntaxException{
		return getScrapper(url).scrape(url);
	}

	public Recipe scrape2(String url) throws URISyntaxException{
		return getScrapper(url).scrape2(url);
	}

	private Scrapper getScrapper(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String host = uri.getHost();
		if (host.equalsIgnoreCase("www.kingarthurbaking.com")){
		  	return new Kingarthurbaking();
		} else {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity");
		}
	}
}
