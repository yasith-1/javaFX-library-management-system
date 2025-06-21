package entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DelayReturnEntity {
    private String memberId;
    private String bookId;
    private String memberName;
    private LocalDate issueDate;
    private LocalDate dateToReturn;
    private LocalDate returnedDate;
    private LocalTime returnedTime;
    private Integer delayedDays;
}
