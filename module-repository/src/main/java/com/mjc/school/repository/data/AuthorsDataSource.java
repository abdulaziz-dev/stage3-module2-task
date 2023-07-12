package com.mjc.school.repository.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.util.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorsDataSource {
    private static final AuthorsDataSource INSTANCE = new AuthorsDataSource();
    private final String AUTHORS_FILENAME = "authors";
    private final List<AuthorModel> authorList;


    public AuthorsDataSource() {
        this.authorList = loadAuthors();
    }

    private List<AuthorModel> loadAuthors() {
        List<AuthorModel> authorModels = new ArrayList<>();
        List<String> authors = Utils.readFile(AUTHORS_FILENAME);
        for (int i=0; i<authors.size(); i++){
            LocalDateTime randomDate = Utils.getRandomDate();
            authorModels.add(new AuthorModel(
                    (long) i,
                    authors.get(i),
                    randomDate,
                    randomDate
            ));
        }

        return authorModels;
    }

    public static AuthorsDataSource getInstance() {
        return INSTANCE;
    }

    public List<AuthorModel> getAuthorList() {
        return authorList;
    }
}
