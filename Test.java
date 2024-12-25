import canteen.Canteen;
import canteen.Meal;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class Test {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        //создаем меню
        Canteen rus = new Canteen();
        //добавляем блюда
        rus.addMeal(new Meal("Meat",400));
        rus.addMeal(new Meal ("Apple",35));
        rus.addMeal(new Meal ("Apples",50));
        rus.addMeal(new Meal ("Yogurt",100));
        rus.addMeal(new Meal ("Bread",80));
        rus.addMeal(new Meal("Cookies",90));
        //проверяем добавит ли уже существующее блюдо
        rus.addMeal(new Meal("Cookies",190));
        //проверяем добавит ли блюдо с ценой 0
        rus.addMeal(new Meal("Water",0));

        //распечатываем меню в алфавитном порядке
        rus.printMeals();

// добавляем приготовленные порции блюд
        rus.cooked(2,2);
        rus.cooked(5,6);
        rus.cooked(4,4);

        //выводим доступные блюда
        rus.printAvailable();

        //покупка
        out.println("покупка");
        out.println(rus.buyMeal(2)); //есть в наличии
        out.println(rus.buyMeal(1)); //нет в наличии


        out.println("покупка нескольких блюд");
        //покупка нескольких блюд
        out.println(rus.buyMeal(new int[]{2, 4, 5})); //есть в наличии
        out.println(rus.buyMeal(new int[]{1,3})); //нет в наличии

        //выводим доступные блюда
        rus.printAvailable();

        //кол-во блюд из доступных по сумме
        out.println("кол-во блюд из доступных по сумме");
        out.println(rus.amountMeals(50));
        out.println(rus.amountMeals(150));


        rus.cooked(2,2);
        rus.cooked(3,7);

        //3 самых дорогих из доступных
        rus.mostExpensive();

        //3 самых дешевых из доступных
        rus.mostCheap();

        //меняем цену
        rus.changePrice(2,60); //увеличили цену
        rus.changePrice(3,50); //уменьшили
        System.out.println("Изменили цену");
        rus.printMeals();

        //удаляяем блюдо
        rus.deleteMeal(2);
        System.out.println("Удалили блюдо");
        rus.printMeals();

        //удаляем блюдо, если нет в наличии
        rus.deleteIfEmpty(1);//нет в наличии
        rus.deleteIfEmpty(3);//есть в наличии
        System.out.println("Удалили блюдо, если его нет в наличии");
        rus.printMeals();


    }
}
