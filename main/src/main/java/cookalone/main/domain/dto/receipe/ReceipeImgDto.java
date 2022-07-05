package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.ReceipeImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 * ReceipeImgDto of
 * ReceipeImg 객체를 파라미터로 받아서 receipeImg 객체의 자료형과 멤버변수의 이름이 같을 때, ItemImgDto로 값을 복사해 반환합니다.
 * static 메소드로 선언해 ItemImgDto 객체를 생성하지 않아도 호출할 수 있도록 한다.
 */
@Getter @Setter
public class ReceipeImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

//    private static ModelMapper modelMapper = new ModelMapper();
//
//    public static ReceipeImgDto of(ReceipeImg receipeImg){
//        return modelMapper.map(receipeImg, ReceipeImgDto.class);
//    }
}
