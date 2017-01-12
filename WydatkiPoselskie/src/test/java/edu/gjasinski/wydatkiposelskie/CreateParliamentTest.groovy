package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.Expenses
import main.java.edu.gjasinski.wydatkiposelskie.ExpensesTitles
import main.java.edu.gjasinski.wydatkiposelskie.Politician
import main.java.edu.gjasinski.wydatkiposelskie.parliament.CreateParliament
import main.java.edu.gjasinski.wydatkiposelskie.parliament.Parliament
import spock.lang.Specification


class CreateParliamentTest extends Specification {

    def "constructor should create list of politcians"() {
        setup:
        CreateParliament createParliament

        when:
        createParliament = new CreateParliament("7")

        then:
        0 < createParliament.getParliament().getListOfPoliticians().size()
    }


    def "constructor should add details to profiles, then then method getPoliticiansAverageSumOfAllExpenses shouldn't return 0"() {
        setup:
        CreateParliament createParliament

        when:
        createParliament = new CreateParliament("7")

        then:
        BigDecimal.ZERO < createParliament.getParliament().getPoliticiansAverageSumOfAllExpenses()
    }
}