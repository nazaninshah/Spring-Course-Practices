package persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Address;
import persistence.model.Book;
import persistence.model.Bookstore;

public interface BookstoreRepository extends CrudRepository<Bookstore, Integer>{
	List<Bookstore> findById(int id);
	List<Bookstore> findByName(String name);
	List<Book> findByAddress(Address address);
}
