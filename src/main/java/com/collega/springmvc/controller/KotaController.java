package com.collega.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.collega.springmvc.dao.CustomerDAOInterface;
import com.collega.springmvc.dao.KotaDAOInterface;
import com.collega.springmvc.model.CustomerModel;
import com.collega.springmvc.model.KotaModel;
@Controller
@RequestMapping(value="/kota")
public class KotaController {

	@Autowired
	private KotaDAOInterface kota;
	
	@RequestMapping(value="/")
	public ModelAndView listCustomer(ModelAndView model) throws IOException{
		try{
			
			List<KotaModel> listKota = kota.list();
			model.addObject("kotaModel", new KotaModel());
			model.addObject("kotaList", listKota);
			model.setViewName("kota");
			
			return model;
		} catch (Exception e) {
			
			model.addObject("error", e.getMessage());
			model.setViewName("kota");
			return model;
		}
	}
	
	

	

	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RedirectView create(@ModelAttribute KotaModel kotaModel,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/kota/");
		try{
			String exac = kota.save(kotaModel);
			if(exac != ""){
				throw new Exception(exac);
			}
			redirectAttributes.addFlashAttribute("success", "Success");
		
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return redirectView;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute KotaModel kotaModel,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/kota/");
		try{
			String exac = kota.update(kotaModel);
			if(exac != ""){
				throw new Exception(exac);
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
		redirectView.setUrl("/kota/");
		
		try{
			String execa = kota.delete(request.getParameter("id"));
			if(execa != ""){
				throw new Exception(execa);
			}
			redirectAttributes.addFlashAttribute("success", "Success");
			
						
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		
		}
		return redirectView;
		
	}
}
