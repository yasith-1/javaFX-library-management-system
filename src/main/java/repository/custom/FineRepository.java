package repository.custom;

import entity.FineEntity;
import repository.CrudRepository;
import java.util.HashMap;
import java.util.List;

public interface FineRepository extends CrudRepository<FineEntity, String> {

    HashMap<String, String> getBookSet();

    HashMap<String, String> getMemberSet();

    HashMap<String, String> getFineStatusSet();

    List<FineEntity> allFineList();

    String getLastFineId();

    Double totalFineAmount(String memberId,String bookId);
}
