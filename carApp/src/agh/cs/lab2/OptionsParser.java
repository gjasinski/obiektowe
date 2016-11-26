package agh.cs.lab2;

/**
 * Created by gjasinski on 04.11.16.
 */
import java.util.ArrayList;

public class OptionsParser {

    public static MoveDirection[] parse(String[] instructionsFromUser) {
        ArrayList<MoveDirection> result = new ArrayList<MoveDirection>();
        String[] instructions;
        if(instructionsFromUser.length == 1){
            instructions = instructionsFromUser[0].split(" ");
        }
        else{
            instructions = instructionsFromUser;
        }
        for(int i = 0; i < instructions.length; i++){
            switch (instructions[i]){
                case "f":
                case "forward": result.add(MoveDirection.Forward);
                    break;
                case "b":
                case "backward": result.add(MoveDirection.Backward);
                    break;
                case "l":
                case "left": result.add(MoveDirection.Left);
                    break;
                case "r":
                case "right": result.add(MoveDirection.Right);
                    break;
                case " ": break;
                default: throw new IllegalArgumentException(instructions[i] + " is not legal move specification");
            }
        }

        MoveDirection[] res = new MoveDirection[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

}
