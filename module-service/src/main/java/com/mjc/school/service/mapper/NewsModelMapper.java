package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface NewsModelMapper {
    NewsResponseDTO modelToDTO(NewsModel newsModel);
    List<NewsResponseDTO> modelListToDtoList (List<NewsModel> newsList);
    @Mappings(value = { @Mapping(target = "createDate", ignore = true),
                        @Mapping(target = "lastUpdateDate", ignore = true)})
    NewsModel dtoToModel (NewsRequestDTO requestDTO);
}