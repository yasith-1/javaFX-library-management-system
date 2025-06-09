package service.custom.impl;

import dto.IssuedBook;
import entity.IssuedBookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.IssuedBookRepositoryImpl;
import service.custom.IssuedBookService;
import util.RepositoryType;
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
        return repository.getMemberSet();
    }

    @Override
    public Boolean addIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.add(issuedBookEntity);
    }

    @Override
    public Boolean deductBookQty(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.deductbookQuantity(issuedBookEntity);
    }

}
