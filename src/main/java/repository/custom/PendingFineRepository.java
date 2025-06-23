package repository.custom;

import entity.PendingFineEntity;
import repository.SuperRepository;

import java.util.List;

public interface PendingFineRepository extends SuperRepository {
    List<PendingFineEntity> getPendingFineList();
}
