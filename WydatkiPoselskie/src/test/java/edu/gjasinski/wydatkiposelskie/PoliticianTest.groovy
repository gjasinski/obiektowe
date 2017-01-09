package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.BusinessTrip
import main.java.edu.gjasinski.wydatkiposelskie.Expenses
import main.java.edu.gjasinski.wydatkiposelskie.ExpensesTitles
import main.java.edu.gjasinski.wydatkiposelskie.Politician
import org.json.JSONObject
import spock.lang.Specification


class PoliticianTest extends Specification {
    def 'constructor test - basic data - getters should return id and name,  detailed data - getters should return zero'(){
        when:
        def politician = new Politician(10, "Nowak Jan")
        then:
        politician.getId() == 10
        politician.getLastNameFirstName() == "Nowak Jan"
        politician.getAllExpenses() == BigDecimal.ZERO
        politician.getAllExpensesForSmallRepairsOfPoliticianOffice() == BigDecimal.ZERO
        politician.getQuantityOfTips() == 0
        politician.getDurationOfLongestTrip() == 0
        politician.getCostOfMostExpensiveTrip() == BigDecimal.ZERO
        !politician.hadBeenInItaly()
    }

    def 'method getAllExpenses should return all expenses'(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses1 = Spy(Expenses, constructorArgs:[2010, expensesTitles])
        def expenses2 = Spy(Expenses, constructorArgs:[2011, expensesTitles])

        def politician = new Politician(10, "Nowak Jan")
        expenses1.getSumOfExpenses() >> new BigDecimal("10000.01")
        expenses2.getSumOfExpenses() >> new BigDecimal("1000.11")

        when:
        politician.addExpenses(expenses1)
        politician.addExpenses(expenses2)

        then:
        politician.getAllExpenses() == new BigDecimal("11000.12")
    }

    def 'method getAllExpensesForSmallRepairsOfPoliticianOffice should return all expenses for repairs'(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses1 = Spy(Expenses, constructorArgs:[2010, expensesTitles])
        def expenses2 = Spy(Expenses, constructorArgs:[2011, expensesTitles])
        def expenses3 = Spy(Expenses, constructorArgs:[2012, expensesTitles])

        def politician = new Politician(10, "Nowak Jan")
        expenses1.getExpensesForSmallRepairsOfPoliticianOffice() >> new BigDecimal("10000.01")
        expenses2.getExpensesForSmallRepairsOfPoliticianOffice() >> new BigDecimal("1000.11")
        expenses3.getExpensesForSmallRepairsOfPoliticianOffice() >> new BigDecimal("0")

        when:
        politician.addExpenses(expenses1)
        politician.addExpenses(expenses2)
        politician.addExpenses(expenses3)

        then:
        politician.getAllExpensesForSmallRepairsOfPoliticianOffice() == new BigDecimal("11000.12")
    }

    def 'method getQuantityOfTrips should return quantity of trips'(){
        given:
        def businessTrip1
        def businessTrip2
        def politician = new Politician(10, "Nowak Jan")

        when:
        politician.addBusinessTrip(businessTrip1)
        politician.addBusinessTrip(businessTrip2)
        def quantityOfTrips = politician.getQuantityOfTips()

        then:
        quantityOfTrips == 2
    }


    def 'method getLongestTrip should return longest trip'(){
        def jsonObject = Mock(JSONObject)
        jsonObject.getInt("liczba_dni") >> 20
        jsonObject.getInt(_) >> 0
        jsonObject.getString(_) >> ""
        def businessTrip1 = Spy(BusinessTrip, args:[jsonObject])
        def politician = new Politician(10, "Nowak Jan")
        //businessTrip1.getDurationOfTripInDays() >> 10
        ///businessTrip2.getDurationOfTripInDays() >> 20

        when:
        politician.addBusinessTrip(businessTrip1)
        //politician.addBusinessTrip(businessTrip2)
        def durationOfLongestTrip = politician.getDurationOfLongestTrip()

        then:
        durationOfLongestTrip == 20
    }

}
