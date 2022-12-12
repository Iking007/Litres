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

/**
 * @author Pavel
 */
@Controller
public class SecretController {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://db:5432/lab","postgres",
            "rootuser");
    Statement statement = connection.createStatement();
    private static String secretURL;

    public SecretController() throws SQLException {
    }

    @GetMapping("/menu/{secret}")
    private String secretMenu(@PathVariable(value = "secret") String secret, Model model){
        if(Objects.equals(secret, secretURL)) {
            model.addAttribute("namePage", "menuSuperUser");
            model.addAttribute("secret", secret);
        }
        else {
            model.addAttribute("namePage", "404");
            model.addAttribute("secret", secret);
        }
        return "menu";
    }
    @GetMapping("/menu/{secret}/all")
    private String all(@PathVariable(value = "secret") String secret, Model model) throws SQLException {
        if(Objects.equals(secret, secretURL)) {
            model.addAttribute("namePage", "secretAll");
            model.addAttribute("secret", secret);
            ResultSet resultSet = statement.executeQuery("select * from public.book");
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
        }
        else {
            model.addAttribute("namePage", "404");
            model.addAttribute("secret", secret);
        }

        return "secretAll";
    }

    @GetMapping("/menu/{secret}/del/{id}")
    private String delBook(@PathVariable(value = "secret") String secret, @PathVariable(value = "id") int id) throws SQLException{
        if(Objects.equals(secret, secretURL)) {
            statement.execute("delete from public.book where book_id='"+id+"'");
        }

        return "redirect:/menu/"+secret;
    }

    @GetMapping("/menu/{secret}/update/{id}")
    private String updateBook(@PathVariable(value = "secret") String secret, @PathVariable(value = "id") int id, Model model) throws SQLException {
        if(Objects.equals(secret, secretURL)) {
            model.addAttribute("namePage", "update");
            model.addAttribute("secret", secret);
            ResultSet resultSet = statement.executeQuery("select * from public.book where book_id='"+id+"'");
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
        }
        else {
            model.addAttribute("namePage", "404");
            model.addAttribute("secret", secret);
        }
        return "update";
    }

    @PostMapping("/menu/{secret}/update/{id}/up")
    private String upBook(@PathVariable(value = "secret") String secret, @PathVariable(value = "id") int id,
                          @RequestParam String title, @RequestParam String img,
                          @RequestParam String download, @RequestParam String writer,
                          @RequestParam String str) throws SQLException{
        if(Objects.equals(secret, secretURL)) {
            if(img == ""){
                img = "NULL";
            }
            if(download == ""){
                download = "NULL";
            }
            statement.execute("update book set name='"+title+"', image='"+img+"', file='"+
                    download+"', writer='"+writer+"', description='"+str+"' where book_id='"+id+"'");
        }

        return "redirect:/menu/"+secret;
    }

    @GetMapping("/menu/{secret}/form")
    public String secret(@PathVariable(value = "secret") String secret, Model model){
        if (!Objects.equals(secret, secretURL)){
            model.addAttribute("namePage", "404");
            model.addAttribute("secret", secret);
        }
        else {
            model.addAttribute("namePage", "Форма");
        }
        return "new";
    }
    @PostMapping("/menu/{secret}/form/new")
    public String newBook(@PathVariable(value = "secret") String secret, @RequestParam String title, @RequestParam String img,
                          @RequestParam String download, @RequestParam String writer, @RequestParam String str) throws SQLException {
        if(Objects.equals(secret, secretURL)) {
            if(img == ""){
                img = "NULL";
            }
            if(download == ""){
                download = "NULL";
            }
            statement.execute("insert into book (name, image, file, writer, description) values ('" +
                    title + "','" + img + "','" + download + "','"+ writer +"','" + str + "')");
        }
        return "redirect:/menu/"+secret;
    }

    public static void newSecret(){
        double n = Math.random() * 90 + 10;
        char c;
        secretURL = "";
        for (int i = 0; i < n; i++) {
            c = (char) (Math.random() * 26 + 'a');
            secretURL += c;
        }
        System.out.println("URL для добавления данных: 'хост'/menu/" + secretURL);
    }
}
