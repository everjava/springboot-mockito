package br.com.ever.springbootmockito.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.ever.springbootmockito.model.Item;
/**
 * 1- (If you are using JUnit 5) In the next lecture, You do NOT need to add  RunWith(SpringRunner.class) </br>  
 * 2 - pode colocar data.sql dentro de tests/resources e carregar os dados de lá para testes
 */

//@RunWith(SpringRunner.class) Junit4
@DataJpaTest
public class ItemRepositoryTest {

	
	@Autowired
	private ItemRepository repository;
	
	
	@Test
	public void testFindAll() {
		List<Item> items = repository.findAll();
		assertEquals(3,items.size());
	}

	@Test
	public void testFindOne() {
		Item item = repository.findById(10001).get();
		
		assertEquals("Item1",item.getName());
	}
	
}
