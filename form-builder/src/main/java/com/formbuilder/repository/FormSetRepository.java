package com.formbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formbuilder.domain.FormSet;

@Repository("formSetRepository")
public interface FormSetRepository extends JpaRepository<FormSet, Integer>{
}
