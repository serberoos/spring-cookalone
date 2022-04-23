package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private Long receipeId;
    private Long userId;

    private String reviewContent;
    private String createdDate;
    private String modifiedDate;

}
