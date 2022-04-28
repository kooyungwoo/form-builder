package com.formbuilder.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formbuilder.domain.FormField;

@Repository("formFieldRepository")
public interface FormFieldRepository extends JpaRepository<FormField, Integer>{
	
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM FormField WHERE formItemSeq = :formItemSeq")
    int deleteByFormItemSeq(@Param("formItemSeq") Integer formItemSeq);
}
