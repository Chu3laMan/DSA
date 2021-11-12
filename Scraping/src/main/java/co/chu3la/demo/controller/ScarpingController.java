package co.chu3la.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.chu3la.demo.service.PdfService;

@Controller
@RequestMapping("/extract")
public class ScarpingController {
	
	@Autowired
	private PdfService pdfService;
	
	
	
	@GetMapping(value = "pdf")
	public String pdfExctractor() {
		pdfService.extractHighlightedWords("/home/chu3la/Documents/DocumentFragment_29949220.pdf");
		return "pdf";
	}

}
