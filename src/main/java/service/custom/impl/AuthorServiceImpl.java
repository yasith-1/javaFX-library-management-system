package service.custom.impl;

import dto.Author;
import dto.Category;
import service.custom.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    @Override
    public String getCategoryId() {
        return "";
    }

    @Override
    public Boolean addAuthor(Author author) {
        return null;
    }

    @Override
    public Boolean updateAuthor(Author author) {
        return null;
    }

    @Override
    public Boolean deleteAuthor(Author author) {
        return null;
    }

    @Override
    public List<Category> getAuthorList() {
        return List.of();
    }
}
