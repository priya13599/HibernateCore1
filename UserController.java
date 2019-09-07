package com.nucleus.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nucleus.dao.ICustomerDao;
import com.nucleus.model.Customer;
import com.nucleus.model.Role;
import com.nucleus.model.User;
import com.nucleus.service.ICustomerService;
import com.nucleus.service.IUserService;


@Controller
public class UserController {
	
	@Autowired
	ICustomerDao customerDao;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IUserService userService;
	

	Date date = new Date();
	SimpleDateFormat smd = new SimpleDateFormat("dd/MMM/yyyy");
	String modifieddate = smd.format(date);
	

	@RequestMapping("/insertcustomerdetailsform")
	public ModelAndView request1(Customer customer)
	{
		return new ModelAndView("insertcustomerdetailsform");
	}
	
	
	@RequestMapping("/insertCustomer")
	public ModelAndView request2(@Valid Customer customer,BindingResult result,Principal principal)
	{
		
		if(result.hasErrors())
		{
			return new ModelAndView("insertcustomerdetailsform");
		}
		
		int i = customerService.checkprimaryKey(customer.getCustomerCode());
		if(i==1)
		{
		customer.setCreatedBy(principal.getName());
		customer.setRecordStatus("N");
	
			try {
				customer.setCreateDate(new SimpleDateFormat("dd/MMM/yyyy").parse(modifieddate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		ModelAndView model = new ModelAndView();
		customerService.saveCustomer(customer);
		/*List<Customer> customerlist=customerService.displayCustomer1();
		model.addObject("customerlist",customerlist);*/
		model.addObject("flag","Record Inserted Successfully");
		model.setViewName("insertcustomerdetailsform");
		return model;
		}
		else
		{
			ModelAndView model = new ModelAndView();
			model.addObject("flag","Unique Constraint Voileted:- CustomerCode exists In Database");
			model.setViewName("insertcustomerdetailsform");
			 return model;
		}
		
	}
	
	
	@RequestMapping("/deletecustomerdetailsform")
	public ModelAndView request3(Customer customer)
	{
		return new ModelAndView("deletecustomerdetailsform");
	}

	
	@RequestMapping("/deleteCustomer")
	public ModelAndView request4(@Valid Customer customer,BindingResult result)
	{
		ModelAndView model = new ModelAndView();
		int flag = customerService.deleteCustomer(customer);
		if(flag==0)
		{
		
		model.addObject("flag","Record Deleted Successfully");
		model.setViewName("deletecustomerdetailsform");
		return model;
		}
		else
		{
			model.addObject("flag","No Such Record Found In Database");
			model.setViewName("deletecustomerdetailsform");
			return model;
		}
		
	}
	
	
	
	@RequestMapping("/displaycustomerdetailsform")
	public ModelAndView request5(Customer customer)
	{
		return new ModelAndView("displaycustomerdetailsform");
	}
	
	
	@RequestMapping("/displayCustomer")
	public ModelAndView request6(@Valid Customer customer,BindingResult result)
	{
		ModelAndView model = new ModelAndView();
		Customer customer1 = customerService.displayCustomer(customer);
		if(customer1!=null)
		{
		
		model.addObject("flag","sn1");
		model.addObject("customer",customer1);
		model.setViewName("displaycustomerdetails");
		return model;
		}
		else
		{
			model.addObject("flag","No Such Record Found In Database");
			model.setViewName("displaycustomerdetailsform");
			return model;
			
		}
		
	}
	
	
	@RequestMapping("/displaycustomerdetailsform1")
	public ModelAndView request7(Customer customer)
	{
		return new ModelAndView("displaycustomerdetailsform1");
	}
	
	
	@RequestMapping("/displayCustomers")
	public ModelAndView request8(@Valid Customer customer,BindingResult result)
	{
		
		List<Customer> customer1 = customerService.displayCustomer1();
		ModelAndView model = new ModelAndView();
		model.addObject("flag","sn2");
		model.addObject("customer",customer1);
		model.setViewName("displaycustomerdetails");
		return model;
		
	}
	
	
	@RequestMapping("/updatecustomerdetailsform")
	public ModelAndView request11(Customer customer)
	{
		return new ModelAndView("updatecustomerdetailsform");
	}
	
	@RequestMapping("/updateCustomer")
	public ModelAndView request12(@Valid Customer customer,BindingResult result)
	{
		ModelAndView model = new ModelAndView();
		
		Customer customer1 =customerService.updateCustomer(customer);
		if(customer1!=null)
		{
		java.util.Date createdate = customer1.getCreateDate();
		if(createdate!=null)
		{
		java.sql.Date dt = new java.sql.Date(createdate.getTime());   // Util date converted into sql date
		model.addObject("date",dt);
		}
		else
		{
			model.addObject("date",(Date) createdate);     //this allows JDBC to identify this is a sql date
		}
	
		/*model.addObject("date",customer1.getCreateDate());*/
		model.addObject("by",customer1.getCreatedBy());
		model.addObject("customer",customer1);
		model.setViewName("updatecustomerdetails");
		return model;
		}
		else
		{
			model.addObject("flag","No Such Record Found In Database");
			model.setViewName("updatecustomerdetailsform");
			return model;
		}
	}
	
	
	@RequestMapping("/updateCustomerDetails")
	public ModelAndView request13(@Valid Customer customer,BindingResult result,Principal principal)
	{
		/*if(result.hasErrors())
		{
			return new ModelAndView("updatecustomerdetailsform");
		}*/
		customer.setRecordStatus("N");
		customer.setModifiedDate(date);
		customer.setModifiedBy(principal.getName());
	    
		ModelAndView model = new ModelAndView();
		customerService.updateCustomerdetails(customer);
		Customer cust = new Customer();
		model.addObject("customer",cust);
		model.addObject("flag","Record Updated Successfully");
		model.setViewName("updatecustomerdetails");
		return model;
        
	}
	
	
	@RequestMapping("/ascendingorder")
	public ModelAndView request9()
	{
		List<Customer> customer =customerService.displayascendingorder();
		ModelAndView model = new ModelAndView();
		model.addObject("flag","sn2");
		model.addObject("customer",customer);
		model.setViewName("displaycustomerdetails");
		return model;
	}
	
	
	@RequestMapping("/descendingorder")
	public ModelAndView request10()
	{
		List<Customer> customer =customerService.displaydescendingorder();
		ModelAndView model = new ModelAndView();
		model.addObject("flag","sn2");
		model.addObject("customer",customer);
		model.setViewName("displaycustomerdetails");
		return model;
	}
	

	@RequestMapping("/registeruserform")
	public ModelAndView request11(User user,Role role)
	{
		System.out.println("Priya");
		return new ModelAndView("registeruserform");
	}

	
	@RequestMapping("/registerdetails")
	public ModelAndView request12(User user,@RequestParam("role0") String[] role)
	{
		ModelAndView model = new ModelAndView();
		int i = userService.checkuserid(user.getUserId());
		if(i==1)
		{
	userService.SaveUser1(user,role);
	
	User user1= new User();
	model.addObject("user",user1);
	model.addObject("flag","Successfully Registered");
	model.setViewName("registeruserform");
	
	
	return model;
		}
		else
		{
			model.addObject("flag","Unique Constraint Voileted:- CustomerCode exists In Database");
			model.setViewName("registeruserform");
			return model;
		}
   }
	@RequestMapping("/displayall")
	public ModelAndView  display(HttpServletRequest request)

	{


		List<Customer> customerList = customerService.displayCustomer1();
		return new ModelAndView("dynamic","customerList",customerList);



	}
}