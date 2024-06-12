package com.gojap.moviechu.manager.repository;

import com.gojap.moviechu.manager.entity.MemberMovieManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMovieManagerRepository extends JpaRepository<MemberMovieManager, Long> {
    List<MemberMovieManager> findAllByMovieId(Long movieId);
}
