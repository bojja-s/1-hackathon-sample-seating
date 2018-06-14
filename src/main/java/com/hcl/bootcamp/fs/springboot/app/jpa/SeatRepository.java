package com.hcl.bootcamp.fs.springboot.app.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.bootcamp.fs.springboot.app.model.Seat;



public interface SeatRepository extends JpaRepository<Seat, Long> {
}