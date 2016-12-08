package src.agh.cs;

import java.io.IOException;

/**
 * Created by Grzegorz Jasinski on 03.12.16.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            InputParser inputParser = new InputParser(args);
            TextParser textParser = new TextParser(inputParser.getFileLocalization());
            String parsedText = textParser.getParsedText();
            ConstitutionBuilder constitutionBuilder = new ConstitutionBuilder(parsedText);
            Constitution constitution = constitutionBuilder.buildConstitution();
            if(inputParser.showSection()){
                System.out.print(constitution.toString(inputParser.getSectionNumber()));
            }else{
                System.out.print(constitution.toString(inputParser.getFirstArticleNumber(), inputParser.getLastArticleNumber()));
            }
        }catch (IllegalArgumentException ex){
            System.out.print(ex.toString());
        }
        catch (IOException ex){
            System.out.print(ex.toString());
        }

    }
}
