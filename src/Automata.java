import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Automata {

    private String rule;
    private String[] curGen = new String[171];

    public static void main (String args[]) {
        new Automata(30);
    }

    public Automata(int rule){
        this.rule = getBinaryString(rule);

        curGen [curGen.length/2] = "1";
        for(int i = 0; i < curGen.length; i++) {
            if(curGen[i] == null) curGen[i] = "0";
        }

        generate();
    }

    public void generate() {
        String binaryNum = "";
        while(true) {
            String[] nextGen = new String[curGen.length];

            for (int i = 0; i < curGen.length; i++) {
                //setting next state for 0th index
                if (i == 0) {
                    nextGen[0] = getNextState(curGen[curGen.length-1] + curGen[0] + curGen[1]);
                }
                //setting next state for last index
                else if (i == curGen.length - 1) {
                    nextGen[curGen.length-1] = getNextState(curGen[i-1] + curGen[i] + curGen[0]);
                }
                //setting next state for middle indexes
                else {
                    nextGen[i] = getNextState(curGen[i-1] + curGen[i] + curGen[i+1]);
                }
            }

            binaryNum += curGen[curGen.length/2];
            if(binaryNum.length() == 4) binaryNum = binaryNum.substring(1);
            System.out.println(Integer.parseInt(binaryNum, 2) * 250);

            try {
                TimeUnit.MILLISECONDS.sleep(Integer.parseInt(binaryNum, 2) * 250);
            } catch (Exception e) {
                System.out.println(e);
            }
            /*
            for (String cell : curGen) {
                if(cell != null) {
                    if (cell.equals("0")) System.out.print(".");
                    else System.out.print("#");
                }
            }
            System.out.println();
            */
            curGen = nextGen;
        }
    }

    public String getNextState(String neighborhood) {
        if(neighborhood.equals("111")) return ""+rule.charAt(0);
        else if(neighborhood.equals("110")) return ""+rule.charAt(1);
        else if(neighborhood.equals("101")) return ""+rule.charAt(2);
        else if(neighborhood.equals("100")) return ""+rule.charAt(3);
        else if(neighborhood.equals("011")) return ""+rule.charAt(4);
        else if(neighborhood.equals("010")) return ""+rule.charAt(5);
        else if(neighborhood.equals("001")) return ""+rule.charAt(6);
        else return ""+rule.charAt(7);
    }

    public static String getBinaryString(int val) {
        if(val < 2) return ""+val;
        String binary = "";
        //converting int to reverse of binary String
        while(val > 0) {
            binary += "" + val % 2;
            val = val / 2;
        }
        //tacking on trailing 0's
        for(int i = 8 - binary.length() ; i > 0; i--) {
            binary += "0";
        }
        //returning reverse of String
        return new StringBuilder(binary).reverse().toString();
    }
}
