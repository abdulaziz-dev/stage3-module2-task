package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public record NewsResponseDTO(Long id, String title, String content,
                              LocalDateTime createDate, LocalDateTime lastUpdateDate,
                              Long authorId) {
}