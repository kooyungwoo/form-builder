package com.formbuilder.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formbuilder.domain.FormItem;

@Repository("formItemRepository")
public interface FormItemRepository extends JpaRepository<FormItem, Integer>{
	
	List<FormItem> findByFormSetSeq(Integer formSetSeq, Sort sort);
	
	List<FormItem> findByFormSetSeqAndFormItemList(Integer formSetSeq, String formItemList, Sort sort);
	
	@Transactional
	@Modifying
    @Query(value = "UPDATE FormItem SET formItemList='N', formItemListOrder=0 WHERE formSetSeq = :formSetSeq")
    int updateByFormSetSeq(@Param("formSetSeq") Integer formSetSeq);
}
