package com.domain.congregentur.book;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    @Query(value = "SELECT * FROM books WHERE (title || ' ' || author || ' ' || isbn) ILIKE '%' || ? || '%'", nativeQuery = true)
    public List<Book> findByTitleOrAuthorOrIsbn(String keyword);

}
