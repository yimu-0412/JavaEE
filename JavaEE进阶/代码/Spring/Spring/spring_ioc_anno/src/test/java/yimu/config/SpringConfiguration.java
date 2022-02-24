package yimu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-22:16
 */
// 标志该类是 Spring 的核心配置类
// <context:component-scan base-package="yimu"/>
@Configuration
@ComponentScan("yimu")
@Import(DataSourceConfig.class)
public class SpringConfiguration {

}
