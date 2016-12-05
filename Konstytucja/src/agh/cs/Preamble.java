package src.agh.cs;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Preamble extends Article {
    public Preamble(String content){
        super(0, content);
    }

    @Override
    public String toString(){
        return this.content + "\n";
    }
}
