package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertEquals(prototypeBean1.getCount(), 1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertEquals(prototypeBean2.getCount(), 1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertEquals(count1, 1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertEquals(count2, 1);
    }

    @Scope("singleton") // 생략 가능
    @RequiredArgsConstructor
    static class ClientBean {
        // 프로토타입 빈이지만 싱글톤 빈 생성 시점에 주입된 것을 계속 재사용
        // 서로 다른 싱글톤 빈은 다른 프로토타입 빈을 주입받긴 함
//        private final PrototypeBean prototypeBean;
//        private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        private final Provider<PrototypeBean> prototypeBeanObjectProvider;

//        @Autowired
//        ApplicationContext applicationContext;

        public int logic(){
            // 무식한 해결 방법 : Dependency Lookup(DL) 의존관계를 주입받는게 아니라 직접 찾는 것
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
