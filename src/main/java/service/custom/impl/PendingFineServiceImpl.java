package service.custom.impl;

import dto.PendingFine;
import entity.PendingFineEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.PendingFineRepositoryImpl;
import service.custom.PendingFineService;
import util.Mapper;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.List;

public class PendingFineServiceImpl implements PendingFineService {

    PendingFineRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.PENDINGFINE);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

    @Override
    public List<PendingFine> getPendingFineList() {
        ArrayList<PendingFine> pendingFineArrayList = new ArrayList<>();
        List<PendingFineEntity> pendingFineList = repository.getPendingFineList();
        if (pendingFineList != null) {
            pendingFineList.forEach(pendingFineEntity -> {
                pendingFineArrayList.add(modelMapper.map(pendingFineEntity, PendingFine.class));
            });
            return pendingFineArrayList;
        }
        return null;
    }
}
