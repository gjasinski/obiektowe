package src.agh.cs;

import java.util.ArrayList;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Section extends Article {
    private int firstArticleNo;
    private ArrayList<Article> articleList;

    public Section(int no, String content, ArrayList<Article> articleList, int firstArticleNo){
        super(no, content);
        this.articleList = articleList;
        this.firstArticleNo = firstArticleNo;
    }

    private int getArticleIndexByNo(int articleNo){
        return articleNo - this.firstArticleNo;
    }


    public int getLastArticleNo() {
        return this.firstArticleNo + this.articleList.size() - 1;
    }


    public Article getArticleByNo(int articleID) {
        return this.articleList.get(getArticleIndexByNo(articleID));
    }

    public String getTitle(){
        return this.content;
    }

    public boolean isEqual(Section section){
        return this.no == section.no;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(this.content);
        for(Article article: articleList){
            stringBuilder.append("\n");
            stringBuilder.append(article.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toString(int firstArticleNo, int lastArticleNo){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.content);
        for(int articleNo = getArticleIndexByNo(firstArticleNo); articleNo < getArticleIndexByNo(lastArticleNo); articleNo++){
            {
                stringBuilder.append(this.articleList.get(articleNo).toString());
            }
        }
        return stringBuilder.toString();
    }
}
