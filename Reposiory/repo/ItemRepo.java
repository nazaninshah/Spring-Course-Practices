package ir.freeland.springboot.practice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Students;
import ir.freeland.springboot.practice.Item;

public interface ItemRepo extends CrudRepository<Item, Integer>{
    List<Item> findByName(String name);
    List<Item> findByNameIgnoreCase(String name);
    List<Item> findByNameEndingWith(String name);
    List<Item> findByNameEndingWithIgnoreCase(String name);
    List<Item> findByNameEndingWithIgnoreCaseOrderByIdDesc(String name);
}
