package service.custom.impl;

import dto.Author;
import dto.Category;
import entity.AuthorEntity;
import entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.AuthorRepositoryImpl;
import repository.custom.impl.CategoryRepositoryImpl;
import service.custom.AuthorService;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    AuthorRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.AUTHOR);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String getAuthoryId() {
        String currentId = repository.getId();
        if (currentId != null) {
            return String.format("A%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "A001";
        }
    }

    @Override
    public List<Author> getAuthorList() {
        List<AuthorEntity> authorEntities = repository.getAuthorEntityList();
        ArrayList<Author> authorArrayList = new ArrayList<>();

        for (AuthorEntity authorEntity : authorEntities) {
            authorArrayList.add(modelMapper.map(authorEntity, Author.class));
        }
        return authorArrayList;
    }

    @Override
    public Boolean addAuthor(Author author) {
        AuthorEntity authorEntity = modelMapper.map(author, AuthorEntity.class);
        return repository.add(authorEntity);
    }

    @Override
    public Boolean updateAuthor(Author author) {
        AuthorEntity authorEntity = modelMapper.map(author, AuthorEntity.class);
        return repository.update(authorEntity);
    }

    @Override
    public Boolean deleteAuthor(Author author) {
        AuthorEntity authorEntity = modelMapper.map(author, AuthorEntity.class);
        return repository.delete(authorEntity);
    }
}
