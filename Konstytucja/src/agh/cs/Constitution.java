package src.agh.cs;


import java.util.ArrayList;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class Constitution {
    private String title;
    private Preamble preamble;
    private ArrayList<Section> sectionList;
    private ArrayList<Article> articlesList;
    private ArrayList<Section> articleInSectionList;

    public Constitution(String title, Preamble preamble, ArrayList<Section> sectionList){
        this.title = title;
        this.preamble = preamble;
        this.sectionList = sectionList;
        int i = 1;
        int lastArticleNo;
        ArrayList<Article> articlesList = new ArrayList<>();
        ArrayList<Section> articleInSectionList = new ArrayList<>();
        for(Section section : this.sectionList){
            lastArticleNo = section.getLastArticleNo();
            while(i <= lastArticleNo){
                articlesList.add(section.getArticleByNo(i));
                articleInSectionList.add(section);
                i++;
            }
        }
        this.articlesList = articlesList;
        this.articleInSectionList = articleInSectionList;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getGetTitleOfConstitution());
        stringBuilder.append("\n");
        stringBuilder.append(this.preamble.toString());
        for(Section section : this.sectionList){
            stringBuilder.append(section.toString());
            }
        return stringBuilder.toString();

    }

    public String toString(int section){
        if(section < 0 || section > this.sectionList.size()){
            throw new IndexOutOfBoundsException("Section number is not correct. Type section from 0 - " + this.sectionList.size());
        }

        if(section == 0) return this.title + "\n" + this.preamble.toString();
        else return this.sectionList.get(section - 1).toString();
    }

    public String toString(int firstArticleNo, int lastArticleNo){
        if(firstArticleNo < 1 || lastArticleNo > this.articlesList.size() || firstArticleNo > lastArticleNo){
            throw new IndexOutOfBoundsException("Article numbers are not correct. Type articles from 1 - " + this.articlesList.size() + " and firstArticle < lastArticle.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.articleInSectionList.get(firstArticleNo - 1).getTitle());
        stringBuilder.append("\n");
        Section previousSection = this.articleInSectionList.get(firstArticleNo - 1);
        for(int i = firstArticleNo - 1; i < lastArticleNo; i++){
            if(!previousSection.isEqual(this.articleInSectionList.get(i))){
                previousSection = this.articleInSectionList.get(i);
                stringBuilder.append(("\n"));
                stringBuilder.append(previousSection.getTitle());
                stringBuilder.append("\n");
            }
            stringBuilder.append(this.articlesList.get(i).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getGetTitleOfConstitution() {
        return this.title;
    }
}


