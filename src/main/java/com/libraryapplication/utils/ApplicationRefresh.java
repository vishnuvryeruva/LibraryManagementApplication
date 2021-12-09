package com.libraryapplication.utils;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.libraryapplication.entities.Book;
import com.libraryapplication.entities.Notification;
import com.libraryapplication.entities.User;
import com.libraryapplication.services.BookService;
import com.libraryapplication.services.NotificationService;
import com.libraryapplication.services.UserService;


@Component
public class ApplicationRefresh {

	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	
		public void applicationRefresher() {
			
			for (Book book : bookService.findAll()) {
				if (book.getEndReservationDate() != null) {
					if (book.getEndReservationDate().compareTo(LocalDate.now()) == -1) {
						if (book.getReservedByUser() != null) {
							User user = book.getReservedByUser();
							book.setReservedByUser(null);
							userService.save(user);
						}
						book.setStartReservationDate(null);
						book.setEndReservationDate(null);	
						book.setReadyForPickup(false);
						bookService.save(book);
					}	
				}
			}
	
			for (Notification notif : notificationService.findAll()) {	
				if (notif.getValidUntilDate() != null) {
					if (notif.getValidUntilDate().compareTo(LocalDate.now()) == -1) {
						notificationService.deleteById(notif.getNotificationId());
					}
				}	
			}
		}
}
