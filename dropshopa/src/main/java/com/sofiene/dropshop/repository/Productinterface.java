package com.sofiene.dropshop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sofiene.dropshop.models.Product;

@Repository
public interface Productinterface extends CrudRepository<Product, Long> {
	List<Product> findAll();

}
