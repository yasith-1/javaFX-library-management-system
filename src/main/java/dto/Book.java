package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String isbn;
    private String title;
    private Integer copies;
    private String statusId;
    private String gerneId;
    private String authorId;
}
