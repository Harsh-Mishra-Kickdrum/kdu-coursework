package com.example.assessmenttwo.repository;

import com.example.assessmenttwo.entity.CancelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelTicketRepository extends JpaRepository<CancelTicket,Long> {
}
