package service.custom;

import dto.Fine;
import service.SuperService;

import java.util.HashMap;
import java.util.List;

public interface FineService extends SuperService {
    String fineId();

    List<Fine> getAllFinesList();

    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    HashMap<String, String> getFineStatusMap();

    Boolean addFine(Fine fine);

    Boolean updateFine(Fine fine);

    Boolean deleteFine(Fine fine);

    Fine searchFine(Fine fine);

    Double getTotalFineAmount(String memberId,String bookId);
}
