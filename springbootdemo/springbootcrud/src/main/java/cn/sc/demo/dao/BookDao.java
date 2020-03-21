package cn.sc.demo.dao;

import cn.sc.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> ,JpaSpecificationExecutor<Book>{

    @Query("select b from Book b where b.bookName like %?1%")
    List<Book> getBookByName(String bookName);

    @Query(value = "select * from db_book order by RAND() limit ?1", nativeQuery = true)
    List<Book> getBookRadom(Integer n);
}