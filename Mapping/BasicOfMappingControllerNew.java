package ir.freeland.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class BasicOfMappingControllerNew {

    public BasicOfMappingControllerNew() {
        super();
    }

    // API

    // mapping examples

    @GetMapping("/books")
    @ResponseBody
    public String getBooksList() {
    	System.out.println("user inserted nothing but the /books !");
        return "This is a books list.";
        
    }
    
    @GetMapping("/book/{id}")
    @ResponseBody
    public String getBookById(@PathVariable final long id) {
    	System.out.println("user inserted the book id " + id + "for getting this book's detail");
        return "Just got the specific book with id=" + id;
    }
    
    @DeleteMapping("/book/{id}")
    @ResponseBody
    public String deleteBookById(@PathVariable final long id) {
    	System.out.println("user inserted the book id " + id + "for deleting this book");
        return "Deleted book with id " + id + " successfully";
    }
    
    @PutMapping("/book/{id}")
    @ResponseBody
    public String updateBookById(@PathVariable final long id) {
    	System.out.println("user inserted the book id " + id + "for updating this book");
        return "Updated book with id " + id + " successfully";
    }
    
    @PostMapping("/book/create")
    @ResponseBody
    public String createNewBook() {
    	System.out.println("user inserted nothing but the /book/create !");
        return "A new book just added to the list";
    }
    
    @GetMapping("/book/title/{title}")
    @ResponseBody
    public String getBookByTitle(@PathVariable final String title) {
    	System.out.println("user inserted the book title " + title + "for getting this book's detail");
        return "Just got the specific book with title=" + title;
    }
    
    @GetMapping("/book")
    @ResponseBody
    public String getBookByTitle(@RequestParam(name = "name", required = true) String name, @RequestParam(name = "publisher", required = true) String publisher) {
    	System.out.println("user inserted the writer's name " + name + " and the publisher name " + publisher +" for getting this book's detail");
    	return "Just got the specific book with name = " + name + " and publisher = " + publisher;
    }

}