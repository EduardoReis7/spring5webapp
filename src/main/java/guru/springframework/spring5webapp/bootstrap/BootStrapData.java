package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting in BootStrap");
		
		
		Publisher dallas = new Publisher();
		dallas.setName("Dallas");
		dallas.setAdressLine1("Rua Acácio Fernandes, 101");
		dallas.setCity("Porto Alegre");
		dallas.setState("RS");
		dallas.setZip("94402-123");
		
		publisherRepository.save(dallas);
		System.out.println("Publishers Count: " + publisherRepository.count());
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "12342314");
		
		eric.getBooks().add(ddd);
		ddd.getAuthor().add(eric);
		ddd.setPublisher(dallas);
		dallas.getBooks().add(ddd);

		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(dallas);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEJBS = new Book("J2EE Development without EJB", "12314235");
		
		rod.getBooks().add(noEJBS);
		noEJBS.getAuthor().add(rod);
		noEJBS.setPublisher(dallas);
		dallas.getBooks().add(noEJBS);
		
		authorRepository.save(rod);
		bookRepository.save(noEJBS);
		publisherRepository.save(dallas);
		
		System.out.println("Publisher Number of Books: " + dallas.getBooks().size());
		System.out.println("Number of books: " + bookRepository.count());
	}

}
