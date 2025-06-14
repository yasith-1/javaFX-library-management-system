package service.custom.impl;

import repository.RepositoryFactory;
import repository.custom.ReturnBookRepository;
import repository.custom.impl.IssuedBookRepositoryImpl;
import util.RepositoryType;

import java.util.HashMap;

public class ReturnBookServiceImpl implements ReturnBookRepository {

    IssuedBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ISSUEDBOOK);

    @Override
    public HashMap<String, String> getMemberSet() {
        return repository.getMemberSet();
    }

    @Override
    public HashMap<String, String> getBookSet() {
        return repository.getBookSet();
    }

    @Override
    public Boolean add(Object entity) {
        return null;
    }

    @Override
    public Boolean update(Object entity) {
        return null;
    }

    @Override
    public Boolean delete(Object o) {
        return null;
    }

    @Override
    public Object search(Object o) {
        return null;
    }
}
