package service.custom.impl;

import dto.Fine;
import entity.FineEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.FineRepositoryImpl;
import service.custom.FineService;
import util.RepositoryType;
import java.util.HashMap;

public class FineServiceImpl implements FineService {

    FineRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.FINE);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String fineId() {
        String currentId = repository.getLastFineId();
        if (currentId != null) {
            return String.format("F%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "F001";
        }
    }

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getBookSet();
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        return repository.getMemberSet();
    }

    @Override
    public HashMap<String, Integer> getFineStatusMap() {
        return repository.getFineStatusSet();
    }

    @Override
    public Boolean addFine(Fine fine) {
        FineEntity fineEntity = modelMapper.map(fine, FineEntity.class);
        return repository.add(fineEntity);
    }

    @Override
    public Boolean updateFine(Fine fine) {
        FineEntity fineEntity = modelMapper.map(fine, FineEntity.class);
        return repository.update(fineEntity);
    }

    @Override
    public Boolean deleteFine(Fine fine) {
        FineEntity fineEntity = modelMapper.map(fine, FineEntity.class);
        return repository.delete(fineEntity);
    }

    @Override
    public Fine searchFine(Fine fine) {
        return null;
    }
}
