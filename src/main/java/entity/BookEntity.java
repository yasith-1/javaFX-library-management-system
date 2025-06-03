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
    private String statusId;
    private String gerneId;
    private String authorId;
}
