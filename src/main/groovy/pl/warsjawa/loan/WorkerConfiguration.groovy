package pl.warsjawa.loan
import com.codahale.metrics.MetricRegistry
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@CompileStatic
@Configuration
class WorkerConfiguration {

    @Bean @PackageScope FraudCheckWorker fraudCheckWorker(MetricRegistry metricRegistry, ServiceRestClient serviceRestClient) {
        return new FraudCheckWorker(serviceRestClient, new LoanAmountMetricCollector(metricRegistry))
    }

}
