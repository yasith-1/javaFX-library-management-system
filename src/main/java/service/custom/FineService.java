package service.custom;

import service.SuperService;

import java.util.HashMap;

public interface FineService extends SuperService {
    HashMap<String, String> getBookMap();
    HashMap<String, String> getMemberMap();
    HashMap<String, Integer> getFineStatusMap();
}
