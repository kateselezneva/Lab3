package canteen;

public class Canteen{
    private Meal [] menu;
    private int n;


    public Canteen() {
        menu = new Meal [300];
        for (int i = 0; i<300; i++){
            menu[i] = new Meal("empty",0);
        }
        n = 0;
    }
    private Meal [] sortAlphabetical(){ //сортировка блюд в алфавитном порядке
        String namex = menu[n].getName();
        int ind = -1;
        boolean f = false;
        for(int i = 0; i<n; i++){ //поиск индекса, на который надо вставить новый элемент
            if (f)
                break;
            String namei = menu[i].getName();//получаем название блюда с индексом i
            //если название блюда №i - часть названия добавленного блюдп
            if(namex.length()>namei.length() && namex.substring(0,namei.length()).equals(namei)){
                ind = i+1;
            }
            else
                //если название добавленного блюда - часть названия  блюда №i
            if (namex.length()<=namei.length() && namei.substring(0,namex.length()).equals(namex))
                ind = i;
            else {
                //иначе, когда названия не являются частями друг друга
                int k = Math.min(namex.length(),namei.length());
                for (int j = 0; j<k; j++) //ищем на какой букве слова начинают различаться
                    if (namex.charAt(j)>namei.charAt(j))
                        break;
                    else
                    if (namex.charAt(j)<namei.charAt(j)){
                        ind = i;
                        f = true;
                        break;
                    }
            }
        }

        if (ind==-1) //если не нашли индекс, значит массив уже отсортирован
            return menu;
        else
        {
            Meal[] menuSorted = new Meal[300]; //menuSorted - новый, отсортированный по алфавиту список блюд
            for (int i = 0; i < ind; i++)
                menuSorted[i] = menu[i];
            menuSorted[ind] = menu[n];
            menu[n] = menu[n+1];
            for (int i = ind + 1; i < 300; i++)
                menuSorted[i] = menu[i - 1];


            return menuSorted;
        }

    }

    public void addMeal(Meal x){ //добавление блюда
        if (x.getPrice()>0){ //если цена > 0
            if (menu[0].getName().equals("empty")){ //если меню пустое, то добавляем на первое место
                menu[0] = x;
                n++;
            }
            else{ //если список не пустой
                boolean f = true;
                for (int i = 0; i<n; i++) //проверяем, есть ли блюдо с таким названием
                    if (menu[i].getName().equals(x.getName())){ //если есть, то не добавляем
                        f = false;
                        break;
                    }
                if (f){ //такого блюда еще нет, добавляем
                    menu[n] = x; //добавляем в конец
                    menu = sortAlphabetical(); //сортируем по алфавиту
                    n++; //увеличиваем длину меню
                }
            }
        }
    }

    public void printMeals(){ //выводит названия и цены блюд в алфавитном порядке
        System.out.println("Меню");
        for (int i = 0; i<n; i++){
            System.out.printf("%-15s  %7.2f",menu[i].getName(),menu[i].getPrice());
            System.out.println();

        }
        System.out.println();
    }

    public void printAvailable(){ //выводит доступные блюда (те у которых >0 порций)
        System.out.println("Доступные блюда");
        for (int i = 0; i<n; i++){
            //если у блюда не 0 порций
            if (menu[i].getPortions()!=0) {
                System.out.printf("%-15s  %7d", menu[i].getName(), menu[i].getPortions());
                System.out.println();
            }

        }
        System.out.println();
    }

    public void cooked(int n, int k){ //увеличивает кол-во порций блюда n на k
        menu[n-1].setPortions(menu[n-1].getPortions()+k);
    }

    public void changePrice(int n, int m){ //меняет цену блюда n на m, если m> актуальной цены
        if (menu[n-1].getPrice()<m)
            menu[n-1].setPrice(m);
    }

    public double buyMeal(int n){ //покупка блюда
        if (menu[n-1].getPortions()>0){ //если оно доступно (больше 0 порций)
            menu[n-1].setPortions(menu[n-1].getPortions()-1); //уменьшаем кол-во порций на 1
            return menu[n-1].getPrice(); //возвращает стоимость
        }
        else
            return -1; //если не доступно возваращает -1
    }

    public double buyMeal(int [] n){ //покупка блюд по массиву номеров
        double s = 0;
        for (int i = 0; i< n.length; i++) { //для каждого номера блюда
            if (menu[n[i] - 1].getPortions() > 0) { //если блюдо доступно
                menu[n[i] - 1].setPortions(menu[n[i] - 1].getPortions() - 1);  //уменьшаем кол-во порций на 1
                s += menu[n[i] - 1].getPrice(); //увеличиваем сумму цен
            }
        }
        if (s>0) //если сумма больше нуля, значит есть доступные блюда, возвращаем s
            return s;
        else
            return -1;

    }

    private Meal [] sortPrices(){ //сортировка по ценам
        Meal[] a = new Meal[n];//новый массив
        for (int i = 0; i<n; i++)
            a[i] = menu[i];
        Meal z;
        for (int i = 1; i<n; i++){ //сортировка пузырьком
            for (int j=0; j<n-i; j++){
                if (a[j].getPrice()>a[j+1].getPrice()){
                    z = a[j];
                    a[j] = a[j+1];
                    a[j+1] = z;
                }

            }
        }
        return a;
    }

    public int amountMeals(double sum){ //кол-во блюд которые можно купить на заданную сумму
        Meal[] a = sortPrices(); // массив с блюдами, отсортированными по возрастанию цены
        double s = 0;
        int k = 0;
        for (int i = 0; i<n; i++){ //в сортированном массиве складываем цены, пока не выйдем за предел
            if (a[i].getPortions()>0){ //если блюдо доступно
                s+=a[i].getPrice(); //добавляем к сумме его цену
                if (s<sum){ //если не вышли за предел
                    k++;} //увеличиваем кол-во блюд которые можно купить
                else
                    break;

            }

        }
        return k;
    }

    public void mostExpensive(){ //выводит 3 самых дорогих доступных блюда
        System.out.println("Самые дорогие блюда");
        Meal[] a = sortPrices();

        int k = 0; //кол-во выведенных блюд
        for (int i =n-1 ; i>=0; i--){
            if (a[i].getPortions()>0){ //если блюдо доступно, выводим его название и цену
                System.out.printf("%-15s  %7.2f",a[i].getName(),a[i].getPrice());
                System.out.println();
                k++;
            }
            if (k==3) //если вывели три блюда
                break;
        }
        System.out.println();
    }

    public void mostCheap(){  //выводит 3 самых дешевых доступных блюда
        System.out.println("Самые дешевые блюда");
        Meal[] a = sortPrices();
        int k = 0; //кол-во выведенных блюд
        for (int i =0 ; i<n; i++){
            if (a[i].getPortions()>0){ //если блюдо доступно, выводим его название и цену
                System.out.printf("%-15s  %7.2f",a[i].getName(),a[i].getPrice());
                System.out.println();
                k++;
            }
            if (k==3) //если вывели три блюда
                break;
        }
        System.out.println();
    }

    public void deleteMeal(int x){ //удаление блюда
        for (int i = x-1; i<n; i++) //с места x сдвигаем значения на 1 влево
            menu[i] = menu[i+1];
        n--; //уменьшаем кол-во блюд в меню
    }

    public void deleteIfEmpty(int x){ //удаления блюда, если у него 0 порций
        if (menu[x-1].getPortions()==0){ //если ноль порций
            for (int i = x-1; i<n; i++) //с места x сдвигаем значения на 1 влево
                menu[i] = menu[i+1];
            n--;
        }
    }
}