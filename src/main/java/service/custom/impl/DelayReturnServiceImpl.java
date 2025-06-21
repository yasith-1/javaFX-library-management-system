package service.custom.impl;

import dto.DelayReturn;
import entity.DelayReturnEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.DelayReturnRepositoryImpl;
import service.custom.DelayReturnService;
import util.Mapper;
import util.RepositoryType;
import java.util.ArrayList;
import java.util.List;

public class DelayReturnServiceImpl implements DelayReturnService {

    DelayReturnRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.DELAYEDRETURN);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

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
}
