package agh.cs.lab2;

/**
 * Created by gjasinski on 04.11.16.
 */
import java.util.ArrayList;
import java.util.stream.Stream;

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

        /*int f, b, l, r;
        f = b = l = r = -1;
        for (int j = 0; j < instructions.length; j++) {
            for (int i = 0; i < instructions[j].length(); i++) {
                if (f <= i) {
                    f = instructions[j].indexOf("forward", i);
                    if (f == i) {
                        result.add(MoveDirection.Forward);
                        i += 6;
                        continue;
                    }
                    f = instructions[j].indexOf("f", i);
                    if (f == i) {
                        result.add(MoveDirection.Forward);
                        continue;
                    }
                }else {
                    if (b <= i) {
                        b = instructions[j].indexOf("backward", i);
                        if (b == i) {
                            result.add(MoveDirection.Backward);
                            i += 7;
                            continue;
                        }
                        b = instructions[j].indexOf("b", i);
                        if (b == i) {
                            result.add(MoveDirection.Backward);
                            continue;
                        }
                    }else{
                        if (l <= i) {
                            l = instructions[j].indexOf("left", i);
                            if (l == i) {
                                result.add(MoveDirection.Left);
                                i += 3;
                                continue;
                            }
                            l = instructions[j].indexOf("l", i);
                            if (l == i) {
                                result.add(MoveDirection.Left);
                                continue;
                            }
                        }else{
                            if (r <= i) {
                                r = instructions[j].indexOf("right", i);
                                if (r == i) {
                                    result.add(MoveDirection.Right);
                                    i += 4;
                                    continue;
                                }
                                r = instructions[j].indexOf("r", i);
                                if (r == i) {
                                    result.add(MoveDirection.Right);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }*/
        MoveDirection[] res = new MoveDirection[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

}
