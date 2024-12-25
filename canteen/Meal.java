package canteen;

public class Meal{ //класс блюдо
    private String name;
    private double price;
    private int portions;

    public Meal(String name, double price) { //конструктор
        this.name = name;
        this.price = price;
        portions = 0;

    }
    @Override
    public String toString() {
        return name+" "+price+"руб "+ portions+"шт";
    }
    //геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
}