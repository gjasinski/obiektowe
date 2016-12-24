package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.Expenses
import main.java.edu.gjasinski.wydatkiposelskie.ExpensesTitles
import java.lang.String
import spock.lang.Specification


class ExpensesTest extends Specification {
    def "test constructor - should getters should always return 0"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        expensesTitles.getExpensesTitle(_) >> -1

        when:
        def expenses = new Expenses(2012, expensesTitles)

        then:
        0 * expensesTitles.setExpensesTitleId(_)
        expenses.getSumOfExpenses() == BigDecimal.ZERO
        expenses.getExpensesForSmallRepairsOfPoliticianOffice() == BigDecimal.ZERO
    }

    def "add two different titles of expenses"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses = new Expenses(2012, expensesTitles)
        expensesTitles.setExpensesTitleId("t1") >> 0
        expensesTitles.setExpensesTitleId("t2") >> 1

        when:
        expenses.addExpense("t1", "12345.67")
        expenses.addExpense("t2", "98765.43")

        then:
        expenses.getSumOfExpenses() == new BigDecimal("111111.1")
    }

    def "add two pairs titles of expenses should return sum of newest records"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses = new Expenses(2012, expensesTitles)
        expensesTitles.setExpensesTitleId("t1") >> 0
        expensesTitles.setExpensesTitleId("t2") >> 1

        when:
        expenses.addExpense("t1", "1000000")
        expenses.addExpense("t2", "5000")
        expenses.addExpense("t1", "100000")
        expenses.addExpense("t2", "500")

        then:
        expenses.getSumOfExpenses() == new BigDecimal(100500)
    }


    def "empty record for small repairs and record is not in ExpensesTitles should return zero"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses = new Expenses(2012, expensesTitles)
        expensesTitles.setExpensesTitleId("t1") >> 0
        expensesTitles.setExpensesTitleId("t2") >> 1
        expensesTitles.getExpensesTitle("Koszty drobnych napraw i remontów lokalu biura poselskiego") >> -1

        when:
        expenses.addExpense("t1", "1000000")
        expenses.addExpense("t2", "5000")

        then:
        expenses.getExpensesForSmallRepairsOfPoliticianOffice() == BigDecimal.ZERO
    }

    def "empty record for small repairs and record is in ExpensesTitles should return zero"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses = new Expenses(2012, expensesTitles)
        expensesTitles.setExpensesTitleId("t1") >> 0
        expensesTitles.setExpensesTitleId("t2") >> 1
        expensesTitles.getExpensesTitle("Koszty drobnych napraw i remontów lokalu biura poselskiego") >> 10

        when:
        expenses.addExpense("t1", "1000000")
        expenses.addExpense("t2", "5000")

        then:
        expenses.getExpensesForSmallRepairsOfPoliticianOffice() == BigDecimal.ZERO
    }

    def "empty record for small repairs and record is in ExpensesTitles should return value"(){
        given:
        def expensesTitles = Mock(ExpensesTitles)
        def expenses = new Expenses(2012, expensesTitles)
        expensesTitles.setExpensesTitleId("t1") >> 0
        expensesTitles.setExpensesTitleId("t2") >> 1
        expensesTitles.setExpensesTitleId("Koszty drobnych napraw i remontów lokalu biura poselskiego") >> 10
        expensesTitles.getExpensesTitle("Koszty drobnych napraw i remontów lokalu biura poselskiego") >> 10

        when:
        expenses.addExpense("t1", "1000000")
        expenses.addExpense("t2", "5000")
        expenses.addExpense("Koszty drobnych napraw i remontów lokalu biura poselskiego", "5000")

        then:
        expenses.getExpensesForSmallRepairsOfPoliticianOffice() == new BigDecimal("5000")
    }
}
