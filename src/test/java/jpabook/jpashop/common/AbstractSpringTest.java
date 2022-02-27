package jpabook.jpashop.common;

import jpabook.jpashop.JpashopApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpashopApplication.class)
@ActiveProfiles("test")
public abstract class AbstractSpringTest {

}