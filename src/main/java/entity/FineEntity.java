package entity;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FineEntity {
    private String id;
    private String reason;
    private LocalDate paidDate;
    private LocalTime paidTime;
    private Double amount;
    private String memberId;
    private String bookIsbn;
    private String statusId;
}
