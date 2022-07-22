import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String primer = null;
        Calcul subtract = new Calcul();
        primer = in.nextLine();
        System.out.println(subtract.calc(primer));
        System.exit(0);
    }
}
class Calcul {
    public String calc(String input) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String s1 = ""; String s2 = ""; char operator = '+';
        String[] massiv = new String[0];
        try {
            massiv = input.split(" ");
            s1 = massiv[0];
            operator = massiv[1].charAt(0);
            s2 = massiv[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Строка не является выражением");
            System.exit(0);
        }
        int c = massiv.length;
        if ( c >= 4){
            System.err.println("Формат математической операции не соответствует заданию");
            System.exit(0);
        }
        int res = 0;
        boolean aIzInt;
        boolean bIzInt;
        int a = 0;
        int b = 0;
        String result = null;
        String result1 = null;
        try {
            a = Integer.parseInt(s1);
            aIzInt = true;
        } catch (NumberFormatException e) {
            aIzInt = false;
        }
        try {
            b = Integer.parseInt(s2);
            bIzInt = true;
        } catch (NumberFormatException e) {
            bIzInt = false;
        }
        if ((aIzInt || bIzInt) == false) {
            boolean own = RomanNum.proverka(s1);
            boolean own1 = RomanNum.proverka(s2);
            if ((own1 && own) == true) {
                RomanNum num = RomanNum.valueOf(s1);
                RomanNum num1 = RomanNum.valueOf(s2);
                a = Integer.parseInt(num.getArabNumber());
                b = Integer.parseInt(num1.getArabNumber());
            } else {
                System.err.println("Введенное число не находится в дипазоне от 1 до 10");
                System.exit(0);
            }
        }
        if (aIzInt == false && bIzInt == true) {
            System.err.println("Используются одновременно разные системы отсчета");
            System.exit(0);
        } else if (aIzInt == true && bIzInt == false) {
            System.err.println("Используются одновременно разные системы отсчета");
            System.exit(0);
        }
        if ((aIzInt && bIzInt) == true) {
            a = Integer.parseInt(s1);
            b = Integer.parseInt(s2);
        }
        if (a > 10 || a < 0) {
            System.err.println("");
            System.exit(0);
        } else if (b > 10 || b <0) {
            System.err.println("Введенное число не находится в дипазоне от 1 до 10");
            System.exit(0);
        }
            switch (operator) {
                case '+' -> res = (a + b);
                case '-' -> res = (a - b);
                case '*' -> res = (a * b);
                case '/' -> res = (int) (a / b);
        }
        if ((aIzInt || bIzInt) == false) {
            result1 = Translate.arabToRom(res);
            return result1;
        } else {
            result = Integer.toString(res);
            return result;
        }
    }
}

class  Translate{
    static String arabToRom (int clef) {
        String firstPath = "";
        String secPath = "";
        int i1 =0; int i2 = 0;
            if (clef <= 0) {
                System.err.println("В римской системе нет отрицательных чисел");
                System.exit(0);
            }
            i1 = clef / 10;
            switch (i1) {
                case 0 -> firstPath = "";
                case 1 -> firstPath = "X";
                case 2 -> firstPath = "XX";
                case 3 -> firstPath = "XXX";
                case 4 -> firstPath = "XL";
                case 5 -> firstPath = "L";
                case 6 -> firstPath = "LX";
                case 7 -> firstPath = "LXX";
                case 8 -> firstPath = "LXXX";
                case 9 -> firstPath = "XC";
                case 10 -> firstPath = "C";
            }
            i2 = clef%10;
            switch (i2) {
                case 0 -> secPath = "";
                case 1 -> secPath = "I";
                case 2 -> secPath = "II";
                case 3 -> secPath = "III";
                case 4 -> secPath = "IV";
                case 5 -> secPath = "V";
                case 6 -> secPath = "VI";
                case 7 -> secPath = "VII";
                case 8 -> secPath = "VIII";
                case 9 -> secPath = "IX";
            }

        return firstPath + secPath;
    }
}
enum RomanNum {
    I("1"), II("2"), III("3"), IV("4"),
    V("5"), VI("6"), VII("7"), VIII("8"),
    IX("9"), X("10");
    private String getArabNumber;

    RomanNum(String getArabNumber) {
        this.getArabNumber = getArabNumber;
    }

    String getArabNumber() {
        return getArabNumber;
    }
    static boolean proverka (String key){
        RomanNum[] variant = values();
        boolean found = false;
        for (RomanNum d : variant) {
            if(d.toString().equals(key)) {
                found = true;
                break;
            }
        }
        return found;
    }
}