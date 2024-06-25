package jpabook.jpashop;


import jakarta.transaction.Transactional;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception{
        //given 테스트 수행하기 위한 초기 상황
        Member member = new Member();
        member.setUsername("memberA");

        //when 테스트 대상 동작 실행
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then 실행 결과가 예상대로 나왔는지 검증
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

        System.out.println("findMember == member: " + (findMember == member));
    }
}