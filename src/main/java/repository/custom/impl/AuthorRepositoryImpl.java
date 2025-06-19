package repository.custom.impl;

import entity.AuthorEntity;
import repository.custom.AuthorRepository;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public String getId() {
        return "";
    }

    @Override
    public List<AuthorEntity> getAuthorEntityList() {
        return List.of();
    }

    @Override
    public Boolean add(AuthorEntity entity) {
        return null;
    }

    @Override
    public Boolean update(AuthorEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(AuthorEntity entity) {
        return null;
    }

    @Override
    public AuthorEntity search(AuthorEntity entity) {
        return null;
    }
}
