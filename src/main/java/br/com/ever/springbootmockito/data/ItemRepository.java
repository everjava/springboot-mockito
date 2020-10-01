package br.com.ever.springbootmockito.data;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ever.springbootmockito.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
