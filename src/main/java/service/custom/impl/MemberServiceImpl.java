package service.custom.impl;

import com.google.inject.Inject;
import dto.Member;
import entity.MemberEntity;
import org.modelmapper.ModelMapper;
import repository.custom.MemberRepository;
import service.custom.MemberService;
import util.Mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    //    MemberRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.MEMBER);
    @Inject
    MemberRepository repository;

    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();

    @Override
    public String getMemberId() {
        String currentId = repository.getId();
        if (currentId != null) {
            return String.format("M%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "M001";
        }
    }

    @Override
    public List<Member> getMembersList() {
        ArrayList<Member> memberArrayList = new ArrayList<>();
        List<MemberEntity> membersList = repository.getMembersList();
        if (membersList != null) {
            membersList.forEach(memberEntity -> {
                memberArrayList.add(modelMapper.map(memberEntity, Member.class));
            });
            return memberArrayList;
        }
        return null;
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        HashMap<String, String> memberTypeSet = repository.getMemberTypeSet();
        if (memberTypeSet != null) {
            return memberTypeSet;
        }
        return null;
    }

    @Override
    public Boolean addMember(Member member) {
        MemberEntity memberEntity = modelMapper.map(member, MemberEntity.class);
        return repository.add(memberEntity);
    }

    @Override
    public Boolean updateMember(Member member) {
        MemberEntity memberEntity = modelMapper.map(member, MemberEntity.class);
        return repository.update(memberEntity);
    }

    @Override
    public Boolean deleteMember(Member member) {
        MemberEntity memberEntity = modelMapper.map(member, MemberEntity.class);
        return repository.delete(memberEntity);
    }

    @Override
    public Member findAdminExists(Member member) {
        MemberEntity memberEntity = modelMapper.map(member, MemberEntity.class);
        MemberEntity entity = repository.search(memberEntity);
        if (entity != null) {
            return modelMapper.map(entity, Member.class);
        }
        return null;
    }
}
