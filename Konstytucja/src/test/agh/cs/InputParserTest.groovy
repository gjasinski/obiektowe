import org.junit.Before
import org.junit.Test
import src.agh.cs.InputParser

/**
 * Created by Grzegorz Jasinski on 05.12.16.
 */
class InputParserTest extends GroovyTestCase {

    void testGetFileLocalization(){
        def input = new String[3]
        input[0] = "Konstytucja.txt"
        input[1] = "12"
        input[2] = "20"
        def inputParser = new InputParser(input)
        assert "Konstytucja.txt" == inputParser.getFileLocalization()

        input[0] = "dira/dirb/dirc/aaaa/test.txt"
        inputParser = new InputParser(input)
        assert "dira/dirb/dirc/aaaa/test.txt" == inputParser.getFileLocalization()
    }

    void testGetSectionNumber(){
        def input = new String[2]
        input[0] = "Konstytucja.txt "
        input[1] = "12"
        def inputParser = new InputParser(input)
        inputParser.getSectionNumber()
        assert 12 == inputParser.getSectionNumber()
    }

    void testGetArticleNumbers(){
        def input = new String[3]
        input[0] = "Konstytucja.txt"
        input[1] = "12"
        input[2] = "20"
        def inputParser = new InputParser(input)
        assert 12 == inputParser.getFirstArticleNumber()
        assert 20 == inputParser.getLastArticleNumber()
    }

    void testShowSection(){
        try {
            def input = new String[3]
            input[0] = "Konstytucja.txt"
            input[1] = "12"
            input[2] = "20"
            def inputParser = new InputParser(input)
            assert !inputParser.showSection()

            def input2 = new String[2]
            input2[0] = "Konstytucja.txt"
            input2[1] = "12"
            inputParser = new InputParser(input2)
            assert inputParser.showSection()
        }catch (Exception ex){
            println(ex.toString())
        }
    }
}
