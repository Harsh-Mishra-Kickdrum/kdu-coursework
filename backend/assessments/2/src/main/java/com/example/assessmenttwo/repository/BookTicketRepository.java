package com.example.assessmenttwo.repository;

import com.example.assessmenttwo.entity.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket,Long> {
}
