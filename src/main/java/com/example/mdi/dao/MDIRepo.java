package com.example.mdi.dao;

import com.example.mdi.entity.MDIEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MDIRepo extends JpaRepository<MDIEntity, Long> {
}
