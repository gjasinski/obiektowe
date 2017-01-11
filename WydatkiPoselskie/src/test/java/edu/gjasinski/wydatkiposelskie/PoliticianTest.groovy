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
        politician.getDurationOfTrips() == 0
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


    def 'method getDurationOfTrips should return duration of trips'(){
        given:
        def jsonObject1 = Mock(JSONObject)
        def jsonObject2 = Mock(JSONObject)
        def politician = new Politician(10, "Nowak Jan")

        jsonObject1.getString(_) >> "0"
        jsonObject1.getInt("liczba_dni") >> 10
        jsonObject1.getInt(_) >> 0
        jsonObject2.getString(_) >> "0"
        jsonObject2.getInt("liczba_dni") >> 20
        jsonObject2.getInt(_) >> 0

        def businessTrip1 = Spy(BusinessTrip, constructorArgs:[jsonObject1])
        def businessTrip2 = Spy(BusinessTrip, constructorArgs:[jsonObject2])

        when:
        politician.addBusinessTrip(businessTrip1)
        politician.addBusinessTrip(businessTrip2)
        def durationOfTrips = politician.getDurationOfTrips()

        then:
        durationOfTrips == 30
    }

    def 'method getCostOfMostExpensiveTrip should return cost of most expensive trip'(){
        given:
        def jsonObject1 = Mock(JSONObject)
        def jsonObject2 = Mock(JSONObject)
        def politician = new Politician(10, "Nowak Jan")

        jsonObject1.getString("koszt_suma") >> "1000.99"
        jsonObject1.getString(_) >> "10"
        jsonObject1.getInt(_) >> 0
        jsonObject2.getString("koszt_suma") >> "2000"
        jsonObject2.getString(_) >> "20"
        jsonObject2.getInt(_) >> 0

        def businessTrip1 = Spy(BusinessTrip, constructorArgs:[jsonObject1])
        def businessTrip2 = Spy(BusinessTrip, constructorArgs:[jsonObject2])

        when:
        politician.addBusinessTrip(businessTrip1)
        politician.addBusinessTrip(businessTrip2)
        def costOfMostExpensiveTrip = politician.getCostOfMostExpensiveTrip()

        then:
        costOfMostExpensiveTrip == new BigDecimal("2000")
    }


    def 'method hadBeenInItaly should return false'(){
        given:
        def jsonObject1 = Mock(JSONObject)
        def jsonObject2 = Mock(JSONObject)
        def politician1 = new Politician(10, "Nowak Jan")
        def politician2 = new Politician(11, "Jan Jan")

        jsonObject1.getString("kraj") >> "Polska"
        jsonObject1.getString(_) >> "10"
        jsonObject1.getInt(_) >> 0
        jsonObject2.getString("kraj") >> "USA"
        jsonObject2.getString(_) >> "20"
        jsonObject2.getInt(_) >> 0

        def businessTrip1 = Spy(BusinessTrip, constructorArgs:[jsonObject1])
        def businessTrip2 = Spy(BusinessTrip, constructorArgs:[jsonObject2])

        when:
        politician1.addBusinessTrip(businessTrip1)
        politician1.addBusinessTrip(businessTrip2)

        then:
        !politician1.hadBeenInItaly()
        !politician2.hadBeenInItaly()
    }
}
