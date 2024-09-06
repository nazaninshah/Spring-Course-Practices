package ir.freeland.springboot.practice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Students;
import ir.freeland.springboot.practice.corruptedItem;

public interface CorruptedRepo extends CrudRepository<corruptedItem, Long>{
	List<Students> findByItem(String name);
    List<Students> findByReasonIgnoreCase(String name);
}
