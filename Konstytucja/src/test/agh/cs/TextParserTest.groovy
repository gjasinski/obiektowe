package src.test.agh.cs

import src.agh.cs.TextParser

/**
 * Created by Grzegorz Jasinski on 05.12.16.
 */
class TextParserTest extends GroovyTestCase {
    void testParseTitle(){
        def textParser = new TextParser("TextParserTests/test1.txt")
        def testAns = "KONSTYTUCJA"+"\n"+"RZECZYPOSPOLITEJ POLSKIEJ"+"\n"+"z dnia 2 kwietnia 1997 r."+"\n"+"W trosce o byt i przyszłość naszej Ojczyzny, odzyskawszy w 1989 roku możliwość suwerennego i demokratycznego stanowienia o Jej losie, "
        assertEquals(testAns, textParser.getParsedText())
    }

    void testParseSectionArticleAndJoiningWords(){
        def textParser = new TextParser("TextParserTests/test2.txt")
        def testAns = "społecznym oraz na zasadzie pomocniczości umacniającej uprawnienia obywateli i ich wspólnot.\nWszystkich, którzy dla dobra Trzeciej Rzeczypospolitej tę Konstytucję będą stosowali, wzywamy, aby czynili to, dbając o zachowanie przyrodzonej godności człowieka, jego prawa do wolności i obowiązku solidarności z innymi, a poszanowanie tych zasad mieli za niewzruszoną podstawę Rzeczypospolitej Polskiej.\nRozdział I\nRZECZPOSPOLITA\nArt. 1.\nRzeczpospolita Polska jest dobrem wspólnym wszystkich obywateli.\nArt. 2.\n"

        assertEquals(testAns, textParser.getParsedText())
    }

    void testParseArticles(){
        def textParser = new TextParser("TextParserTests/test3.txt")
        def testAns = "Art. 52.\n1. Każdemu zapewnia się wolność poruszania się po terytorium Rzeczypospolitej Polskiej oraz wyboru miejsca zamieszkania i pobytu.\n2. Każdy może swobodnie opuścić terytorium Rzeczypospolitej Polskiej.\n3. Wolności, o których mowa w ust. 1 i 2, mogą podlegać ograniczeniom określonym w ustawie.\n4. Obywatela polskiego nie można wydalić z kraju ani zakazać mu powrotu do kraju.\n5. Osoba, której pochodzenie polskie zostało stwierdzone zgodnie z ustawą, może osiedlić się na terytorium Rzeczypospolitej Polskiej na stałe.\nArt. 53.\n"

        assertEquals(testAns, textParser.getParsedText())
    }
}
