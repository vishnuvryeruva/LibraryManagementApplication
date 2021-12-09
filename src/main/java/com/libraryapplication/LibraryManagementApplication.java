package com.libraryapplication;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;

import com.libraryapplication.entities.Book;
import com.libraryapplication.entities.User;
import com.libraryapplication.services.BookService;
import com.libraryapplication.services.UserService;
import com.libraryapplication.utils.ApplicationRefresh;

@SpringBootApplication
@EnableOAuth2Sso
public class LibraryManagementApplication {

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@Autowired
	ApplicationRefresh applicationRefresh;	

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			User user1 = new User("umang1234", "test", "umangpatel@gmail.com", "Umang", "Patel", "34567",
					"06-11433823", "CA");

			User user2 = new User("nevilshah444", "test", "nevilshah444@gamail.com", "Nevil", "Shah",
					"174", "06-87054875", "CA");
			user2.setRole("ROLE_ADMIN");

			User user3 = new User("sarjakpatel", "test", "sarjakpatel1999@gmail.com", "Sarjak", "Patel", "12345",
					"06-18756892", "CA");
			user3.setRole("ROLE_USER");
			
			User user4 = new User("sarjak", "test", "Sarjak", "Patel", "1234",
					"2345", "06-83472443", "CA");
			User user5 = new User("Sagar", "test", "sagar@gmail.com", "Sagar", "Raval", "134566",
					"06-13644621", "CA");
			
			User user6 = new User("vishnuvryeruva", "test", "vishnu@gmail.com", "Vishnu", "Yeruva", "134566",
					"06-13644621", "CA");
			user6.setRole("ROLE_EMPLOYEE");

			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);
			userService.save(user5);
			userService.save(user6);
	

			Book book1 = new Book("The Pragmatic Programmer", "David Thomas, Andrew Hunt", 2006, 1);
			Book book2 = new Book("Clean Code", "Robert C. Martin", 2020, 2);
			Book book3 = new Book("Code Complete", "Steve McConnell", 2012, 1);
			Book book4 = new Book("Refactoring", "Martin Fowler", 2017, 4);
			Book book5 = new Book("Head First Design Patterns",
					"Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson", 2019, 5);
			Book book6 = new Book("The Mythical Man-Month", "Frederick P. Brooks Jr", 1999, 1);
			Book book7 = new Book("The Clean Coder", "Robert Martin", 2021, 3);
			Book book8 = new Book("Working Effectively with Legacy Code", "Micheal Feathers", 2015, 1);
			Book book9 = new Book("Design Patterns", "Erich Gamma, Richard Helm. Ralph Johnson, John Vlissides", 2019,
					2);
			Book book10 = new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", 2018, 3);
			Book book11 = new Book("Rework", "Jason Fried, David Heinemeier Hansson", 2011, 1);
			Book book12 = new Book("Don't Make Me Think", "Steve Krug", 2005, 1);
			Book book13 = new Book("Code", "Charles Petzold", 2017, 1);
			Book book14 = new Book("Peopleware", "Tom DeMarco, Tim Lister", 2013, 3);
			Book book15 = new Book("Introduction to Algorithms", "Thomas H. Cormen", 2020, 2);
			Book book16 = new Book("Programming Pearls", "Jon Bently", 1998, 1);
			Book book17 = new Book("Patterns of Enterprice Application Architecture", "Martin Fowler", 2020, 2);
			Book book18 = new Book("Structure and Interpretation of Computer Programs",
					"Harold Abelson, Gerald Jay Sussman, Julie Sussman", 2013, 1);
			Book book19 = new Book("The Art of Computer Programming", "Donald E. Knuth", 2014, 4);
			Book book20 = new Book("Domain-Driven Design", "Eric Evans", 2010, 2);
			Book book21 = new Book("Coders at Work", "Peter Seibel", 2009, 1);
			Book book22 = new Book("Rapid Development", "Steve McConnell", 1995, 6);
			Book book23 = new Book("The Self-Taught Programmer", "Cory Althoff", 2021, 1);
			Book book24 = new Book("Algorithms", "Robert Sedgewick, Kevin Wayne", 2000, 3);
			Book book25 = new Book("Continuous Delivery", "Jez Humble, David Farley", 2003, 1);

			bookService.save(book1);
			bookService.save(book2);
			bookService.save(book3);
			bookService.save(book4);
			bookService.save(book5);
			bookService.save(book6);
			bookService.save(book7);
			bookService.save(book8);
			bookService.save(book9);
			bookService.save(book10);
			bookService.save(book11);
			bookService.save(book12);
			bookService.save(book13);
			bookService.save(book14);
			bookService.save(book15);
			bookService.save(book16);
			bookService.save(book17);
			bookService.save(book18);
			bookService.save(book19);
			bookService.save(book20);
			bookService.save(book21);
			bookService.save(book22);
			bookService.save(book23);
			bookService.save(book24);
			bookService.save(book25);

			book10.setTheUser(user3);
			book10.setReturnDate(LocalDate.of(2021, 12, 12));

			book1.setTheUser(user3);
			book1.setReturnDate(LocalDate.of(2021, 12, 07));
			user3.setBooks(Arrays.asList(book10, book1));

			bookService.save(book1);
			bookService.save(book10);
			userService.save(user3);

			applicationRefresh.applicationRefresher();
		};

	}

}
