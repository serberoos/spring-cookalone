package cookalone.main.domain.dto.account;

import lombok.Data;

import javax.validation.constraints.AssertTrue;

@Data
public class TermsDto {

    @AssertTrue
    private boolean termsAgreement;
}
