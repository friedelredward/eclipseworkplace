package com.sbtutorial.forms1.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sbtutorial.forms1.models.SupportForm;

@Controller
public class SupportFormController {
	
	@GetMapping("/sf")
	public String supportForm(Model model){
		//we bind the model to the form class. so we send it to the ui
		model.addAttribute("supportDetails", new SupportForm());
		
		// we bind some data for the view to render
		List<String> cList= Arrays.asList("content1","content2","content3");
		model.addAttribute("contentList", cList);
		
		return "supporForm";
	}
	
	@PostMapping("/sf")
	public String submitSupportForm(@ModelAttribute SupportForm supportForm, Model model){
		//we bind the model to the form class. so we send it to the ui
		model.addAttribute("supportDetails", supportForm);
		
		return "submitSupportForm";
	}
}
