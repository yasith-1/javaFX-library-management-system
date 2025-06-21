package service.custom;

import dto.DelayReturn;
import service.SuperService;

import java.util.HashMap;
import java.util.List;

public interface DelayReturnService extends SuperService {
    HashMap<String, String> getMemberMap();

    List<DelayReturn> getDelayReturnMembersList();

    List<String> delayReturnedMembersNameList();

    List<DelayReturn> delayReturnedOverviewList(String memberId);
}
