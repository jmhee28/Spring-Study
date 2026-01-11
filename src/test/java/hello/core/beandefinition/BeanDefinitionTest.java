package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
  @Test
  @DisplayName("빈 설정 메타정보 출력하기")
  void findBeanDefinition() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println("애플리케이션 빈 " + "beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
      } else if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
        System.out.println("스프링 내부 빈 " + "beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
      }
    }


  }

}
