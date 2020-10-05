package com.collega.springmvc.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
import com.collega.springmvc.dao.CustomerDAOInterface;
import com.collega.springmvc.dao.KotaDAOInterface;
import com.collega.springmvc.model.CustomerModel;
import com.collega.springmvc.model.KotaModel;


@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private CustomerDAOInterface customer;
	@Autowired
	private KotaDAOInterface kota;

	
	
	@RequestMapping(value="/")
	public ModelAndView listCustomer(ModelAndView model) throws IOException{
		try{
			List<CustomerModel> listCustomer = customer.list();
			List<KotaModel> listKota = kota.list();
			CustomerModel customerModel = new CustomerModel();
			model.addObject("customerModel", customerModel);
			model.addObject("listCustomer", listCustomer);
			model.addObject("kotaList", listKota);
			model.setViewName("customer");
			
			return model;
		} catch (Exception e) {
		
			model.addObject("error", e.getMessage());
			model.setViewName("error");
			return model;
		}
	}
	
	@RequestMapping(value="/print")
	public ModelAndView print(ModelAndView model) throws IOException{
		try{
			 Calendar cal = Calendar.getInstance();
			 java.util.Date date = new java.util.Date();
			 java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
			 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		    
		   

		    String formattedDate=dateFormat.format(date);
			
			List<CustomerModel> listCustomer = customer.list();
		
			model.addObject("listCustomer", listCustomer);
			model.addObject("title", formattedDate);
			model.setViewName("print");
			
			return model;
		} catch (Exception e) {
		
			model.addObject("error", e.getMessage());
			model.setViewName("error");
			return model;
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RedirectView create(@ModelAttribute CustomerModel customerModel,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/customer/");
		try{
			String exac = customer.save(customerModel);
			if(exac != ""){
				throw new Exception("Failed");
			}
			redirectAttributes.addFlashAttribute("success", "Success");
		
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return redirectView;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute CustomerModel customerModel,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/customer/");
		try{
			String exac = customer.update(customerModel);
			if(exac != ""){
				throw new Exception("Failed");
			}
			redirectAttributes.addFlashAttribute("success", "Success");
		
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return redirectView;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public RedirectView deleteContact(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/customer/");
		try{
			customer.delete(request.getParameter("id"));
			redirectAttributes.addFlashAttribute("success", "Success");
						
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			
		}
		return redirectView;
		
	}
}
