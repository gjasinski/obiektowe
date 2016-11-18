package agh.cs.lab2;

/**
 * Created by gjasinski on 04.11.16.
 */
public enum MapDirection {
    North, South, West, East;

    @Override
    public String toString(){
        if(this.equals(North)) return "Północ";
        if(this.equals(South)) return "Południe";
        if(this.equals(West)) return "Zachód";
        if(this.equals(East)) return "Wschód";
        return "Niezaimplementowany przypadek!";
    }

    public MapDirection next(){
        switch(this){
            case North: return East;
            case East: return South;
            case South: return West;
            case West: return North;
            default: System.out.println("Niezaimplementowany przypadek!");
                return North;
        }
    }

    public MapDirection previous(){
        switch(this){
            case South: return East;
            case West: return South;
            case North: return West;
            case East: return North;
            default: System.out.println("Niezaimplementowany przypadek!");
                return North;
        }
    }
}