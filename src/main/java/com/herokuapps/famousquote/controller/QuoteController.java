package com.herokuapps.famousquote.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.herokuapps.famousquote.model.Quote;
import com.herokuapps.famousquote.repository.QuoteRepository;

@RestController
@RequestMapping(value = "/quotes")
public class QuoteController {

	private static String QUOTE_CREATED = "Quote with id = {0} has been created.";
	private static String QUOTE_UPDATED = "Quote with id = {0} has been updated.";
	private static String QUOTE_DELETED = "Quote with id = {0} has been deleted.";
	private static String QUOTE_DOES_NOT_EXIST = "Quote with id = {0} does not exist.";

	@Autowired
	QuoteRepository quoteRepository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Quote> getAllQuotes() {
		return quoteRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<String> createQuote(@RequestBody Quote quote) {
		Quote createdQuote = quoteRepository
				.save(new Quote(quote.getFirstName(), quote.getLastName(), quote.getQuote()));
		return new ResponseEntity<>(MessageFormat.format(QUOTE_CREATED, createdQuote.getId()), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateQuote(@PathVariable("id") long id, @RequestBody Quote quote) {
		Optional<Quote> existingQuote = quoteRepository.findById(id);
		if (existingQuote.isPresent()) {
			Quote quoteToSave = existingQuote.get();
			quoteToSave.setFirstName(quote.getFirstName());
			quoteToSave.setLastName(quote.getLastName());
			quoteToSave.setQuote(quote.getQuote());
			quoteRepository.save(quoteToSave);
			return new ResponseEntity<>(MessageFormat.format(QUOTE_UPDATED, quoteToSave.getId()), HttpStatus.OK);
		}

		return new ResponseEntity<>(MessageFormat.format(QUOTE_DOES_NOT_EXIST, id), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteQuote(@PathVariable("id") long id) {
		try {
			quoteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(MessageFormat.format(QUOTE_DOES_NOT_EXIST, id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(MessageFormat.format(QUOTE_DELETED, id), HttpStatus.OK);
	}

}