import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Automata {

    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        int arrLength = 0;
        int intRule = 0;

        while(intRule == 0 || arrLength == 0) {//menu allowing user to enter array length & rule to use
            try {
                if(arrLength == 0) {
                    System.out.print("Please enter an array length: ");
                    arrLength = sc.nextInt();
                    if(arrLength < 0) {
                        System.out.println("Please enter a number greater than 0.");
                        arrLength = 0;
                        continue;
                    }
                }
                System.out.print("Please enter a rule between 0 and 256 inclusive: ");
                intRule = sc.nextInt();
                if(intRule < 0 || intRule > 256) {
                    System.out.println("The rule must be between 0 and 256 inclusive.");
                    intRule = 0;
                }
            } catch (Exception e){
                System.out.println("Invalid input- please try again.");
            }
        }

        char[] curGen = new char[arrLength];

        curGen [curGen.length/2] = '1';//all generations start with array of 0's and 1 in middle
        for(int i = 0; i < curGen.length; i++) {//populating rest of array with 0's
            if(curGen[i] != '1') curGen[i] = '0';
        }

        new Automata(intRule, new StringBuilder(), curGen);
    }

    public Automata(int intRule, StringBuilder fourBitNum, char[] curGen){
        String rule = intToBinary(intRule);

        while(true) {
            int fourBitInt = binaryToInt(fourBitNum.toString());
            int melodicInt = (int)Math.pow(fourBitInt, 2);

            Note.play(60 + (melodicInt % 14));

            if(melodicInt % 10 == fourBitInt % 10) {
                Note.play(44 + ((int) Math.pow(fourBitInt, 3) % 14));
            }

            fourBitNum = rhythmDelay(fourBitNum, fourBitInt, curGen);

            printArray(curGen);

            curGen = generate(rule, curGen);
        }
    }

    public char[] generate(String rule, char[] curGen) {
        char[] nextGen = new char[curGen.length];
        for (int i = 0; i < curGen.length; i++) {
            //setting next state for 0th index
            if (i == 0) {
                nextGen[0] = getNextState(rule, curGen[curGen.length-1], curGen[0], curGen[1]);
            }
            //setting next state for last index
            else if (i == curGen.length - 1) {
                    nextGen[curGen.length-1] = getNextState(rule,curGen[i-1], curGen[i], curGen[0]);
            }
            //setting next state for middle indexes
            else {
                nextGen[i] = getNextState(rule,curGen[i-1], curGen[i], curGen[i+1]);
            }
        }
        return nextGen;
    }

    public char getNextState(String rule, char left, char at, char right) {//neighborhood refers to two cells around given index
        if(left == '1' && at == '1' && right == '1') return rule.charAt(0);
        else if(left == '1' && at == '1' && right == '0') return rule.charAt(1);
        else if(left == '1' && at == '0' && right == '1') return rule.charAt(2);
        else if(left == '1' && at == '0' && right == '0') return rule.charAt(3);
        else if(left == '0' && at == '1' && right == '1') return rule.charAt(4);
        else if(left == '0' && at == '1' && right == '0') return rule.charAt(5);
        else if(left == '0' && at == '0' && right == '1') return rule.charAt(6);
        else return rule.charAt(7);
    }

    public void printArray(char[] curGen) {
        for (char cell : curGen) {
            if (cell == '0') System.out.print(".");
            else System.out.print("#");
            }
        System.out.println();
    }

    public StringBuilder rhythmDelay(StringBuilder fourBitNum, int fourBitInt, char[] curGen) {//uses 4 bit num to calculate delay
        fourBitNum.append(curGen[curGen.length/2]);

        if(fourBitNum.length() > 4) fourBitNum.deleteCharAt(0);//keeps number from becoming larger than 4 bits

        try {
            TimeUnit.MILLISECONDS.sleep(fourBitInt * 50);
        } catch (Exception e) {
            System.out.println(e);
        }

        return fourBitNum;
    }

    public static String intToBinary(int val) {
        String binaryStr = "";
        if(val < 2) binaryStr = ""+val;
        else {
            //converting int to reverse of binary String
            while (val > 0) {
                binaryStr += "" + val % 2;
                val = val / 2;
            }
        }
        //tacking on trailing 0's
        for(int i = 8 - binaryStr.length() ; i > 0; i--) {
            binaryStr += "0";
        }
        //returning reverse of String
        return new StringBuilder(binaryStr).reverse().toString();
    }

    public static int binaryToInt (String binaryStr) {
        char[] numbers = binaryStr.toCharArray();
        int result = 0;
        for(int i=numbers.length - 1; i>=0; i--)
            if(numbers[i]=='1')
                result += Math.pow(2, (numbers.length-i - 1));
        return result;
    }
}
