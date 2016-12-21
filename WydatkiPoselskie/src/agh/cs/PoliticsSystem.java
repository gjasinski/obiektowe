package agh.cs;


import agh.cs.rubbish.Parser;

import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by Grzegorz Jasinski on 16.12.16.
 */
public class PoliticsSystem {
    public static void main(String[] args) {
        Parser parser = new Parser();

        try{
            //parser.argumentsAreValid(args);
            /*switch (Integer.parseInt(args[0])){
                case 0: printHelp();
                    break;
                case 1:
                    printPoliticians();
                    break;
                case 2:
                    print(średniej wartości sumy wydatków wszystkich posłów);
                    break;
                case 3:
                    print(posła/posłanki, który wykonał najwięcej podróży zagranicznych);
                    break;
                case 4:
                    print(posła/posłanki, który najdłużej przebywał za granicą);
                    break;
                case 5:
                    print(posła/posłanki, który odbył najdroższą podróż zagraniczną);
                    break;
                case 6:
                    print(listę wszystkich posłów, którzy odwiedzili Włochy);
                    break;
                case 7:
                    print(suma wydatków posła/posłanki o określonym imieniu i nazwisku);
                    break;
                case 8:(wysokości wydatków na 'drobne naprawy i remonty biura poselskiego' określonego posła/posłanki);
                    break;

            }*/
            CreateParliament createParliament = new CreateParliament();
            System.out.print(createParliament.toString());
            createParliament.updatePoliticiansProfile();

        }catch (Exception e){
            System.out.print(e.toString());
            //System.out.print(
                    e.printStackTrace();//);
        }
    }

    private static void printHelp() {
    }


}
