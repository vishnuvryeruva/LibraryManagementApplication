package com.libraryapplication.DAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryapplication.entities.Notification;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
