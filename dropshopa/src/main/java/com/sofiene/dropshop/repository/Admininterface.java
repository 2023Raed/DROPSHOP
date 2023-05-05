package com.sofiene.dropshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sofiene.dropshop.models.Admin;

@Repository
public interface Admininterface extends CrudRepository<Admin, Long> {
	List<Admin> findAll();
	 Optional<Admin> findByEmail(String email);

}
