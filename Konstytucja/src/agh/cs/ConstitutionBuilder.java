package src.agh.cs;

import java.util.ArrayList;

/**
 * Created by Grzegorz Jasinski on 03.12.16.
 */
public class ConstitutionBuilder {
    private String constitutionText;

    public ConstitutionBuilder(String constitutionText){
        this.constitutionText = constitutionText + "Rozdział";
    }

    public Constitution buildConstitution(){
        String section = getNextSection();
        String titleOfConstitution = section.substring(0, section.indexOf("r.") + 2);
        section = section.substring(titleOfConstitution.length() + 1);
        section = section.replace('\n', ' ');
        section = section.substring(0, section.length() - 1);
        Preamble preamble = new Preamble(section);
        int articleNo = 1;
        int sectionNo = 1;
        int firstArticleNo;

        ArrayList<Section> sectionList = new ArrayList<>();
        while (!isTextParsed(this.constitutionText)){
            section = getNextSection() + "Art.";
            String titleOfSection = section.substring(0, section.indexOf("Art.") - 1);
            section = removeXChars(section, titleOfSection.length() + 1);

            ArrayList<Article> articleList = new ArrayList<>();
            firstArticleNo = articleNo;
            while(!isTextParsed(section)){
                section = removeXChars(section, section.indexOf("\n"));
                articleList.add(new Article(articleNo, section.substring(0, section.indexOf("Art.")-1)));
                section = removeXChars(section, section.indexOf("Art."));
                articleNo++;
            }
            sectionList.add(new Section(sectionNo, titleOfSection, articleList, firstArticleNo));
            sectionNo++;
        }

        Constitution constitution = new Constitution(titleOfConstitution, preamble, sectionList);
        return constitution;
    }

    private boolean isTextParsed(String text){
        return text.length() < 10;
    }

    private String getNextSection(){
        int nextSectionIndexOf = this.constitutionText.indexOf("Rozdział", 10);
        String nextSection = this.constitutionText.substring(0, nextSectionIndexOf);
        this.constitutionText = this.constitutionText.substring(nextSectionIndexOf, this.constitutionText.length());
        return nextSection;
    }

    private String removeXChars(String text, int x){
        return text.substring(x);
    }

}
