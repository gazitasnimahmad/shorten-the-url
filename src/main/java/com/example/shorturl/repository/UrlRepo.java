package com.example.shorturl.repository;

import com.example.shorturl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UrlRepo extends JpaRepository<Url, Long> {
}

