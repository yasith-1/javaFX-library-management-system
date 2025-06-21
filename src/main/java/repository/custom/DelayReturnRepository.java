package repository.custom;

import entity.DelayReturnEntity;
import repository.SuperRepository;

import java.util.HashMap;
import java.util.List;

public interface DelayReturnRepository extends SuperRepository {
    HashMap<String, String> getMemberSet();

    List<DelayReturnEntity> delayReturnedMembersList();

    List<String> delayReturnedMembersNameList();

    List<DelayReturnEntity> delayReturnedOverviewList(String memberId);
}
