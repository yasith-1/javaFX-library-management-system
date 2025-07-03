package service.custom.impl;

import com.google.inject.Inject;
import dto.IssuedBook;
import entity.IssuedBookEntity;
import org.modelmapper.ModelMapper;
import repository.custom.IssuedBookRepository;
import service.custom.IssuedBookService;
import util.Mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IssuedBookServiceImpl implements IssuedBookService {
    //    IssuedBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ISSUEDBOOK);
    @Inject
    IssuedBookRepository repository;

    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getBookSet();
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        return repository.getMemberSet();
    }

    @Override
    public HashMap<String, Integer> getBookCountMap() {
        return repository.getBookQuantityMap();
    }

    @Override
    public Boolean addIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.add(issuedBookEntity);
    }

    @Override
    public Boolean updateIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.update(issuedBookEntity);
    }

    @Override
    public Boolean deleteIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.delete(issuedBookEntity);
    }

    @Override
    public IssuedBook searchIssuedBook(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        IssuedBookEntity foundIssuedBookEntity = repository.search(issuedBookEntity);
        if (foundIssuedBookEntity != null) {
            IssuedBook issuedBookResult = modelMapper.map(issuedBookEntity, IssuedBook.class);
            return issuedBookResult;
        }
        return null;
    }

    @Override
    public List<IssuedBook> getIssuedBookList() {
        List<IssuedBook> issuedBooksList = new ArrayList<>();
        List<IssuedBookEntity> issuedBookEntityList = repository.issuedBookList();

        if (issuedBookEntityList != null) {
            for (IssuedBookEntity issuedBookEntity : issuedBookEntityList) {
                issuedBooksList.add(modelMapper.map(issuedBookEntity, IssuedBook.class));
            }
            return issuedBooksList;
        }
        return null;
    }

}
