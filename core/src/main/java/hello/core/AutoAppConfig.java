package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동으로 등록 시 자동으로 등록하는 빈을 Overriding 한다.
    // Overriding bean definition for bean 'memoryMemberRepository' with a different definition
    // 대신 Spring Boot 로 실행할 경우 에러가 발생한다.
    // spring.main.allow-bean-definition-overriding=false 기본값
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
