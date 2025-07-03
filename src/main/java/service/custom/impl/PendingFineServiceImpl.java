package service.custom.impl;

import com.google.inject.Inject;
import dto.PendingFine;
import entity.PendingFineEntity;
import org.modelmapper.ModelMapper;
import repository.custom.PendingFineRepository;
import service.custom.PendingFineService;
import util.Mapper;
import java.util.ArrayList;
import java.util.List;

public class PendingFineServiceImpl implements PendingFineService {

    //    PendingFineRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.PENDINGFINE);
    @Inject
    PendingFineRepository repository;
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
