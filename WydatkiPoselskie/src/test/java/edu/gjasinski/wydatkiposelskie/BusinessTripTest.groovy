package test.java.edu.gjasinski.wydatkiposelskie

import groovy.json.JsonSlurper
import main.java.edu.gjasinski.wydatkiposelskie.BusinessTrip
import org.json.JSONObject
import spock.lang.Specification


class BusinessTripTest extends Specification {
    def "Test constructor and getters and this is not trip to italy"(){
        given:
        def jsonSlurper = new JsonSlurper()
        def jsonObject  =  (JSONObject)jsonSlurper.parseText("{" +
                "\"delegacja\":\"Jakas nowa delegacja\"," +
                "\"kraj\":\"Wielka Brytania\"," +
                "\"id\":303," +
                "\"liczba_dni\":30," +
                "\"koszt_suma\":\"654321.87\""+
                "}")

        when:
        def businessTrip = new BusinessTrip(jsonObject)

        then:
        30 == businessTrip.getDurationOfTripInDays()
        654321.87 == businessTrip.getTotalCost()
        !businessTrip.thisIsTripToItaly()
    }

    def "Test constructor and getters and this is trip to italy"(){
        given:
        def jsonSlurper = new JsonSlurper()
        def jsonObject  =  (JSONObject)jsonSlurper.parseText("{" +
                "\"delegacja\":\"Jakas nowa delegacja\"," +
                "\"kraj\":\"Włochy\"," +
                "\"id\":303," +
                "\"liczba_dni\":30," +
                "\"koszt_suma\":\"654321.87\""+
                "}")

        when:
        def businessTrip = new BusinessTrip(jsonObject)

        then:
        30 == businessTrip.getDurationOfTripInDays()
        654321.87 == businessTrip.getTotalCost()
        businessTrip.thisIsTripToItaly()
    }
}
