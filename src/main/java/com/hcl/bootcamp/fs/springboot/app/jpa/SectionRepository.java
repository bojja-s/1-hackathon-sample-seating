package com.hcl.bootcamp.fs.springboot.app.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.bootcamp.fs.springboot.app.model.Section;



public interface SectionRepository extends JpaRepository<Section, Long> {
}
