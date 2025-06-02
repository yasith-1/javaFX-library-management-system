package service.custom;

import service.SuperService;

import java.util.HashMap;

public interface BookService extends SuperService {
     String getNextBookId();
     HashMap<String,String> GetBookGerneMap();
     public HashMap<String, String> GetAuthorMap();
     public HashMap<String, String> GetStatusMap();
}
