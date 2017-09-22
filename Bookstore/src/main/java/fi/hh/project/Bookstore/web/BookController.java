package fi.hh.project.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import fi.hh.project.Bookstore.domain.Book;
import fi.hh.project.Bookstore.domain.BookRepository;
import fi.hh.project.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
		
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("book", repository.findAll());
        return "booklist";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("category", drepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	repository.delete(id);
        return "redirect:../booklist";
    } 
    
    @RequestMapping(value="/edit/{id}")
    public String addBook(@PathVariable("id") Long id, Model model){
    	model.addAttribute("book", repository.findOne(id));
    	
        return "editbook";
        
    }     
    
	}	
	


