package com.javasetup.springboot.repository.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javasetup.springboot.entity.db1.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
