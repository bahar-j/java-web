package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void findByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertTrue(rateDiscountPolicy instanceof RateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면, 특정 하위 타입으로 조회") // 늘 구체타입을 명시하는건 좋지 않음
    void findBySubType(){
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertTrue(rateDiscountPolicy instanceof RateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertEquals(beansOfType.size(), 2);
        for (String key: beansOfType.keySet()){ // 실제로 현업에서 테스트할 때는 이렇게 프린트해서 눈으로 테스트하진 않음
            System.out.println("key= " + key + "value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key: beansOfType.keySet()){ // 스프링 내부적으로 쓰는 빈까지 다 나옴
            System.out.println("key= " + key + "value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
