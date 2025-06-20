package service.custom.impl;

import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.MemberRepositoryImpl;
import service.SuperService;
import util.Mapper;
import util.RepositoryType;

public class MemberServiceImpl implements SuperService {

    MemberRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.MEMBER);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();
}
