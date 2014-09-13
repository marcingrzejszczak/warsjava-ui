package pl.warsjawa.microservice

import com.ofg.infrastructure.environment.EnvironmentSetupVerifier
import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableAsync

import static com.ofg.config.BasicProfiles.*

@TypeChecked
@Configuration
@EnableAutoConfiguration(exclude = MetricRepositoryAutoConfiguration)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = ["pl.warsjawa.microservice", "pl.warsjawa.fraud"])
@EnableCaching
@EnableAsync
class Application {

    static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application)
        application.addListeners(new EnvironmentSetupVerifier([PRODUCTION, DEVELOPMENT, TEST]))
        application.run(args)
    }
}