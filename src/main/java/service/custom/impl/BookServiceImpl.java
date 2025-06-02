package service.custom.impl;

import repository.RepositoryFactory;
import repository.custom.impl.BookRepositoryImpl;
import service.custom.BookService;
import util.RepositoryType;

import java.util.HashMap;

public class BookServiceImpl implements BookService {

    BookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);

    @Override
    public String getNextBookId() {
        String currentId = repository.getLastBookId(); // e.g., "B001"
        if (currentId.equals("B001")) {
            return "B001";
        } else {
            int num = Integer.parseInt(currentId.substring(1)); // Extract number part
            int nextNum = num + 1;
            String nextId = String.format("B%03d", nextNum); // e.g., "B002"
            return nextId;
        }

    }

    @Override
    public HashMap<String, String> GetBookGerneMap() {
        return repository.getAllGernes();
    }

    @Override
    public HashMap<String, String> GetAuthorMap() {
        return repository.getAllAuthors();
    }

    @Override
    public HashMap<String, String> GetStatusMap() {
        return repository.getAllStatus();
    }
}
