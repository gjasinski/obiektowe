package main.java.edu.gjasinski.wydatkiposelskie;


import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;

public class DownloadManager {
    public DownloadManager(){}


    public String downloadJson(URL url) throws IOException{
        try(InputStream inputStream = url.openStream()) {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
                return stringBuilder.toString();
            }
        }catch (UnknownHostException e){
            throw new IOException("There is problem with internet connection.", e);
        }
        catch (FileNotFoundException e){
            throw new IOException("There is no JSON with this URL" + url.toString(), e);
        }
    }

    public String downloadPoliticianTravelsAndExpensesJson(int politicianId) throws IOException{
        return downloadJson(new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + politicianId + ".json?layers[]=wyjazdy&layers[]=wydatki"));
    }
}
