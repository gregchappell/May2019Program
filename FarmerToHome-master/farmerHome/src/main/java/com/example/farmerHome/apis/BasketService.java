package com.example.farmerHome.apis;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Basket;
import com.example.farmerHome.repositories.BasketRepository;
import com.mastek.training.hrapp.entitiese.Employee;
import com.mastek.training.hrapp.entitiese.Project;



@Component
@Scope("singleton")
@Path("/Basket/")
public class BasketService {
	
	@Autowired
	private BasketRepository basketRepsoitory;
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GET
	@Path("/fetchProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Products> fetchProductByBasket(
			@QueryParam("basketId")Integer basketId){
			return productRepository.findByBasketId(basketId);
	}
	
	@GET
	@Path("/fetchProductsByConsumer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consumer> fetchProductByConsumer(
			@QueryParam("conso")Integer conso){
		currentBasketId = consumerRepository.findByConso(conso);
		return productRepository.findByBasketId(currentBasketId);
	}
	
	@Path("/find/{basketId}")
	@GET //HTTP method used to call the api
	@Produces({//declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in json
		MediaType.APPLICATION_XML // Object to be given in XML
	})
	@Transactional // help to fetch dependent data
	public Basket findByBasketId(
			// use the path parameter as the argument for the method
			@PathParam("basketId")int basketId) {
		try {
			Basket ba = basketRepsoitory.findById(basketId).get();
		
			return ba;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@DELETE //delete http method
	@Path("/delete/{basketId}")
	public void deleteByBasketId(@PathParam("basketId")int basketId) {
		basketRepsoitory.deleteById(basketId);
	}
	
	@DELETE //delete http method
	@Path("/deleteProduct/{ProductId}")
	public void deleteByProductId(@PathParam("productId")int productId) {
		basketRepsoitory.deleteById(productId);
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON) // object to be given in json
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Transactional
	public Basket registerOrUpdateBasket(@BeanParam Basket ba) {
		Basket currentBa = findByBasketId(ba.getBasketId());
		if(currentBa!= null) {
			currentBa.setCurrentConsumer(ba.getConso);
			
			ba = basketRepsoitory.save(currentBa);
			
		}
		else{ba = basketRepsoitory.save(ba);
		}
		
		System.out.println("Basket Registered" + ba);
		return ba;
	}
	
	@POST
	@Path("/assign/consumer")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@org.springframework.transaction.annotation.Transactional
	public Basket assignConsumer(@FormParam("basketId") int basketId, 
			@FormParam("consno")int consno) {
		try {
			
			Basket ba = findByBasketId(basketId);
			Consumer con = consumerRepository.findById(conso).get();
			con.getUsers().add(con);
			ba.setCurrentConsumer(con);
			registerOrUpdateBasket(ba);
			return ba;
		} catch(Exception e) {
			e.printStackTrace();
		return null;
	}}

	@Transactional
	@POST
	@Path("/assign/products")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Products>assignProduct(@FormParam("basketId") int basketId, @FormParam("productId") int productId) {
		try {
			Basket ba = findByBasketId(basketId);
			Products pr = productsService.findById(productId).get();
			
			ba.getOrders().add(pr);
			ba = registerOrUpdateBasket(ba);
			
			return ba.getOrders();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}