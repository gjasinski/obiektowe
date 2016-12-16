package src.agh.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Created by Grzegorz Jasinski on 02.12.16.
 */
public class TextParser {
    private String parsedText;

    public TextParser(String fileDir) throws IOException{
        String constText1 = "Kancelaria Sejmu";
        String constText2 = "2009-11-16";

        Path path = FileSystems.getDefault().getPath(fileDir);
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            this.parsedText = bufferedReader.lines()
                    .filter(s -> s.length() > 1 && !s.contains(constText1) && !s.contains(constText2))
                    .map(this::parseLine)
                    .collect(Collectors.joining());
        }
    }

    public String getParsedText(){
        return this.parsedText;
    }

    private boolean isNumeric(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private String parseLine(String s){
        int length = s.length() - 1;
        if(s.charAt(length) == '-'){
            s = s.substring(0, length);
        }else{
            if((s.charAt(length) == ',' || s.charAt(length) != '.') && !Character.isUpperCase(s.charAt(length))){
                s = s + " ";
            }else{
                s = s + "\n";
            }
        }
        if(s.contains(")") && isNumeric(s.substring(0, s.indexOf(")")))){
            s = "\n  " + s;
        }
        return s;
    }
}
