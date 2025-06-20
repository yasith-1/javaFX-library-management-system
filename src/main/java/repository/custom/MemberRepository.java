package repository.custom;

import entity.MemberEntity;
import repository.CrudRepository;

import java.util.HashMap;
import java.util.List;

public interface MemberRepository extends CrudRepository<MemberEntity, String> {
    String getId();

    List<MemberEntity> getMembersList();

    HashMap<String, String> getMemberTypeSet();
}
