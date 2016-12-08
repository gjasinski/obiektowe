package src.agh.cs;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */

public class InputParser {
    private String fileLocalization;
    private int sectionNumber;
    private int firstArticleNumber;
    private int lastArticleNumber;
    private boolean showSection;

    public InputParser(String[] input) throws IllegalArgumentException{
        if(input.length < 2 || input.length > 3){
            throw new IllegalArgumentException("Not enough/too many arguments. Number of arguments: " + input.length);
        }
        try{
            this.fileLocalization = input[0].substring(0, input[0].indexOf(".txt")+4);
            this.firstArticleNumber = this.sectionNumber = Integer.parseInt(input[1]);
            if(input.length == 2){
                this.showSection = true;
            }else{
                this.showSection = false;
                this.lastArticleNumber = Integer.parseInt(input[2]);
            }
        }catch (NumberFormatException ex){
            throw new IllegalArgumentException("Wrong arguments. Arguments should be directory number1 number2(optional)", ex);
        }
    }

    public String getFileLocalization(){
        return this.fileLocalization;
    }

    public int getSectionNumber(){
        return this.sectionNumber;
    }

    public int getFirstArticleNumber(){
        return this.firstArticleNumber;
    }

    public int getLastArticleNumber(){
        return this.lastArticleNumber;
    }

    public boolean showSection(){
        return this.showSection;
    }
}
