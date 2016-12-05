package src.test.agh.cs

import src.agh.cs.Constitution
import src.agh.cs.ConstitutionBuilder
import src.agh.cs.InputParser
import src.agh.cs.TextParser

/**
 * Created by Grzegorz Jasinski on 05.12.16.
 */
class ConstitutionTest extends GroovyTestCase {
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
        def constitution = buildConstitution()
        assertEquals("KONSTYTUCJA\n" +
                "RZECZYPOSPOLITEJ POLSKIEJ\n" +
                "z dnia 2 kwietnia 1997 r.", constitution.getTitleOfConstitution)
    }

    void testShowSection2(){
        def constitution = buildConstitution("TextParserTests/KonstytucjaOriginal.txt")
        def parsedConstitution = parseConstitution("TextParserTests/KonstytucjaOriginal2.txt")
        assertEquals(parsedConstitution, constitution.toString(2))
    }

    void testShowSection7(){
        def constitution = buildConstitution("TextParserTests/KonstytucjaOriginal.txt")
        def parsedConstitution = parseConstitution("TextParserTests/KonstytucjaOriginal3.txt")
        assertEquals(parsedConstitution, constitution.toString(7))
    }

    void testShowArticles(){
        def constitution = buildConstitution("TextParserTests/KonstytucjaOriginal.txt")
        def parsedConstitution = parseConstitution("TextParserTests/KonstytucjaOriginal4.txt")
        assertEquals(parsedConstitution, constitution.toString(20,70))
    }
}
