package jpabook.jpashop.global;

import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AppContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        return AppContextHolder.context.getBean(clazz);
    }

    public static boolean containsBean(String beanName) {
        return AppContextHolder.context.containsBean(beanName);
    }

    public static <T> boolean containsBean(Class<T> clazz) {
        return AppContextHolder.context.getBeanNamesForType(clazz).length > 0;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        AppContextHolder.context = applicationContext;
    }


}