package com.collega.springmvc.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.InputStream;
import java.io.OutputStream;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collega.springmvc.dao.CustomerDAOInterface;
import com.collega.springmvc.dao.KotaDAOInterface;
import com.collega.springmvc.model.CustomerModel;
import com.collega.springmvc.model.KotaModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;    

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private CustomerDAOInterface customer;
	@Autowired
	private KotaDAOInterface kota;

	
	
	@RequestMapping(value="")
	public ModelAndView listCustomer(ModelAndView model,HttpServletRequest request) throws IOException{
		String page_data = request.getParameter("page");
		int pageID  = 0;
		if ((page_data != null) && (page_data != "")) {
			pageID = Integer.parseInt(page_data);
		}
		try{
		
			List<CustomerModel> listCustomer = customer.list(pageID);
			List<KotaModel> listKota = kota.list();
			CustomerModel customerModel = new CustomerModel();
			String page = customer.getTotalData(pageID);
			model.addObject("customerModel", customerModel);
			model.addObject("listCustomer", listCustomer);
			model.addObject("kotaList", listKota);
			model.addObject("page", page);
		
			model.setViewName("customer");
			
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
			model.setViewName("customer");
			return model;
		}
	}
	
	

	@RequestMapping(value = "/print", method = RequestMethod.GET)
	@ResponseBody
	public void getRpt1(HttpServletResponse response) throws JRException, IOException {
	    InputStream jasperStream = this.getClass().getResourceAsStream("/Invoice.jasper");
	    Map<String,Object> parameters = new HashMap<>();
	    
	    List<CustomerModel> dataList = customer.list(-1);
	    
	    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	    
	    LocalDateTime dateTime = LocalDateTime.now();
	  
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename="+dateTime+"-Data Customer.pdf");
	
	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}



	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RedirectView create(@ModelAttribute CustomerModel customerModel,final RedirectAttributes redirectAttributes) {
		
		try{	
			RedirectView redirectView = new RedirectView();
			redirectView.setContextRelative(true);
			redirectView.setUrl("/customer");
			String exac = customer.save(customerModel);
			if(exac != ""){
				throw new Exception("Failed");
			}
			redirectAttributes.addFlashAttribute("success", "Success");
			return redirectView;
		
		} catch (Exception e) {
			RedirectView redirectView = new RedirectView();
			redirectView.setContextRelative(true);
			redirectView.setUrl("/customer/");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return redirectView;
		}
	
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute CustomerModel customerModel,final RedirectAttributes redirectAttributes) {

		try{
			RedirectView redirectView = new RedirectView();
			redirectView.setContextRelative(true);
			redirectView.setUrl("/customer");
			String exac = customer.update(customerModel);
			if(exac != ""){
				throw new Exception("Failed");
			}
			redirectAttributes.addFlashAttribute("success", "Success");
			return redirectView;
		
		} catch (Exception e) {
			e.printStackTrace();
			RedirectView redirectView = new RedirectView();
			redirectView.setContextRelative(true);
			redirectView.setUrl("/customer/");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return redirectView;
		}
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteContact(HttpServletRequest request,RedirectAttributes redirectAttributes) {

		try{
//			RedirectView redirectView = new RedirectView();
//			redirectView.setContextRelative(true);
//			redirectView.setUrl("/customer/");
			customer.delete(request.getParameter("id"));
			redirectAttributes.addFlashAttribute("success", "Success");
			return "redirect:/customer";	
		} catch (Exception e) {
//			RedirectView redirectView = new RedirectView();
//			redirectView.setContextRelative(true);
//			redirectView.setUrl("/customer/");
//			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/customer";	
			
		}
		
		
	}
}
