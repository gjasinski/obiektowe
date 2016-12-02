package agh.cs;


import javax.xml.soap.Text;
import java.util.ArrayList;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Constitution {
    private String title;
    private ArrayList<Section> section;
    private ArrayList<Article> articles;

    public static void main(String[] args) {
        try {
            InputParser inputParser = new InputParser(args);
            TextParser textParser = new TextParser(inputParser.getFileLocalization());
        }catch (IllegalArgumentException ex){
            System.out.print(ex.toString());
        }

    }
}
