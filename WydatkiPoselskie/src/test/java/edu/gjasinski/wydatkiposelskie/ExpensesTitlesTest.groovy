package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.ExpensesTitles
import spock.lang.Specification


class ExpensesTitlesTest extends Specification {
    def "set only unique titles"(){
        setup:
        ExpensesTitles expensesTitles = new ExpensesTitles()

        when:
        def id1 = expensesTitles.setExpensesTitleId("1")
        def id2 = expensesTitles.setExpensesTitleId("2")
        def id3 = expensesTitles.setExpensesTitleId("3")

        then:
        id1 != id2 != id3
        id1 == expensesTitles.getExpensesTitle("1")
        id2 == expensesTitles.getExpensesTitle("2")
        id3 == expensesTitles.getExpensesTitle("3")
    }

    def "set two pair of titles"(){
        setup:
        ExpensesTitles expensesTitles = new ExpensesTitles()

        when:
        def id1 = expensesTitles.setExpensesTitleId("1")
        def id2 = expensesTitles.setExpensesTitleId("2")
        def id3 = expensesTitles.setExpensesTitleId("1")
        def id4 = expensesTitles.setExpensesTitleId("2")

        then:
        id1 != id2
        id1 == id3
        id2 == id4
        id1 == expensesTitles.getExpensesTitle("1")
        id2 == expensesTitles.getExpensesTitle("2")
        id3 == expensesTitles.getExpensesTitle("1")
        id4 == expensesTitles.getExpensesTitle("2")
    }

    def "get title which is not in hashmap"(){
        setup:
        ExpensesTitles expensesTitles = new ExpensesTitles()

        when:
        expensesTitles.getExpensesTitle("2")

        then:
        thrown(IllegalArgumentException)
    }
}
