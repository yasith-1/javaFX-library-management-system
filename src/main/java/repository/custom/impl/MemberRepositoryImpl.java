package repository.custom.impl;

import entity.MemberEntity;
import repository.custom.MemberRepository;
import java.util.HashMap;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {

    @Override
    public List<MemberEntity> getMembersList() {
        return List.of();
    }

    @Override
    public HashMap<String, String> getMemberTypeSet() {
        return null;
    }

    @Override
    public Boolean add(MemberEntity entity) {
        return null;
    }

    @Override
    public Boolean update(MemberEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(MemberEntity entity) {
        return null;
    }

    @Override
    public MemberEntity search(MemberEntity entity) {
        return null;
    }

}
