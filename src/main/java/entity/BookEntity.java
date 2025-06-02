package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {
    private String isbn;
    private String title;
    private Integer copies;
    private Integer statusId;
    private String gerneId;
    private String authorId;
}
