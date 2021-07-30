package com.Brice.Languages.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Brice.Languages.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
	List<Language> findAll();
	List<Language> findByNameContaining(String search);
	Long countByNameContaining(String search);
	Long deleteByNameStartingWith(String search);
}
