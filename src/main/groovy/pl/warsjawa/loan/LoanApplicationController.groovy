package pl.warsjawa.loan

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.constraints.NotNull

import static pl.warsjawa.loan.UiApi.*

@CompileStatic
@Slf4j
@RestController
@RequestMapping(API_URL)
@Api(value = "loanApplication", description = "Entry point to the generation of a loan application")
class LoanApplicationController {

    private final FraudCheckWorker fraudCheckWorker
    private int counter = 0;

    @Autowired
    LoanApplicationController(FraudCheckWorker fraudCheckWorker) {
        this.fraudCheckWorker = fraudCheckWorker
    }

    @RequestMapping(
            value = LOAN_APPLICATION_ROOT_URL,
            method = RequestMethod.POST,
            consumes = UI_API_VERSION_1,
            produces = UI_API_VERSION_1)
    @ApiOperation(value = "Sends a request ",
            notes = "This will asynchronously verify what's the probability of the user to be a fraud and will call LoanApplicationDecisionMaker")
    void checkIfUserIsFraud(@RequestBody @NotNull String loanApplicationDetails) {
        int loanApplicationId = ++counter
        fraudCheckWorker.checkIfUserIsFraud(loanApplicationId, loanApplicationDetails)
    }
}