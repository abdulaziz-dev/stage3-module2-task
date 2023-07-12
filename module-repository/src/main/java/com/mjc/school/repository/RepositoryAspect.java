package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryAspect {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public RepositoryAspect(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Around("@annotation(com.mjc.school.repository.OnDelete) && args(authorId)")
    public boolean deleteNewsByAuthor(ProceedingJoinPoint joinPoint, Long authorId) throws Throwable {
        if (!((boolean) joinPoint.proceed())){
            return false;
        }
        newsRepository.readAll().removeIf(x -> x.getAuthorId().equals(authorId));
        return true;
    }
}
