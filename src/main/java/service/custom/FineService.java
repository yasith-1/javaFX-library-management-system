package service.custom;

import dto.Fine;
import service.SuperService;

import java.util.HashMap;

public interface FineService extends SuperService {
    String fineId();

    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    HashMap<String, Integer> getFineStatusMap();

    Boolean addFine(Fine fine);

    Boolean updateFine(Fine fine);

    Boolean deleteFine(Fine fine);

    Fine searchFine(Fine fine);
}
