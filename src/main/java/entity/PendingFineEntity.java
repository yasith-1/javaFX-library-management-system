package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PendingFineEntity {
    private String memberId;
    private String bookIsbn;
    private String name;
    private String title;
    private Integer delayedDays;
}
