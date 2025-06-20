package service.custom;

import dto.Member;;
import service.SuperService;

import java.util.HashMap;
import java.util.List;

public interface MemberService extends SuperService {
    String getMemberId();

    List<Member> getMembersList();

    HashMap<String, String> getMemberMap();

    Boolean addMember(Member member);

    Boolean updateMember(Member member);

    Boolean deleteMember(Member member);
}
