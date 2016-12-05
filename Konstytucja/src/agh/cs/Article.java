package src.agh.cs;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Article {
    protected int no;
    protected String content;

    public Article(int no, String content){
        this.no = no;
        this.content = content;
    }

    @Override
    public String toString(){
        return "Art. " + this.no + "." + this.content;
    }
}
