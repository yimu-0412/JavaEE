package dataTest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-21:12
 */
@Configuration // //标志该类是Spring的核心配置类
//<context:component-scan base-package="dataTest"/>
@ComponentScan("dataTest")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
