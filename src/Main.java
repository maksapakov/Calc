import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws VariableException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String input = calc(scanner.nextLine());
        System.out.println("Output:");
        System.out.println(input);
    }
    public static String calc(String input) throws VariableException {

        String[] arraySplit = input.split(" ");
// Блок исключений
        //Исключение при нарушении количества операторов
        if (arraySplit.length > 3) {
            throw new VariableException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (arraySplit.length != 3) {
            throw new VariableException("т.к. строка не является математической операцией");
        }
        //Исключение при вводе чисел не входящих в диапазон от 1 до 10
        for (int i = 0; i < 3; i = i +2) {
            if (Integer.parseInt(arraySplit[i]) < 1 || Integer.parseInt(arraySplit[i]) > 10) {
                throw new VariableException("Вводимые числа должны лежать в диапазоне от 1 до 10");
            }
        }
        // Исключение при разных системах счисления


// Блок вычисления
        switch (arraySplit[1]) {
            case "+" -> {
                int res = Integer.parseInt(arraySplit[0]) + Integer.parseInt(arraySplit[2]);
                input = String.valueOf(res);
            }
            case "-" -> {
                int res = Integer.parseInt(arraySplit[0]) - Integer.parseInt(arraySplit[2]);
                input = String.valueOf(res);
            }
            case "/" -> {
                int res = Integer.parseInt(arraySplit[0]) / Integer.parseInt(arraySplit[2]);
                input = String.valueOf(res);
            }
            case "*" -> {
                int res = Integer.parseInt(arraySplit[0]) * Integer.parseInt(arraySplit[2]);
                input = String.valueOf(res);
            }
        }
        //
        return input;
    }
}
