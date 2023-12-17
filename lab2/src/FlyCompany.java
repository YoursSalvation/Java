import java.util.ArrayList;
/**
 * Класс Авиакомпания - класс для данных о летательных аппаратах
 * @author Прохоров Д.С.
 */
public class FlyCompany {
    /** Создает массив с летательными аппаратами */
    private ArrayList<FlyApparat> masFly = new ArrayList<FlyApparat>();
    /** Метод для добавления летательного аппарата в авиакомпанию
     * @param f - летательный аппарат */
    public void addFly(FlyApparat f) {
        masFly.add(f);
    }
    /** Пустой конструктор*/
    public FlyCompany(){

    }
    /** Проверка есть ли летательный аппарат в авиакомпании
     * @return True or False */
    public Boolean findFly(FlyApparat f) {
        return masFly.contains(f);
    }
    /** Устанавливает значение поля {@link FlyCompany#masFly}
     * @param n - летательный аппарат */
    public FlyCompany(ArrayList<FlyApparat> n) {
        masFly = n;
    }
    /** Выводит список летательных аппаратов в авиакомпании*/
    public void printCompany() {
        System.out.println("В авиакомпании: ");
        int k = 0;
        int n = 0;
        for (FlyApparat f:masFly) {
            System.out.println("\t"+f.toString());
        }
        for (FlyApparat f:masFly) {
            if (f instanceof Plane)
                k++;
            if (f instanceof Helicopter)
                n++;
        }
        System.out.println("\t\nКоличество самолетов: " + k + " Количество вертолетов: " + n);
    }
}