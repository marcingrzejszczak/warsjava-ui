package pl.warsjawa.loan

import com.ofg.base.MicroserviceMvcWiremockSpec

class AcceptanceSpec extends MicroserviceMvcWiremockSpec {


    def "should forward user's request to fraud detection"() {
        given: "user's filled out form"
        when: "receiving that form"
        then: "it should be forwarded to fraud service"
    }

}
