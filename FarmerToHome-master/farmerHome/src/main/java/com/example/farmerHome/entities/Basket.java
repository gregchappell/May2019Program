package com.example.farmerHome.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Entity
@Table(name = "JPA_BASKET")

public class Basket implements Serializable {
	
	@FormParam("basketId")
	@Value("-1")
	private int basketId;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Many to Many relationship with products

	private Set<Products> orders = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinTable(name="JPA_ORDERS", joinColumns=@JoinColumn(name="basket_id_fk"),
			inverseJoinColumns = @JoinColumn(name = "product_id_fk"))
	public Set<Products> getOrders() {
		return orders;
	}	
	public void setOrders(Set<Products> orders) {
		this.orders = orders;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Many to One relationship with Consumers
	
	private Consumer currentConsumer;
	
	@ManyToOne
	@JoinColumn(name="consumer_id_fk")
	public Consumer getCurrentConsumer() {
		return currentConsumer;
	}
	public void setCurrentConsumer(Consumer currentConsumer) {
		this.currentConsumer = currentConsumer;
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public Basket() {
		System.out.println("Basket Created");
		
	}
	@Id
	@Column(name = "Basket_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBasketId() {
		return basketId;
		
	}
	
	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + "]";
	}
	

}
