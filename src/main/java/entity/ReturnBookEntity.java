package entity;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnBookEntity {
    private String memberId;
    private String isbn;
    private LocalDate returnDate;
    private LocalTime returnTime;
}
