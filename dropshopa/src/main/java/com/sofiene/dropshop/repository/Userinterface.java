package com.sofiene.dropshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sofiene.dropshop.models.User;

@Repository
public interface Userinterface extends CrudRepository<User, Long> {
	List<User> findAll();
	 Optional<User> findByEmail(String email);

}

