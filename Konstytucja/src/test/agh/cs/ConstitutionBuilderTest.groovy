package src.test.agh.cs

import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import src.agh.cs.Constitution
import src.agh.cs.ConstitutionBuilder
import src.agh.cs.InputParser
import src.agh.cs.TextParser

/**
 * Created by Grzegorz Jasinski on 05.12.16.
 */
class ConstitutionBuilderTest extends GroovyTestCase {
    Constitution buildConstitution(String dir){
        def input = new String[2]
        input[0] = dir
        input[1] = "10"
        def inputParser = new InputParser(input)
        def textParser = new TextParser(inputParser.getFileLocalization())
        def constitutionBuilder = new ConstitutionBuilder(textParser.getParsedText())
        return constitutionBuilder.buildConstitution()
    }

    String parseConstitution(String dir){
        def input = new String[2]
        input[0] = dir
        input[1] = "10"
        def inputParser = new InputParser(input)
        def textParser = new TextParser(inputParser.getFileLocalization())
        return textParser.getParsedText()
    }

    void testTitleOfConstitution(){
        def constitution = buildConstitution("TextParserTests/KonstytucjaOriginal.txt")
        assert"KONSTYTUCJA\n" +
                "RZECZYPOSPOLITEJ POLSKIEJ\n" +
                "z dnia 2 kwietnia 1997 r." == constitution.getTitleOfConstitution
    }

    /*void testPreamble(){
        def constitution = buildConstitution("TextParserTests/Konstytucja.txt")
        def parsedConstitution = parseConstitution("TextParserTests/Konstytucja.txt")
        assert parsedConstitution.substring(0, parsedConstitution.indexOf("Rozdział")) == constitution.toString(0)
    }*/

    void testConstitution(){
        def constitution = buildConstitution("TextParserTests/KonstytucjaOriginal.txt")
        def parsedConstitution = parseConstitution("TextParserTests/KonstytucjaOriginal.txt")
        parsedConstitution = parsedConstitution.replace("wspólnot.\n", "wspólnot. ")
        assert parsedConstitution == constitution.toString()
    }
}
