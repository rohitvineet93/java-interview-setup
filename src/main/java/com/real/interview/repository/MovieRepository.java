package com.real.interview.repository;

import com.real.interview.model.dao.MovieDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieDao, Long> {
    List<MovieDao> findByTitleContainingIgnoreCase(String title);
    List<MovieDao> findByReleaseYear(Integer releaseYear);
}
