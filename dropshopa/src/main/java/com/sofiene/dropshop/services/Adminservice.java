package com.sofiene.dropshop.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sofiene.dropshop.models.Loginadmin;
import com.sofiene.dropshop.models.Admin;
import com.sofiene.dropshop.repository.Admininterface;

@Service
public class Adminservice {
	@Autowired
	private Admininterface adminRepo;

	// TO-DO: Write register and login methods!
	public Admin register(Admin newAdmin, BindingResult result) {
		Optional<Admin> potentialAdmin = adminRepo.findByEmail(newAdmin.getEmail());
		if (potentialAdmin.isPresent()) {
			result.rejectValue("email", "register error", "This email is already taken");
		}
		if (!newAdmin.getPassword().equals(newAdmin.getConfirm())) {
			result.rejectValue("confirm", "register error", "Passwords must match");
		}
		// Return the errors back
		if (result.hasErrors()) {
			return null;

		} else {
			String hashed = BCrypt.hashpw(newAdmin.getPassword(), BCrypt.gensalt());
			newAdmin.setPassword(hashed);

			// Save the Admin
			return adminRepo.save(newAdmin);

		}
	}

	public Admin login(Loginadmin newLoginObject, BindingResult result) {
		// Check if email exists in the DB
		Optional<Admin> potentialAdmin = adminRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialAdmin.isPresent()) {
			result.rejectValue("email", "login error", "Email not found in DB");
		} else {
			Admin admin = potentialAdmin.get();
			// returns true || false
			if (!BCrypt.checkpw(newLoginObject.getPassword(), admin.getPassword())) {
				result.rejectValue("password", "login error", "invalid password");
			}
			if (result.hasErrors()) {
				return null;
			} else {
				// return admin object
				return admin;
			}
		}

		return null;
	}

	public Admin findAdminById(Long id) {
		Optional<Admin> optionalAdmin = adminRepo.findById(id);
//	        
//	        if(optionalAdmin.isPresent()) {
//	            return optionalAdmin.get();
//	        } else {
//	            return null;
//	        }
		return optionalAdmin.isPresent() ? optionalAdmin.get() : null;
	}

}
