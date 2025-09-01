package com.base.demo.repository;

import com.base.demo.model.AcaoFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AcaoFavoritaRepository extends JpaRepository<AcaoFavorita, Long> {

}