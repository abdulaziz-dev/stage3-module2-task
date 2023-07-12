package com.mjc.school.repository.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.util.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class NewsDataSource {
    private final String TITLES_FILENAME = "news";
    private final String CONTENTS_FILENAME = "content";
    private final List<AuthorModel> authorsList = AuthorsDataSource.getInstance().getAuthorList();
    private final List<NewsModel> newsList;

    public NewsDataSource() {
        this.newsList = loadNews(authorsList);
    }

    private List<NewsModel> loadNews(List<AuthorModel> authorModels) {
        List<NewsModel> newsModels = new ArrayList<>();
        List<String> contents = Utils.readFile(CONTENTS_FILENAME);
        List<String> titles = Utils.readFile(TITLES_FILENAME);

        for (int i=0; i<contents.size(); i++){
            LocalDateTime randomDate = Utils.getRandomDate();
            newsModels.add(
                    new NewsModel(
                            (long) i,
                            titles.get(i),
                            contents.get(i),
                            randomDate,
                            randomDate,
                            authorModels.get(i).getId()
                    )
            );
        }
        return newsModels;
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }
}
