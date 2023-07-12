package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.NewsDataSource;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final NewsDataSource newsDataSource;

    @Autowired
    public NewsRepository(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return newsDataSource.getNewsList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return newsDataSource.getNewsList().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entity.setId(newsDataSource.getNewsList().size() + 1L);
        newsDataSource.getNewsList().add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        Optional<NewsModel> newsOptional = readById(entity.getId());
        if (newsOptional.isEmpty()) {
            return null;
        }
        NewsModel model = newsOptional.get();
        model.setTitle(entity.getTitle());
        model.setContent(entity.getContent());
        model.setLastUpdateDate(entity.getLastUpdateDate());
        model.setAuthorId(entity.getAuthorId());
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        return newsDataSource.getNewsList().removeIf(x -> x.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        return newsDataSource.getNewsList().stream().anyMatch(x -> x.getId().equals(id));
    }
}
