package service.custom.impl;

import repository.RepositoryFactory;
import repository.custom.impl.FineRepositoryImpl;
import service.custom.FineService;
import util.RepositoryType;
import java.util.HashMap;

public class FineServiceImpl implements FineService {

    FineRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.FINE);

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
}
