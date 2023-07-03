package hellospring.hello.repository;

import hellospring.hello.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        Assertions.assertThat(member).isEqualTo(result);
    }
}
