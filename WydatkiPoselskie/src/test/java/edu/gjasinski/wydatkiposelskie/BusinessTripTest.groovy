package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.BusinessTrip
import org.json.JSONObject
import spock.lang.Specification


class BusinessTripTest extends Specification {
    def "Test constructor and getters and this is not trip to italy"(){
        given:
        def jsonObject  =  Mock(JSONObject)
        jsonObject.getString("delegacja") >> "Jakas nowa delegacja"
        jsonObject.getString("kraj") >> "Rosja"
        jsonObject.getInt("id") >> 303
        jsonObject.getInt("liczba_dni") >> 30
        jsonObject.getString("koszt_suma") >> "654321.87"

        when:
        def businessTrip = new BusinessTrip(jsonObject)

        then:
        businessTrip.getDurationOfTripInDays() == 30
        businessTrip.getTotalCost() == 654321.87
        !businessTrip.thisIsTripToItaly()
    }

    def "Test constructor and getters and this is trip to italy"(){
        given:
        def jsonObject  =  Mock(JSONObject)
        jsonObject.getString("delegacja") >> "Jakas nowa delegacja"
        jsonObject.getString("kraj") >> "Włochy"
        jsonObject.getInt("id") >> 303
        jsonObject.getInt("liczba_dni") >> 30
        jsonObject.getString("koszt_suma") >> "654321.87"

        when:
        def businessTrip = new BusinessTrip(jsonObject)

        then:
        businessTrip.getDurationOfTripInDays() == 30
        businessTrip.getTotalCost() == 654321.87
        businessTrip.thisIsTripToItaly()
    }
}
