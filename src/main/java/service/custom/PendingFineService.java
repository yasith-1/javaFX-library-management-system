package service.custom;

import dto.PendingFine;
import service.SuperService;
import java.util.List;

public interface PendingFineService extends SuperService {
    List<PendingFine> getPendingFineList();
}
