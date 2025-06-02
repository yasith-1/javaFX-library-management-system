package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.BookRepositoryImpl;
import service.custom.BookService;
import util.RepositoryType;

import java.util.HashMap;

public class BookServiceImpl implements BookService {

    BookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String bookId() {
        String currentId = repository.getLastBookId(); // e.g., "B001"

        if (currentId == null || currentId.isEmpty()) {
            return "B001";
        }
        try {
            int num = Integer.parseInt(currentId.substring(1)); // Extract numeric part
            int nextNum = num + 1;
            return String.format("B%03d", nextNum); // Format as B002, B010, etc.
        } catch (NumberFormatException e) {
            // Fallback in case the ID is not formatted as expected
            return "B001";
        }
    }

    @Override
    public HashMap<String, String> getBookGerneMap() {
       return repository.getAllGernes();
    }

    @Override
    public HashMap<String, String> getAuthorMap() {
        return repository.getAllAuthors();
    }

    @Override
    public HashMap<String, String> getStatusMap() {
        return repository.getAllStatus();
    }

    @Override
    public Boolean addBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return repository.add(bookEntity);
    }


}
