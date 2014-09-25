package pl.warsjawa.loan

import groovy.json.JsonSlurper
import groovy.transform.PackageScope

@PackageScope
class RequestBodyBuilder {

    String createClientsRequestBody(String loanApplicationId, String loanApplicationDetails) {
        def root = new JsonSlurper().parseText(loanApplicationDetails)
        def builder = new groovy.json.JsonBuilder()
        builder {
            name root.name
            lastName root.lastName
            id loanApplicationId
        }

        return builder.toString()
    }

}
