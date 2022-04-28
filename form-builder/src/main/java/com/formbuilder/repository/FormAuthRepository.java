package com.formbuilder.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formbuilder.domain.FormAuth;

@Repository("formAuthRepository")
public interface FormAuthRepository extends JpaRepository<FormAuth, Integer>{
	
	List<FormAuth> findByFormSetSeq(Integer formSetSeq, Sort sort);
	
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM FormAuth WHERE authSeq = :authSeq")
    int deleteByAuthSeq(@Param("authSeq") Integer authSeq);
}
