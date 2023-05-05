package com.sofiene.dropshop.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="delivery")
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull()
    @Size(min = 3, max = 200, message="adresse should not be empty")
	private String adressedepart;
	
	
	
	
	@NotNull()
    @Size(min = 3, max = 200, message="adresse should not be empty")
	private String adressefinale;
	

	
	 @Column(updatable=false)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date createdAt;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date updatedAt;
	 
	 

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="user_id")
	 private User user;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="product_id")
	 private Product product;
	 
	 
	   public Delivery () {};    

	 
	 
			 @PrePersist
			    protected void onCreate(){
			        this.createdAt = new Date();
			    }
			 @PreUpdate
			    protected void onUpdate(){
			        this.updatedAt = new Date();
			    }

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getAdressedepart() {
				return adressedepart;
			}

			public void setAdressedepart(String adressedepart) {
				this.adressedepart = adressedepart;
			}

			public String getAdressefinale() {
				return adressefinale;
			}

			public void setAdressefinale(String adressefinale) {
				this.adressefinale = adressefinale;
			}

			public Date getCreatedAt() {
				return createdAt;
			}

			public void setCreatedAt(Date createdAt) {
				this.createdAt = createdAt;
			}

			public Date getUpdatedAt() {
				return updatedAt;
			}

			public void setUpdatedAt(Date updatedAt) {
				this.updatedAt = updatedAt;
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}

			
			

}
