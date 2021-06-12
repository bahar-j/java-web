package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        // {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@5b218417, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@645aa696}
        System.out.println(discountService.getPolicyMap());
        // [hello.core.discount.FixDiscountPolicy@5b218417, hello.core.discount.RateDiscountPolicy@645aa696]
        System.out.println(discountService.getPolicies());

        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertTrue(discountService instanceof DiscountService);
        Assertions.assertEquals(discountPrice, 1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertEquals(rateDiscountPrice, 2000);
    }

    @RequiredArgsConstructor
    @Getter
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
