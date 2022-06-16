package cookalone.main.domain.dto.account;

import lombok.Data;

import javax.validation.constraints.AssertTrue;

@Data
public class JoinTermsRequestDto {

    @AssertTrue(message="쿡얼론 이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.")
    private boolean termsAgreement;
}
