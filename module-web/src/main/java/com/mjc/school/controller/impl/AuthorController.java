package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.AuthorResponseDTO;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorRequestDTO, AuthorResponseDTO, Long> {
    private final BaseService<AuthorRequestDTO, AuthorResponseDTO, Long> authorService;

    public AuthorController(BaseService<AuthorRequestDTO, AuthorResponseDTO, Long> authorService) {
        this.authorService = authorService;
    }


    @Override
    public List<AuthorResponseDTO> readAll() {
        return authorService.readAll();
    }

    @Override
    public AuthorResponseDTO readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    public AuthorResponseDTO create(AuthorRequestDTO createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    public AuthorResponseDTO update(AuthorRequestDTO updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
