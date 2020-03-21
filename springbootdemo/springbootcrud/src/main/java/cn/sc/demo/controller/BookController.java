package cn.sc.demo.controller;

import cn.sc.demo.dao.BookDao;
import cn.sc.demo.entity.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookDao bookDao;

    /**
     * 查询图书
     * @return
     */
    @RequestMapping("/list2")
    public ModelAndView list2(Book book,Integer lowPrice,Integer highPrice){
        ModelAndView mv = new ModelAndView();
        List<Book> bookList = bookDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (book != null) {
                if (book.getBookName() != null && !"".equals(book.getBookName())) {
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("bookName"), "%" + book.getBookName() + "%"));
                }
            }

                if (lowPrice != null && highPrice != null && !"".equals(lowPrice) && !"".equals(highPrice)) {
                    if (lowPrice <= highPrice) {
                        predicate.getExpressions().add(criteriaBuilder.between(root.get("price"), lowPrice, highPrice));
                    }
                }
            return predicate;
        });
        mv.addObject("bookList",bookList);
        mv.setViewName("bookList");
        return mv;
    }
    /**
       * 查询图书
     * @return
             */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bookList",bookDao.findAll());
        mv.setViewName("bookList");
        return mv;
    }

    /**
     * 添加图书
     * @param book
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Book book){
      bookDao.save(book);
        return "forward:/book/list";
    }


    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("book",bookDao.getOne(id));
        mv.setViewName("bookList");
        return mv;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Book book){
        bookDao.save(book);
        return "forward:/book/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(value="id" ,required = false) Integer id){
        bookDao.delete(id);
        return "forward:/book/list";
    }

    @ResponseBody
    @RequestMapping("/getBookByName")
    public List<Book>  getBookByName(@RequestParam(value = "name",required = false) String name){
        System.out.println("name = " + name);
        return bookDao.getBookByName(name);
    }

    @ResponseBody
    @RequestMapping("/getBookRadom")
    public List<Book>  getBookRadom(@RequestParam(value = "n",required = false) Integer n){
        System.out.println("n = " + n);
        return bookDao.getBookRadom(n);
    }
}
