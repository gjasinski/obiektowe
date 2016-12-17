package agh.cs;


import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by Grzegorz Jasinski on 16.12.16.
 */
public class PoliticsSystem {
    public static void main(String[] args) {

        try{
            JsonManager jsonManager = new JsonManager(new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8"));
            LinkedHashMap<Integer, String> linkedHashMap = jsonManager.getPoliticianList();
            Set<Integer> politicianIdList = linkedHashMap.keySet();
            Iterator<Integer> politicianIdListIterator = politicianIdList.iterator();
            while(politicianIdListIterator.hasNext()){
                int key = politicianIdListIterator.next();
                System.out.println(linkedHashMap.get(key)+" - "+key);
            }
        }catch (Exception e){
            System.out.print(e.toString());
        }
    }
}
