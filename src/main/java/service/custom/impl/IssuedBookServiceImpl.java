package service.custom.impl;

import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.IssuedBookRepositoryImpl;
import service.custom.IssuedBookService;
import util.RepositoryType;
import java.util.HashMap;

public class IssuedBookServiceImpl implements IssuedBookService {

    IssuedBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ISSUEDBOOK);

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getBookSet();
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        return repository.getMemberSet();
    }

}
