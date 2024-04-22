import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = sc.nextLine();
        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//----------------------------------------------------------------------------------
//                                метод calc

    public static String calc(String input) throws Exception {
        String[] num = input.split("[+\\-*/@#$%^&]");

        int x;
        int y;
        String oper;
        String result;
        boolean isRoman;

        if (num.length != 2)
            throw new Exception("Должно быть два операнда и один оператор"); //если массив не из 2х чисел, выкидываем исключение
// если из 2х элементов строка выше игнорируется
        oper = detectOperation(input);
// в строке выше ищем знак и записываем его в виде string
        if (oper == null) throw new Exception("Неподдерживаемая операция");
        //если оба числа римские
        if (isRoman(num[0]) && isRoman(num[1])) {
            x = convertToArabian(num[0]);
            y = convertToArabian(num[1]);
            isRoman = true;
        }
//        //если числа арабские
        else if (!isRoman(num[0]) && !isRoman(num[1])) {
            x = Integer.parseInt(num[0]);
            y = Integer.parseInt(num[1]);
            isRoman = false;
        }
//        // если числа не в одном формате
        else {
            throw new Exception("Числа должны быть в одном формате"); // переделать на трикетчь
        }
        if (x > 10 || y > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        // делаем мат. операцию при помощи метода calc и записываем в переменную arabian
        int arabian = calc(x, y, oper);
// выявляем какими были цифры, и при необходимости конвентируем/выбрасываем исключение
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, делаем исключ
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
//            // конвертируем результат из арабского в римский
            result = convertToRoman(arabian);
        } else {
            //конвертируем арабское число в стринг
            result = String.valueOf(arabian);
        }
//
//
        return result;
    }

    // -----------------------------------------------------------------------------------------
    //                               метод detectOperation
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else if (expression.contains("[@#$%^&]")) return null;
        else return null;
    }

    //------------------------------------------------------------------------------------------
    //                                метод isRoman
    public static boolean isRoman(String val) {
        try {
            Roman roman = Roman.valueOf(val);
            for (Roman rom : Roman.values()) {
                if (roman.equals(rom)) {
                    return true;
                }
            }
        } catch (Exception e) {
            //   System.out.println("Числа не римские");
        }
        return false;
    }

    //------------------------------------------------------------------------------------------
    //                             метод convertToArabian
    public static int convertToArabian(String roman) {
        Roman romconv = Roman.valueOf(roman);
        return Integer.parseInt(romconv.getNumber());
    }

    //------------------------------------------------------------------------------------------
    //                                    метод calc
    static int calc(int x, int y, String oper) { //производим мат. операцию выдаем значение в инте
        if (oper.equals("+")) return x + y;
        else if (oper.equals("-")) return x - y;
        else if (oper.equals("*")) return x * y;
        else return x / y;
    }

    // ------------------------------------------------------------------------------------------
    //                                метод convertToRoman
    public static String convertToRoman(int arabian) {
        Roman[] arrayRoman = Roman.values();
        for (int i = 0; i < arrayRoman.length; i++) {
            if (arabian == i) {
                return String.valueOf(arrayRoman[i]);
            }
        }
        return null;
    }

}

