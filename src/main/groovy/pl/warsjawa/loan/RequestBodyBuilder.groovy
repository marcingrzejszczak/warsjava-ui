package pl.warsjawa.loan

import groovy.json.JsonSlurper
import groovy.transform.PackageScope

@PackageScope
class RequestBodyBuilder {

    String createClientsRequestBody(int loanApplicationId, String loanApplicationDetails) {
        def root = new JsonSlurper().parseText(loanApplicationDetails)
        def builder = new groovy.json.JsonBuilder()
        builder {
            firstName root.firstName
            lastName root.lastName
            id loanApplicationId
        }

        return builder.toString()
    }

}
