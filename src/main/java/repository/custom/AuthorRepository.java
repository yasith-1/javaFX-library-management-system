package repository.custom;

import entity.AuthorEntity;
import repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {
    String getId();

    List<AuthorEntity> getAuthorEntityList();
}
