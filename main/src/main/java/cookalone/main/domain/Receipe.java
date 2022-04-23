package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Receipe {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String titie;
    private String content;
    private String writer;
    private int viewCnt;
    private String createdDate;
    private String modifiedDate;

}
