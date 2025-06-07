package dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IssuedBook {
    private String memberId;
    private String isbn;
    private Integer qty;
    private LocalDate date;
    private LocalTime time;
    private LocalDate returnDate;
}
