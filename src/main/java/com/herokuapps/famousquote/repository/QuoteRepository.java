package com.herokuapps.famousquote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herokuapps.famousquote.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
