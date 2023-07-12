package com.mjc.school.controller;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ControllerHandler {
    private final NewsController newsController;
    private final AuthorController authorController;

    @Autowired
    public ControllerHandler(NewsController newsController, AuthorController authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    @CommandHandler( command = 1)
    public void getAllNews(){
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }

    @CommandHandler( command = 2)
    public void getNewsById(){
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        Long id = readNumber("News Id");
        System.out.println(newsController.readById(id));
    }

    @CommandHandler(command = 3)
    public void createNews(){
        System.out.println(Operations.CREATE_NEWS.getOperation());
        NewsRequestDTO dto = readNewsValues(null);
        System.out.println(newsController.create(dto));
    }

    @CommandHandler(command = 4)
    public void updateNews(){
        System.out.println(Operations.UPDATE_NEWS.getOperation());
        Long id = readNumber("News id");
        NewsRequestDTO dto = readNewsValues(id);
        System.out.println(newsController.update(dto));
    }

    @CommandHandler(command = 5)
    public void deleteNewsById(){
        Long id = readNumber("News id");
        System.out.println(newsController.deleteById(id));
    }
    @CommandHandler( command = 6)
    public void getAllAuthors(){
        System.out.println(Operations.GET_ALL_AUTHORS.getOperation());
        authorController.readAll().forEach(System.out::println);
    }

    @CommandHandler( command = 7)
    public void getAuthorById(){
        System.out.println(Operations.GET_AUTHOR_BY_ID.getOperation());
        Long id = readNumber("Author Id");
        System.out.println(authorController.readById(id));
    }

    @CommandHandler(command = 8)
    public void createAuthor(){
        System.out.println(Operations.CREATE_AUTHOR.getOperation());
        AuthorRequestDTO dto = readAuthorValues(null);
        System.out.println(authorController.create(dto));
    }

    @CommandHandler(command = 9)
    public void updateAuthor(){
        System.out.println(Operations.UPDATE_AUTHOR.getOperation());
        Long id = readNumber("Author id");
        AuthorRequestDTO dto = readAuthorValues(id);
        System.out.println(authorController.update(dto));
    }

    @CommandHandler(command = 10)
    public void deleteAuthorById(){
        System.out.println(Operations.DELETE_AUTHOR_BY_ID.getOperation());
        Long id = readNumber("Author id");
        System.out.println(authorController.deleteById(id));
    }

    private Long readNumber(String type) {
        System.out.println("Enter " + type +":");
        Scanner scr = new Scanner(System.in);
        try {
            return scr.nextLong();
        } catch (Exception e){
            throw new ValidatorException(String.format(ErrorCodes.CHECK_SHOULD_BE_NUMBER.getMessage(), type));
        }
    }
    private AuthorRequestDTO readAuthorValues(Long id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter author name: ");
        String name = sc.nextLine();
        return new AuthorRequestDTO(id, name);
    }
    private NewsRequestDTO readNewsValues(Long id){
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter news title:");
        String title = scr.nextLine();
        System.out.println("Enter news content:");
        String content = scr.nextLine();
        System.out.println("Enter author id:");
        Long authorId = readNumber("Author Id");
        return new NewsRequestDTO(id, title, content, authorId);
    }
}
