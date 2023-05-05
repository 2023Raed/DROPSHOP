package com.sofiene.dropshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sofiene.dropshop.models.Loginuser;
import com.sofiene.dropshop.models.User;
import com.sofiene.dropshop.services.Userservice;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Usercontrollers {
	@Autowired
	private Userservice userServ;
	
	// Show the authentication page
	@GetMapping("/")
	public String loginPage(@ModelAttribute("newUser") User newUser,
							@ModelAttribute("newLogin") Loginuser newLogin,
							Model model) {
		
	      model.addAttribute("newUser", new User());
	      model.addAttribute("newLogin", new Loginuser());
		return "loginPage.jsp";
	}
	// Register === Process register from === POST
		@PostMapping("/register")
		public String register(@Valid @ModelAttribute("newUser") User newUser, 
	            BindingResult result, Model model, HttpSession session) {
			
			 // call a register method in the service
	        userServ.register(newUser, result);
			
	        if(result.hasErrors()) {
	            // Be sure to send in the empty LoginUser before 
	            // re-rendering the page.
	            model.addAttribute("newLogin", new Loginuser());
	            return "RegistrationPage.jsp";
	        }
	        // No errors! 
	        // Store their ID from the DB in session, 
	        session.setAttribute("user_id", newUser.getId());
	        session.setAttribute("userName", newUser.getUserName());
	        return "redirect:/Login.jsp";
	    }
		// === Render the success page ===
		
		// Login === Process login from === POST
		@PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") Loginuser newLogin, 
	            BindingResult result, Model model, HttpSession session) {
	        
	        // Add once service is implemented:
	         User user = userServ.login(newLogin, result);
	    
	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "login.jsp";
	        }
	    
	        
	        session.setAttribute("user_id", user.getId());
	       
	        session.setAttribute("userName", user.getUserName());
	        return "redirect:/Dashboard.jsp";
	    }
		
		@GetMapping("/logout")
		public String logout(HttpSession sesh) {
			sesh.invalidate();
			return "redirect:/";
		}
	
	

}
