package fi.hh.project.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.project.Bookstore.domain.BookRepository;
import fi.hh.project.Bookstore.domain.Category;
import fi.hh.project.Bookstore.domain.Book;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	 @Autowired
	    private BookRepository repository;
	
	 @Test
	    public void findByTitleShouldReturnBook() {
	        List<Book> books = repository.findByTitle("Web Server Programming");
	        
	        assertThat(books).hasSize(1);
	        assertThat(books.get(0).getAuthor()).isEqualTo("Neil Gray");
	    }
	 
	 @Test
	    public void createNewBook() {
	    	Book book = new Book("Once", "Mike Daile", 2012, "685-5254567646", 9.00, new Category("Fantasy") );
	    	repository.save(book);
	    	assertThat(book.getId()).isNotNull();
	    }   
	 
	 @Test
	 	public void deleteBook() {
		 List<Book> books = repository.findByTitle("Web Server Programming");
	        
	        assertThat(books).hasSize(1);
	        repository.delete(books.get(0));
	        
	        List<Book> deletedbook = repository.findByTitle("Web Server Programming");
	        
	        assertThat(deletedbook).hasSize(0);
		 
	 }
}
