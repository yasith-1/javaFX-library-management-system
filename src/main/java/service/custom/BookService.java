package service.custom;

import service.SuperService;

import java.util.HashMap;

public interface BookService extends SuperService {
     String getNextBookId();
     HashMap<String,String> getBookGerneMap();
     public HashMap<String, String> getAuthorMap();
     public HashMap<String, String> getStatusMap();
}
