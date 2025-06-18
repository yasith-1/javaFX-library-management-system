package repository.custom;

import entity.FineEntity;
import repository.CrudRepository;
import java.util.HashMap;

public interface FineRepository extends CrudRepository<FineEntity, String> {

    HashMap<String, String> getBookSet();

    HashMap<String, String> getMemberSet();

    HashMap<String, Integer> getFineStatusSet();

    String getLastFineId();
}
