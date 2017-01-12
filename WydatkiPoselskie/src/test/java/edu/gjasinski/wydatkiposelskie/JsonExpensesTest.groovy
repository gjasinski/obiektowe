package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.ExpensesTitles
import main.java.edu.gjasinski.wydatkiposelskie.JsonExpenses
import main.java.edu.gjasinski.wydatkiposelskie.Politician
import org.json.JSONArray
import org.json.JSONObject
import spock.lang.Specification


class JsonExpensesTest extends Specification {
    def "method updateYearExpenses should add expenses to year"() {
        given:
        def politician = Spy(Politician, constructorArgs: [10, "Nowak Jan"])
        def jsonObjectExpenses = createJSONObject()
        def expensesTitles = new ExpensesTitles()

        when:
        def jsonExpenses = new JsonExpenses(politician, jsonObjectExpenses, expensesTitles)
        jsonExpenses.updateAllExpenses()

        then:

        2 * politician.addExpenses(_)
        politician.getAllExpenses() == new BigDecimal("600")
    }

    def JSONObject createJSONObject() {
        return new JSONObject(
                "{\"liczba_pol\": 2," +
                "      \"liczba_rocznikow\": 2," +
                "      \"punkty\": [" +
                "        {" +
                "          \"tytul\": \"title1\"," +
                "          \"numer\": \"1\"" +
                "        }," +
                "        {" +
                "          \"tytul\": \"title2\"," +
                "          \"numer\": \"2\"" +
                "        }" +
                "      ]," +
                "      \"roczniki\": [" +
                "        {" +
                "          \"pola\": [" +
                "            \"100\"," +
                "            \"200\"" +
                "          ]," +
                "          \"dokument_id\": \"860531\"," +
                "          \"rok\": \"2013\"" +
                "        }," +
                "        {" +
                "          \"pola\": [" +
                "            \"300\"," +
                "            \"0.00\"," +
                "          ]," +
                "          \"dokument_id\": \"366161\"," +
                "          \"rok\": \"2012\"" +
                "        }" +
                "      ]" +
                "}")
    }
}
