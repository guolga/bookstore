package fi.hh.project.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.project.Bookstore.domain.Book;
import fi.hh.project.Bookstore.domain.BookRepository;
import fi.hh.project.Bookstore.domain.CategoryRepository;
import fi.hh.project.Bookstore.domain.Category;
import fi.hh.project.Bookstore.domain.User;
import fi.hh.project.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	} 
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository, UserRepository urepository) {
		return (args) -> {
			log.info("save new books");
			
			drepository.save(new Category("IT"));
			drepository.save(new Category("Romance"));
			drepository.save(new Category("Action"));
			drepository.save(new Category("Mystery"));
			drepository.save(new Category("Health"));
			drepository.save(new Category("Poetry"));
			drepository.save(new Category("Travel"));
			drepository.save(new Category("Dictionaries"));

			repository.save(new Book("Web Server Programming", "Neil Gray", 2003, "978-0470850978", 55.99, drepository.findByName("IT").get(0)));
			repository.save(new Book("Java Server Side Programming", "Mukesh Prasad", 2013, "952-5254634646", 32.99, drepository.findByName("IT").get(0)));
			repository.save(new Book("Yourney Around the World", "Andy Malcolm", 2015, "952-5254567646", 47.00, drepository.findByName("Travel").get(0)));
			repository.save(new Book("Into the Woods", "Henry Lloyd", 2001, "665-5254567646", 23.00, drepository.findByName("Mystery").get(0)));
			repository.save(new Book("Healthy Mind", "Lindsey Danes", 2012, "672-5254567646", 9.99, drepository.findByName("Health").get(0)));
			repository.save(new Book("Remember me", "Ella Molnar", 2016, "665-5254573326", 15.99, drepository.findByName("Romance").get(0)));

			User user1 = new User("guolga", "$2a$06$qSQDWssUvbMYkRHSTzbzc.kcmRIPXBwJpHyr4rxIKU/vnso6lojqK", "guolga@gmail.com", "USER");
			User user2 = new User("admin", "$2a$06$agdWTMxFom/Fj0o5sRAbOu5w57OjSRRNT9KnG.D.jnDG4yhs3A8PO", "admin@bookstore.fi", "ADMIN");
			User user3 = new User("nadezhda", "$2a$06$bNRQIPz0WL6AHG2pqlShi.t8aruWYO/ryMIMRASh5o9CPBmfUnJT.", "nadezhda@myy.haaga-helia.fi", "USER");

			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
};
}
}
