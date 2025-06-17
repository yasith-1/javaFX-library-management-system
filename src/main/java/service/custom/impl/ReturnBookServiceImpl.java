package service.custom.impl;

import dto.ReturnBook;
import entity.ReturnBookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.ReturnBookRepositoryImpl;
import service.custom.ReturnBookService;
import util.RepositoryType;

import java.util.HashMap;

public class ReturnBookServiceImpl implements ReturnBookService {

    ReturnBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RETURNBOOK);
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
    public Boolean addReturnRecord(ReturnBook returnBookDto) {
        ReturnBookEntity returnBookEntity = modelMapper.map(returnBookDto, ReturnBookEntity.class);
        return repository.add(returnBookEntity);
    }

    @Override
    public Boolean deleteReturnRecord(ReturnBook returnBookDto) {
        ReturnBookEntity returnBookEntity = modelMapper.map(returnBookDto, ReturnBookEntity.class);
        return repository.deleteReturnRecord(returnBookEntity);
    }
}
