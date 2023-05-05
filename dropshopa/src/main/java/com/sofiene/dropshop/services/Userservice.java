package com.sofiene.dropshop.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sofiene.dropshop.models.Loginuser;
import com.sofiene.dropshop.models.User;
import com.sofiene.dropshop.repository.Userinterface;

@Service
public class Userservice {
	@Autowired
	private Userinterface userRepo;

	// TO-DO: Write register and login methods!
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "register error", "This email is already taken");
		}
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "register error", "Passwords must match");
		}
		// Return the errors back
		if (result.hasErrors()) {
			return null;

		} else {
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);

			// Save the User
			return userRepo.save(newUser);

		}
	}

	public User login(Loginuser newLoginObject, BindingResult result) {
		// Check if email exists in the DB
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "login error", "Email not found in DB");
		} else {
			User user = potentialUser.get();
			// returns true || false
			if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("password", "login error", "invalid password");
			}
			if (result.hasErrors()) {
				return null;
			} else {
				// return user object
				return user;
			}
		}

		return null;
	}

	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
//	        
//	        if(optionalUser.isPresent()) {
//	            return optionalUser.get();
//	        } else {
//	            return null;
//	        }
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

}
