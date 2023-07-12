package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.OnDelete;
import com.mjc.school.repository.data.AuthorsDataSource;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final AuthorsDataSource authorsDataSource;

    @Autowired
    public AuthorRepository(AuthorsDataSource authorsDataSource) {
        this.authorsDataSource = authorsDataSource;
    }


    @Override
    public List<AuthorModel> readAll() {
        return authorsDataSource.getAuthorList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return authorsDataSource.getAuthorList().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        long maxId = authorsDataSource.getAuthorList().stream().mapToLong(AuthorModel::getId).max().orElse(0);
        entity.setId(maxId + 1);
        authorsDataSource.getAuthorList().add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        Optional<AuthorModel> authorOptional = readById(entity.getId());
        if (authorOptional.isEmpty()) {
            return null;
        }
        AuthorModel authorModel = authorOptional.get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(entity.getLastUpdateDate());
        return authorModel;
    }


    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        return authorsDataSource.getAuthorList().removeIf(x -> x.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        return authorsDataSource.getAuthorList().stream().anyMatch(x -> x.getId().equals(id));
    }
}
