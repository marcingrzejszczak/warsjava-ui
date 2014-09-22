package pl.warsjawa.loan

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic

import static pl.warsjawa.loan.UiApi.FRAUD_API_VERSION_1
import static pl.warsjawa.loan.UiApi.FRAUD_LOAN_APPLICATION_URL

@CompileStatic
class FraudCheckWorker {
    private final ServiceRestClient serviceRestClient
    private final LoanAmountMetricCollector loanAmountMetricCollector

    FraudCheckWorker(ServiceRestClient serviceRestClient, LoanAmountMetricCollector loanAmountMetricCollector) {
        this.serviceRestClient = serviceRestClient
        this.loanAmountMetricCollector = loanAmountMetricCollector
    }

    void checkIfUserIsFraud(String loanApplicationId, String loanApplicationDetails) {
        loanAmountMetricCollector.updateLoanAmountMetric(loanApplicationDetails)
        serviceRestClient.forService(Dependencies.FRED.toString())
                .put()
                .onUrlFromTemplate(FRAUD_LOAN_APPLICATION_URL)
                .withVariables(loanApplicationId)
                .body(loanApplicationDetails)
                .withHeaders()
                .contentType(FRAUD_API_VERSION_1)
                .andExecuteFor()
                .ignoringResponse()

    }
}
