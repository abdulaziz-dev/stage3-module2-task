package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class NewsValidator {

    private static final int TITLE_MAX_LENGTH = 30;
    private static final int TITLE_MIN_LENGTH = 5;
    private static final int CONTENT_MIN_LENGTH = 5;
    private static final int CONTENT_MAX_LENGTH = 255;
    private static final int AUTHOR_NAME_MIN_LENGTH = 3;
    private static final int AUTHOR_NAME_MAX_LENGTH = 15;
//    private static final int MAX_AUTHOR_ID = 20;

    public void checkNewsDto(NewsRequestDTO dto){

        long authorId = dto.authorId();
        if (stringLengthInvalid(dto.title(), TITLE_MIN_LENGTH, TITLE_MAX_LENGTH)){
            throw new ValidatorException(String.format(ErrorCodes.CHECK_TITLE_LENGTH.getMessage(),TITLE_MIN_LENGTH,TITLE_MAX_LENGTH,dto.title()));
        }
        if (stringLengthInvalid(dto.content(), CONTENT_MIN_LENGTH, CONTENT_MAX_LENGTH)){
            throw new ValidatorException(String.format(ErrorCodes.CHECK_CONTENT_LENGTH.getMessage(), CONTENT_MIN_LENGTH, CONTENT_MAX_LENGTH, dto.content()));
        }
        if (authorId < 0){
            throw new NotFoundException(String.format(ErrorCodes.AUTHOR_NOT_EXIST.getMessage(),authorId));
        }
    }

    public void checkAuthorDto(AuthorRequestDTO dto){
        if (stringLengthInvalid(dto.name(), AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH)){
            throw new ValidatorException(String.format(ErrorCodes.CHECK_AUTHOR_NAME_LENGTH.getMessage(), AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH, dto.name()));
        }

    }

    private boolean stringLengthInvalid(String text, int minLength, int maxLength){
        return (text.length() < minLength) || (text.length() > maxLength);
    }
}
