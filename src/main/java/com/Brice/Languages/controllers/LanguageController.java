package com.Brice.Languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Brice.Languages.models.Language;
import com.Brice.Languages.services.LanguageService;

@Controller
public class LanguageController {
	private final LanguageService languageService;
	
	public LanguageController(LanguageService languageService) {
		this.languageService = languageService;
	}
		// Root route --------------------------------------------------------------------------------------
	@RequestMapping("/")
	public String root() {
		return "redirect:/languages";
	}
		// Show all languages route ------------------------------------------------------------------------
	@RequestMapping("/languages")
	public String index(@ModelAttribute("languages") Language language, Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Char must be between 5-20");
		List<Language> Languages = languageService.allLanguages();
		model.addAttribute("languagesList", Languages);
		return "/languages/index.jsp";
	}
		// Create new ---------------------------------------------------------------------------------------
	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("languagesList") Language language, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Char must be between 5-20");
			System.out.println("there are errors on the page");
			return "redirect:/languages";
		}
		else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}
	}
	
	// Show a single language---------------------------------------------------------------------------
	@RequestMapping("/languages/{id}")
	public String view(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Language language = languageService.findLanguage(id);
		if (language != null) {
			model.addAttribute("language", language);
			return "/languages/view.jsp";
		}
		else {
			redirectAttributes.addFlashAttribute("error", "That language ID does not exist.");
			return "redirect:/languages";
		}
	}
	
		// Edit routes --------------------------------------------------------------------------------------
	@RequestMapping("/languages/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("languageEdit", language);
		return "/languages/edit.jsp";
	}
		// Edit languages -----------------------------------------------------------------------------------
	@RequestMapping(value="/languages/{id}/editing")
	public String update(@Valid @ModelAttribute("languages") Language language, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Has errors to be fixed.");
			return "/languages/edit.jsp";
		}
		else {
			languageService.updateLanguage(language);
			return "redirect:/languages";
		}
	}
		// Delete Language -----------------------------------------------------------------------------------
	@RequestMapping(value="/languages/delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}

