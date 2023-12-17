import com.discordLike.Application;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class BeanTest{
    @Test
    public void show() {
        Logger logger = LoggerFactory.getLogger(Application.class);
        ApplicationContext ctx = SpringApplication.run(Application.class);
        String[] beanNames = ctx.getBeanDefinitionNames();
        logger.info("bean总数:{}", ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            logger.info("{},beanName:{}", ++i, str);
        }
    }
}