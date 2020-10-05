package com.collega.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/")
	public ModelAndView Dashboard(ModelAndView model) {
		
		model.setViewName("dashboard");
		return model;
	}
	
//	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
//	public ModelAndView saveContact(@ModelAttribute CustomerModel contact) {
//		customer.save(contact);		
//		return new ModelAndView("redirect:/");
//	}
	
//	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
//	public ModelAndView deleteContact(HttpServletRequest request) {
//		int contactId = Integer.parseInt(request.getParameter("id"));
//		contactDAO.delete(contactId);
//		return new ModelAndView("redirect:/");
//	}
//	
//	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
//	public ModelAndView editContact(HttpServletRequest request) {
//		int contactId = Integer.parseInt(request.getParameter("id"));
//		Contact contact = contactDAO.get(contactId);
//		ModelAndView model = new ModelAndView("ContactForm");
//		model.addObject("contact", contact);
//		
//		return model;
//	}
//	@RequestMapping(value = "/")
//	public ModelAndView test(HttpServletResponse response) throws IOException {
//		return new ModelAndView("dashboard");
//	}
}
