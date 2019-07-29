package com.example.farmerHome;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.farmerHome.apis.BasketService;
import com.example.farmerHome.entities.Basket;
import com.mastek.training.hrapp.entitiese.Department;
import com.mastek.training.hrapp.entitiese.Employee;
import com.mastek.training.hrapp.entitiese.Project;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketApplicationTests {

	@Autowired
	BasketService baskService;
	
	@Test
	public void assignConsumerToBasket() {
		int basketId = 29;
		int consno = 17;
		Basket ba = baskService.assignConsumer(basketId, consno);
		assertNotNull(ba.getCurrentConsumer());
	}
	
	@Test
	public void assignProductToBasket() {
		int basketId = 29;
		int consno = 2;
		
		Set<Products> products = baskService.assignProduct(basketId, productId);
		assertNotNull(products);
		
	}
	
	
	@Test
	public void deleteByProductIdUsingService() {
		int productId = 2;
		baskService.deleteByProductId(productId);
		assertNull(baskService.findByProductId(productId));
	}
	
	@Test
	public void deleteByBasketIdUsingService() {
		int basketId = 2;
		baskService.deleteByBasketId(basketId);
		assertNull(baskService.findByBasketId(basketId));
	}
	
	
}
