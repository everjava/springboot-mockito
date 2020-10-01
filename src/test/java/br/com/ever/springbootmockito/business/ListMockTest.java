package br.com.ever.springbootmockito.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ListMockTest {

	List<String> mock = mock(List.class);

	@Test
	void size_basic() throws Exception {
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	void returnDifferentValues() throws Exception {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	void returnWithParamenters() throws Exception {
		when(mock.get(0)).thenReturn("Everson Test");
		assertEquals("Everson Test", mock.get(0));
		assertEquals(null, mock.get(1));
	}

	
	@Test
	void returnWithGenericParamenters() throws Exception {
		when(mock.get(anyInt())).thenReturn("Everson Test");
		assertEquals("Everson Test", mock.get(0));
		assertEquals("Everson Test", mock.get(1));
	}
	
	
	@Test
	void verification_basic() throws Exception {
		 String value = mock.get(0);
		 verify(mock).get(0);
		 verify(mock).get(anyInt());
		 verify(mock, times(1)).get(anyInt());//verifica se é chamado 1 vez
	}
	
	
	 
	@Test
	void verification_basic2() throws Exception {
		 String value = mock.get(0);
		 String value2 = mock.get(1);
		 
		 verify(mock).get(0);
		 //verify(mock).get(anyInt());//aqui vai dar erro pq checa apenas uma vez, mas executou 2x
		 verify(mock, times(2)).get(anyInt());
		 verify(mock, atLeastOnce()).get(anyInt());
		 verify(mock, atMost(2)).get(anyInt());
		 //verify(mock, never()).get(1);//aqui vai dar erro pq foi chamado em mock.get(1)
		 verify(mock, never()).get(3);
		// verify(mock, times(1)).get(anyInt());//aqui vai dar erro pq chama 2x (value, value2)
		 
	}
	
	
	@Test
	public void argumentCapturing() {
		mock.add("SomeString");
		//Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);//pega valor do argumento 
		verify(mock).add(captor.capture());
		
		assertEquals("SomeString", captor.getValue());
		assertNotEquals("XXX", captor.getValue());
	}
	
	
	@Test
	public void multipleArgumentCapturing() {
		mock.add("SomeString1");
		mock.add("SomeString2");
		
		//Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		verify(mock, times(2)).add(captor.capture());
		
		List<String> allValues = captor.getAllValues();
		
		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));
	}
	
	
	@Test
	public void mocking() {
		ArrayList arrayListMock = mock(ArrayList.class);//mock nao retem o comportamento original da classe
		System.out.println(arrayListMock.get(0));//null
		System.out.println(arrayListMock.size());//0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());//0
		when(arrayListMock.size()).thenReturn(5);//qdo for chamado, retorna 5
		System.out.println(arrayListMock.size());//5
	}
	
	@Test
	public void spying() {
		ArrayList arrayListSpy = spy(ArrayList.class);//spy por default retem o comportamento original da classe
		arrayListSpy.add("Test0");//sem esta linha, a debaixo da erro
		System.out.println(arrayListSpy.get(0));//Test0
		System.out.println(arrayListSpy.size());//1
		arrayListSpy.add("Test");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());//3
		
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());//5
		
		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size());//5
		
		verify(arrayListSpy).add("Test4");
	 
	}
	
}
