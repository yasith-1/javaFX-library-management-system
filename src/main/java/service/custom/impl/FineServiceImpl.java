package service.custom.impl;

import dto.Fine;
import entity.FineEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.FineRepositoryImpl;
import service.custom.FineService;
import util.Mapper;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FineServiceImpl implements FineService {

    FineRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.FINE);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

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
    public List<Fine> getAllFinesList() {
        ArrayList<Fine> fineArrayList = new ArrayList<>();
        List<FineEntity> fineEntitiesList = repository.allFineList();
        if (fineEntitiesList != null) {
            fineEntitiesList.forEach(fineEntity -> {
                fineArrayList.add(modelMapper.map(fineEntity, Fine.class));
            });
            return fineArrayList;
        }
        return null;
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
    public HashMap<String, String> getFineStatusMap() {
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
        FineEntity fineEntity = modelMapper.map(fine, FineEntity.class);
        FineEntity searchResult = repository.search(fineEntity);
        if (searchResult != null) {
            Fine fineDTO = modelMapper.map(searchResult, Fine.class);
            return fineDTO;
        }
        return null;
    }

    @Override
    public Double getTotalFineAmount(String memberId,String bookId){
        Double fineAmount = repository.totalFineAmount(memberId,bookId);
        if (fineAmount != null){
            return fineAmount;
        }
        return null;
    }
}
