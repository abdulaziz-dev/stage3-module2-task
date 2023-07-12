package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsModelMapper;
import com.mjc.school.service.validator.NewsValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService implements BaseService<NewsRequestDTO, NewsResponseDTO, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final NewsModelMapper newsMapper = Mappers.getMapper(NewsModelMapper.class);
    private final NewsValidator validator;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository, NewsValidator validator){
        this.newsRepository = newsRepository;
        this.validator = validator;
    }

    @Override
    public List<NewsResponseDTO> readAll() {
        return newsMapper.modelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsResponseDTO readById(Long id) {
        checkNewsExist(id);
        NewsModel model = newsRepository.readById(id).get();
        return newsMapper.modelToDTO(model);
    }

    @Override
    public NewsResponseDTO create(NewsRequestDTO createRequest) {
        validator.checkNewsDto(createRequest);
        NewsModel model = newsMapper.dtoToModel(createRequest);
        LocalDateTime currentTime = LocalDateTime.now().withNano(0);
        model.setCreateDate(currentTime);
        model.setLastUpdateDate(currentTime);
        NewsModel newModel = newsRepository.create(model);
        return newsMapper.modelToDTO(newModel);
    }

    @Override
    public NewsResponseDTO update(NewsRequestDTO updateRequest) {
        validator.checkNewsDto(updateRequest);
        NewsModel model = newsMapper.dtoToModel(updateRequest);
        model.setLastUpdateDate(LocalDateTime.now().withNano(0));
        NewsModel updatedModel = newsRepository.update(model);

        return newsMapper.modelToDTO(updatedModel);
    }

    @Override
    public boolean deleteById(Long id) {
        checkNewsExist(id);
        return newsRepository.deleteById(id);
    }

    private void checkNewsExist(Long id){
        if (!newsRepository.existById(id)){
            throw new NotFoundException(String.format(ErrorCodes.NEWS_NOT_EXIST.getMessage(),id));
        }
    }
}
