package dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fine {
    private String id;
    private String reason;
    private LocalDate paidDate;
    private LocalTime paidTime;
    private Double amount;
    private String memberId;
    private String bookIsbn;
    private String statusId;
}
