package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class NewsController implements BaseController<NewsRequestDTO, NewsResponseDTO, Long> {
    private final BaseService<NewsRequestDTO, NewsResponseDTO, Long> newsService;

    @Autowired
    public NewsController(BaseService<NewsRequestDTO, NewsResponseDTO, Long> newsService) {
        this.newsService = newsService;
    }


    @Override
    public List<NewsResponseDTO> readAll() {
        return newsService.readAll();
    }


    @Override
    public NewsResponseDTO readById(Long id) {
        return newsService.readById(id);
    }


    @Override
    public NewsResponseDTO create(NewsRequestDTO createRequest) {
        return newsService.create(createRequest);
    }


    @Override
    public NewsResponseDTO update(NewsRequestDTO updateRequest) {
        return newsService.update(updateRequest);
    }


    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
