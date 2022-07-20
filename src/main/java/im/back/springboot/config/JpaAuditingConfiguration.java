package im.back.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/*
    JPA Auditing 을 활성화시켜 중복되거나 자주 생성, 수정되는 field 를 분리하여 관리
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {

}
