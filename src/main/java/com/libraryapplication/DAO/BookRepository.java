package com.libraryapplication.DAO;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryapplication.entities.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
