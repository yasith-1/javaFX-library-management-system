package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.IssuedBookRepositoryImpl;
import service.custom.IssuedBookService;
import util.RepositoryType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class IssuedBookServiceImpl implements IssuedBookService {

    IssuedBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ISSUEDBOOK);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getBookSet();
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        return null;
    }

}
