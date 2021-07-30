package com.Brice.Languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Brice.Languages.models.Language;
import com.Brice.Languages.repository.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	public List<Language> allLanguages() {
		return languageRepository.findAll();
	}
	public Language createLanguage(Language b) {
		return languageRepository.save(b);
	}
	public Language findLanguage(Long id) {
		Optional<Language> optionalLanguage = languageRepository.findById(id);
		if(optionalLanguage.isPresent()) {
			return optionalLanguage.get();
		}
		else {
			return null;
		}
	}
	public Language updateLanguage(Language b) {
		return languageRepository.save(b);
	}
	public void deleteLanguage(Long id) {
		languageRepository.deleteById(id);
	}
}
