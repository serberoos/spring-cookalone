package cookalone.main.service;

import cookalone.main.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 회원가입을 성공해야 한다.
 * 회원가입 시 같은 닉네임이 있다면 예외가 발생해야 한다.
 */

/**
 * RunWith : Spring과의 intigration
 * Transactional : DB를 변경해야되기 때문에.. ( 같은 영속성 컨텍스트 안에서 관리 )
 * Rollback Spring Transactional이 TestCase에 있으면 기본적으로 롤백을 하는데 이를 막는다.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberServiceImpl userService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
//        User user = new User();
//        user.setUsername("kim");

        //when
//        Long savedId = userService.join(user);
//
//        //then
//        assertEquals(user, userRepository.findOne(savedId));

    }

    /**
     * Test(expected = IllegalStateException.class) 간단화
     *         try {
     *             userService.join(user2); // 예외가 발생해야 한다.
     *         }catch(IllegalStateException e){
     *             return;
     *         }
     */
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
//        //given
//        User user1 = new User();
//        user1.setNickname("kim");
//
//        User user2 = new User();
//        user2.setNickname("kim");
//
//        //when
//        userService.join(user1);
//        userService.join(user2); // 예외가 발생해야 한다.

        //then
        fail("중복 회원 예외가 발생해야 합니다.");
    }

}