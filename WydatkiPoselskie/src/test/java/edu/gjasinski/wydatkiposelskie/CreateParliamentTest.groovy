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
        createParliament = new CreateParliament()

        then:
        0 < createParliament.getParliament().getListOfPoliticians().size()
    }

    def "constructor should create list of politicians and details should be empty, then method getPoliticiansAverageSumOfAllExpenses should return 0"() {
        setup:
        CreateParliament createParliament

        when:
        createParliament = new CreateParliament()

        then:
        BigDecimal.ZERO == createParliament.getParliament().getPoliticiansAverageSumOfAllExpenses()
    }

    def "method updatePoliticiansProfile() should add details to profiles, then then method getPoliticiansAverageSumOfAllExpenses shouldn't return 0"() {
        setup:
        CreateParliament createParliament = new CreateParliament()

        when:
        createParliament.updatePoliticiansProfile()

        then:
        BigDecimal.ZERO < createParliament.getParliament().getPoliticiansAverageSumOfAllExpenses()
    }

    def "method getPoliticianSumOfAllExpenses"(){
        given:
        def expenses1 = Mock(Expenses)
        def expenses2 = Mock(Expenses)
        expenses1.getSumOfExpenses() << new BigDecimal(99721)
        expenses2.getSumOfExpenses() << new BigDecimal(279)
        def politician = Mock(Politician)
        politician.
        Parliament parliament = new Parliament()
    }
}