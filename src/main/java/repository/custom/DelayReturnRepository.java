package repository.custom;

import entity.DelayReturnEntity;
import repository.SuperRepository;
import java.util.List;

public interface DelayReturnRepository extends SuperRepository {
    List<DelayReturnEntity> delayReturnedMembersList();
    List<DelayReturnEntity> delayReturnedOverviewList();
}
