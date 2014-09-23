package pl.warsjawa.loan

import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j

@Slf4j
@PackageScope
class LoanAmountMetricCollector {

    public static final String LOAN_AMOUNT_METRIC_NAME = 'loanAmount'
    private final Meter loanAmountMetric

    LoanAmountMetricCollector(MetricRegistry metricRegistry) {
        this.loanAmountMetric = metricRegistry.meter(LOAN_AMOUNT_METRIC_NAME)
    }

    void updateLoanAmountMetric(String loanApplicationDetails) {
        def root = new JsonSlurper().parseText(loanApplicationDetails)
        String amount = root.amount
        log.debug("Updating metrics for amount [$amount]")
        if (amount.isNumber()) {
            loanAmountMetric.mark(amount.toLong())
        }
    }
}
