package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal(); // 내부 메서드 호출 (this.internal())
        // Proxy 가 아닌 this 본인을 호출하기 때문에 AOP 적용되지 않는다.
    }

    public void internal() {
        log.info("call internal");
    }
}
