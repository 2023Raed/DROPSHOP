package com.sofiene.dropshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sofiene.dropshop.models.Loginadmin;
import com.sofiene.dropshop.models.Admin;
import com.sofiene.dropshop.services.Adminservice;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Admincontrollers {
	@Autowired
	private Adminservice adminServ;
	
	// Show the authentication page
	@GetMapping("/admin")
	public String loginPage(@ModelAttribute("newAdmin") Admin newAdmin,
							@ModelAttribute("newLogin") Loginadmin newLogin,
							Model model) {
		
	      model.addAttribute("newAdmin", new Admin());
	      model.addAttribute("newLogin", new Loginadmin());
		return "loginAdminPage.jsp";
	}
			// === Render the success page ===
		
		// Login === Process login from === POST
		@PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") Loginadmin newLogin, 
	            BindingResult result, Model model, HttpSession session) {
	        
	        // Add once service is implemented:
	         Admin admin = adminServ.login(newLogin, result);
	    
	        if(result.hasErrors()) {
	            model.addAttribute("newAdmin", new Admin());
	            return "loginAdminPage.jsp";
	        }
	    
	        
	        session.setAttribute("admin_id", admin.getId());
	       
	        session.setAttribute("adminName", admin.getAdminName());
	        return "redirect:/DashboardAdmin.jsp";
	    }
		
		@GetMapping("/logout")
		public String logout(HttpSession sesh) {
			sesh.invalidate();
			return "redirect:/admin";
		}
	
	

}
