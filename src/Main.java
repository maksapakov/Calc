import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws VariableException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String input = calc(scanner.nextLine());
        System.out.println("Output:");
        System.out.println(input);
        // System.out.println(EnumRoman.values()[1]); возвращаем значение enum через индекс
    }

    public static String calc(String input) throws VariableException {

        String[] arraySplit = input.split(" ");

        int[] arrayRoman = new int[2];
        for (int i1 = 0; i1 < EnumRoman.values().length; i1++) {
            EnumRoman a = EnumRoman.values()[i1];
            for (int i2 = 0; i2 < arraySplit.length; i2++) {
                if (arraySplit[i2].equals(a.name())) {
//                    System.out.println(a.name() + " индекс " + a.ordinal());
                    arrayRoman[i2 / 2] = (a.ordinal() + 1);
                }
            }
        }
        int a = arrayRoman[0];
        int b = arrayRoman[1];
        if (Boolean.logicalOr(a > 10, b > 10)){
            throw new VariableException("Цифры должны лежать в диапазоне от 1 до 10");
        }
//        System.out.println(a + " , " + b);
        //Исключение при нарушении количества операторов
        if (arraySplit.length > 3) {
            throw new VariableException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (arraySplit.length != 3) {
            throw new VariableException("т.к. строка не является математической операцией");
        }

        // Блок вычисления
        int res;
        try {
            if (Boolean.logicalAnd(a > 0, b > 0)) {

                switch (arraySplit[1]) {
                    case "+" -> res = arrayRoman[0] + arrayRoman[1];
                    case "-" -> {
                        res = arrayRoman[0] - arrayRoman[1];
                        if (res < 0) {
                            throw new VariableException("т.к. в римской системе нет отрицательных чисел");                        }
                    }
                    case "/" -> res = arrayRoman[0] / arrayRoman[1];
                    case "*" -> res = arrayRoman[0] * arrayRoman[1];
                    default ->
                            throw new IllegalStateException("Введён неверный арифметический оператор: " + arraySplit[1]);
                }
                input = EnumRoman.values()[res - 1].toString();

            } else if (Boolean.logicalAnd(Integer.parseInt(arraySplit[0]) > 0, Integer.parseInt(arraySplit[2]) > 0)) {

                //Исключение при вводе чисел не входящих в диапазон от 1 до 10
                for (int i = 0; i < 3; i = i + 2) {
                    if (Integer.parseInt(arraySplit[i]) < 1 || Integer.parseInt(arraySplit[i]) > 10) {
                        throw new VariableException("Цифры должны лежать в диапазоне от 1 до 10");
                    }
                }

                switch (arraySplit[1]) {
                    case "+" -> res = Integer.parseInt(arraySplit[0]) + Integer.parseInt(arraySplit[2]);
                    case "-" -> res = Integer.parseInt(arraySplit[0]) - Integer.parseInt(arraySplit[2]);
                    case "/" -> res = Integer.parseInt(arraySplit[0]) / Integer.parseInt(arraySplit[2]);
                    case "*" -> res = Integer.parseInt(arraySplit[0]) * Integer.parseInt(arraySplit[2]);
                    default ->
                            throw new IllegalStateException("Введён неверный арифметический оператор: " + arraySplit[1]);
                }
                input = String.valueOf(res);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("т.к. используются одновременно разные системы счисления");
        }
        return input;
    }
}
