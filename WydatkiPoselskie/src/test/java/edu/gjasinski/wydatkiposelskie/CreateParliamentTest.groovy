package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.CreateParliament
import spock.lang.Specification


class CreateParliamentTest extends Specification {
    def "constructor should create list of politcians"(){
        setup:
        CreateParliament createParliament

        when:
        createParliament = new CreateParliament()

        then:
        0 < createParliament.getParliament().getListOfPoliticians().size()
    }

    def "1constructor should create list of politcians"(){
        setup:
        CreateParliament createParliament

        when:
        createParliament = new CreateParliament()

        then:
        0 < createParliament.getParliament()
    }
}
