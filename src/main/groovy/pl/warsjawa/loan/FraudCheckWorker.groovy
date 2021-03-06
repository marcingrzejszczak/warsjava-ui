package pl.warsjawa.loan

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import static pl.warsjawa.loan.UiApi.*

@Slf4j
@CompileStatic
class FraudCheckWorker {
    private final ServiceRestClient serviceRestClient
    private final LoanAmountMetricCollector loanAmountMetricCollector
    private final RequestBodyBuilder requestBodyBuilder

    FraudCheckWorker(ServiceRestClient serviceRestClient, LoanAmountMetricCollector loanAmountMetricCollector, RequestBodyBuilder requestBodyBuilder) {
        this.requestBodyBuilder = requestBodyBuilder
        this.serviceRestClient = serviceRestClient
        this.loanAmountMetricCollector = loanAmountMetricCollector
    }

    void checkIfUserIsFraud(int loanApplicationId, String loanApplicationDetails) {
        loanAmountMetricCollector.updateLoanAmountMetric(loanApplicationDetails)
        propagateToFraudService(loanApplicationId, loanApplicationDetails)
        propagateToClientsService(loanApplicationId, loanApplicationDetails)
    }

    private propagateToFraudService(int loanApplicationId, String loanApplicationDetails) {
        log.info("Sending a request to [$Dependencies.FRED] to check if the client is a potential fraud")
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

   private propagateToClientsService(int loanApplicationId, String loanApplicationDetails) {
       log.info("Sending a request to [$Dependencies.CLIENTS] to store data about new client")
        serviceRestClient.forService(Dependencies.CLIENTS.toString())
                .post()
                .onUrl(CLIENTS_SERVICE_URL)
                .body(requestBodyBuilder.createClientsRequestBody(loanApplicationId, loanApplicationDetails))
                .withHeaders()
                    .contentType(CLIENTS_JSON)
                .andExecuteFor()
                .ignoringResponse()
    }
}
