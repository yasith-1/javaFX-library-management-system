package service.custom.impl;

import com.google.inject.Inject;
import dto.ReturnBook;
import entity.ReturnBookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.ReturnBookRepository;
import repository.custom.impl.ReturnBookRepositoryImpl;
import service.custom.ReturnBookService;
import util.Mapper;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnBookServiceImpl implements ReturnBookService {

//    ReturnBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RETURNBOOK);
    @Inject
    ReturnBookRepository repository;
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
    public Boolean addReturnRecord(ReturnBook returnBookDto) {
        ReturnBookEntity returnBookEntity = modelMapper.map(returnBookDto, ReturnBookEntity.class);
        return repository.add(returnBookEntity);
    }

    @Override
    public Boolean deleteReturnRecord(ReturnBook returnBookDto) {
        ReturnBookEntity returnBookEntity = modelMapper.map(returnBookDto, ReturnBookEntity.class);
        return repository.delete(returnBookEntity);
    }

    @Override
    public ReturnBook searchReturnRecord(ReturnBook returnBookDto) {
        ReturnBookEntity returnBookEntity = modelMapper.map(returnBookDto, ReturnBookEntity.class);
        ReturnBookEntity foundReturnBookEntity = repository.search(returnBookEntity);
        if (foundReturnBookEntity != null) {
            return modelMapper.map(foundReturnBookEntity, ReturnBook.class);
        }
        return null;
    }

    @Override
    public List<ReturnBook> getAllReturnBookList() {
        List<ReturnBook> returnBookList = new ArrayList<>();
        List<ReturnBookEntity> returnBookEntities = repository.returnBookList();

        if (returnBookEntities != null) {
            returnBookEntities.forEach(entity -> {
                returnBookList.add(modelMapper.map(entity, ReturnBook.class));
            });
            return returnBookList;
        }
        return null;
    }
}
