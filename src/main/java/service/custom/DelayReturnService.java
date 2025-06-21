package service.custom;

import dto.DelayReturn;
import service.SuperService;

import java.util.List;

public interface DelayReturnService extends SuperService {
    List<DelayReturn> getDelayReturnMembersList();
}
