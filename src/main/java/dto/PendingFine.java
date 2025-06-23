package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PendingFine {
    private String memberId;
    private String bookIsbn;
    private String name;
    private Integer delayedDays;
}
