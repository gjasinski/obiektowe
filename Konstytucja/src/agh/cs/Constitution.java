package agh.cs;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Constitution {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser(args);
        System.out.println(args.length);
        System.out.println(inputParser.getFileLocalization());
        System.out.println(inputParser.getFirstArticleNumber());
        System.out.println(inputParser.getLastArticleNumber());
        System.out.println(inputParser.getSectionNumber());
        if(inputParser.showSection()) System.out.println("TROLOLO");else System.out.print("NIEtrorolo");
    }
}
