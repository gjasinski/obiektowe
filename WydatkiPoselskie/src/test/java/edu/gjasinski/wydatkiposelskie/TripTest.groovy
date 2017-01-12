package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.Trip
import org.json.JSONObject
import spock.lang.Specification


class TripTest extends Specification {
    def "Test constructor and getters and this is not trip to italy"(){
        given:
        def jsonObject  =  Mock(JSONObject)
        jsonObject.getString("delegacja") >> "Jakas nowa delegacja"
        jsonObject.getString("kraj") >> "Rosja"
        jsonObject.getInt("id") >> 303
        jsonObject.getInt("liczba_dni") >> 30
        jsonObject.getString("koszt_suma") >> "654321.87"

        when:
        def trip = new Trip(jsonObject)

        then:
        trip.getDurationOfTripInDays() == 30
        trip.getTotalCost() == 654321.87
        !trip.thisIsTripToItaly()
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
        def trip = new Trip(jsonObject)

        then:
        trip.getDurationOfTripInDays() == 30
        trip.getTotalCost() == 654321.87
        trip.thisIsTripToItaly()
    }
}
