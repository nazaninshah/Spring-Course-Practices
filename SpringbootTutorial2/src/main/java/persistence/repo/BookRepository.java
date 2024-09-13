package persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Book;

//query by naming
public interface BookRepository extends CrudRepository<Book, Integer>{
	List<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	
}
