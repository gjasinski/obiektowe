package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.parliament.ParliamentBuilder
import spock.lang.Specification


class ParliamentBuilderTest extends Specification {

    def "constructor should create list of politcians"() {
        setup:
        ParliamentBuilder createParliament

        when:
        createParliament = new ParliamentBuilder("7")

        then:
        0 < createParliament.getParliament().getListOfPoliticians().size()
    }


    def "constructor should add details to profiles, then then method getPoliticiansAverageSumOfAllExpenses shouldn't return 0"() {
        setup:
        ParliamentBuilder createParliament

        when:
        createParliament = new ParliamentBuilder("7")

        then:
        BigDecimal.ZERO < createParliament.getParliament().getPoliticiansAverageSumOfAllExpenses()
    }
}