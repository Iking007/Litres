package com.example.Litres.Controller;

import com.example.Litres.Model.Books;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


/**
 * @author Pavel
 */
@Controller
public class IndexController{
    Connection connection = DriverManager.getConnection("jdbc:postgresql://db:5432/lab","postgres",
            "rootuser");
    Statement statement = connection.createStatement();


    public IndexController() throws SQLException {
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("namePage", "Главная");
        return "index";
    }
    @GetMapping("/books")
    public String books(Model model) throws SQLException {
        model.addAttribute("namePage", "Книги");
        ResultSet resultSet = statement.executeQuery("select * from book");
        ArrayList<Books> books = new ArrayList<>();
        while (resultSet.next()){
            Books book = new Books();
            book.setTitle(resultSet.getString("name"));
            book.setImg(resultSet.getString("image"));
            book.setId(resultSet.getLong("book_id"));
            book.setWriter(resultSet.getString("writer"));
            book.setDownload(resultSet.getString("file"));
            book.setStr(resultSet.getString("description"));
            books.add(book);

        }
        model.addAttribute("books", books);
        return "index";
    }
    @PostMapping("/books")
    public String books(@RequestParam String filter, Model model) throws SQLException {
        model.addAttribute("namePage", "Книги");

        ResultSet resultSet = statement.executeQuery("select * from book WHERE UPPER(name) LIKE UPPER('%"+filter+"%')");

        if (resultSet.isFirst()) {
            return "redirect:/books";
        }
        ArrayList<Books> books = new ArrayList<>();
        while (resultSet.next()){
            Books book = new Books();
            book.setTitle(resultSet.getString("name"));
            book.setImg(resultSet.getString("image"));
            book.setId(resultSet.getLong("book_id"));
            book.setWriter(resultSet.getString("writer"));
            book.setDownload(resultSet.getString("file"));
            book.setStr(resultSet.getString("description"));
            books.add(book);
        }
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books/{id}")
    public String bookId(@PathVariable(value = "id") long id, Model model) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from book where book_id='"+ id +"'");
        if (!resultSet.isBeforeFirst()){
            model.addAttribute("namePage", "404");
            return "index";
        }
        model.addAttribute("namePage", "Книга");
        ArrayList<Books> books = new ArrayList<>();
        Books book = new Books();
        while (resultSet.next()){
            book.setTitle(resultSet.getString("name"));
            book.setImg(resultSet.getString("image"));
            book.setId(resultSet.getLong("book_id"));
            book.setWriter(resultSet.getString("writer"));
            book.setDownload(resultSet.getString("file"));
            book.setStr(resultSet.getString("description"));
            books.add(book);
        }
        model.addAttribute("book", books);
        return "book";
    }
}
