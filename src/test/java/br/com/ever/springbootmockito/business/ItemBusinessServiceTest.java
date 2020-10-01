package br.com.ever.springbootmockito.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ever.springbootmockito.data.ItemRepository;
import br.com.ever.springbootmockito.model.Item;

@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceTest {

	@InjectMocks
	private ItemBusinessService business;

	@Mock
	private ItemRepository repository;
	
	@Test
	public void retrieveAllItems_basic() {
		
		//cria o repositorio para o business
		Mockito  //
		.when(repository.findAll()) //
		.thenReturn(Arrays.asList( //
				new Item(2,"Item2",10,10), //
				new Item(3,"Item3",20,20))); 
		
		//executa metodo business com valores do repositorio criado acima
		List<Item> items = business.retrieveAllItems();
		
		assertEquals(100, items.get(0).getValue());
		assertEquals(400, items.get(1).getValue());
	}
	
	
}
