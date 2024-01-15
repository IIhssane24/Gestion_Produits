package com.sid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sid.entity.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	@Query("select p from Produit p where p.des like :x")
	public Page<Produit> chercher(@Param("x") String mc,Pageable pageable);
	
	public Page<Produit> findByDesContains(String mc,Pageable pageable);

}
