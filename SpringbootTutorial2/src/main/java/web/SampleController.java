package web;

import org.hibernate.annotations.AnyDiscriminator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import persistence.model.Book;
import persistence.model.ResponseTransfer;
import persistence.repo.AddressRepository;
import persistence.repo.BookRepository;
import persistence.repo.BookstoreRepository;
import persistence.repo.OwnersRepository;
import web.exception.BookIdMismatchException;
import web.exception.BookNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Validated
public class SampleController {
	
	@Autowired
	private AddressRepository addressRepo;
	private BookRepository bookRepo;
	private BookstoreRepository bookstrRepo;
	private OwnersRepository ownerRepo;
	
	//BookControllers
	@GetMapping("/book/all")
	public Iterable<Book> findAll(){
		return bookRepo.findAll();
	}
	
	//sending id after ?id=124
	@GetMapping("/book/byId")
	public Iterable<Book> findById(@RequestParam("id") @Min(5) int id ){
		return bookRepo.findAll();
	}
	
	//sending reqBody in json
	@PostMapping("/book/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@Valid @RequestBody Book book) {
		Book book1 = bookRepo.save(book);
		return book1;
	}
	
	// sending id after /124
	@DeleteMapping("/book/delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseTransfer deleteBook(@PathVariable int id) {
		bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
		bookRepo.deleteById(id);
		return new ResponseTransfer("book deleted");
	}
	
	@PutMapping("/book/update/{id}")
	public ResponseTransfer updateBook(@Valid @RequestBody Book book, @PathVariable int id) {
		if (book.getId() != id) {
			throw new BookIdMismatchException();
		}
		bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
		bookRepo.save(book);
		return new ResponseTransfer("book with id" + id + "updated");
	}
	

}
