package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberEntity {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String password;
    private String typeId;
}
