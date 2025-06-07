package service.custom;

import service.SuperService;

import java.util.HashMap;

public interface IssuedBookService extends SuperService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();
}
