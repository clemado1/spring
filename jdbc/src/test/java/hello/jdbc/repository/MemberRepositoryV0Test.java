package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV2", 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById("memberV2");
        log.info("findMember={}", findMember);
        log.info("member == findMember = {}", member == findMember);
        log.info("member equals findMember = {}", member.equals(findMember)); // lombok @Data 사용 시 객체의 모든 필드를 사용하도록 `equals()` 오버라이딩
        assertThat(findMember).isEqualTo(member);
    }
}