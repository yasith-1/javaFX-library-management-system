package service.custom.impl;

import com.google.inject.Inject;
import dto.DelayReturn;
import entity.DelayReturnEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.DelayReturnRepository;
import repository.custom.impl.DelayReturnRepositoryImpl;
import service.custom.DelayReturnService;
import util.Mapper;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DelayReturnServiceImpl implements DelayReturnService {

//    DelayReturnRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.DELAYEDRETURN);
    @Inject
    DelayReturnRepository repository;
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

    @Override
    public HashMap<String, String> getMemberMap() {
        return repository.getMemberSet();
    }

    @Override
    public List<DelayReturn> getDelayReturnMembersList() {
        ArrayList<DelayReturn> delayReturnMemberList = new ArrayList<>();
        List<DelayReturnEntity> delayReturnMemberEntities = repository.delayReturnedMembersList();
        if (delayReturnMemberEntities != null) {
            delayReturnMemberEntities.forEach(delayReturnEntity -> {
                delayReturnMemberList.add(modelMapper.map(delayReturnEntity, DelayReturn.class));
            });
            return delayReturnMemberList;
        }
        return null;
    }

    @Override
    public List<String> delayReturnedMembersNameList() {
        List<String> delayReturnedMembersNameList = repository.delayReturnedMembersNameList();
        if (delayReturnedMembersNameList != null) {
            return delayReturnedMembersNameList;
        }
        return null;
    }

    @Override
    public List<DelayReturn> delayReturnedOverviewList(String memberId) {
        ArrayList<DelayReturn> delayReturnOverViewList = new ArrayList<>();
        List<DelayReturnEntity> delayReturnOverviewEntities = repository.delayReturnedOverviewList(memberId);
        if (delayReturnOverviewEntities != null) {
            delayReturnOverviewEntities.forEach(delayReturnEntity -> {
                delayReturnOverViewList.add(modelMapper.map(delayReturnEntity, DelayReturn.class));
            });
            return delayReturnOverViewList;
        }
        return null;
    }
}
