import java.util.Scanner;
/**
 * Класс Вертолет - класс для данных о вертолете
 * @author Прохоров Д.С.
 */
public class Helicpoter extends FlyApparat {
    /** Поле для хранения типа вертолета */
    private String type;
    /** Поле для хранения мощности вертолета */
    private int power;
    /** Создает вертолет с базовым значением*/
    public Helicpoter() {
        super();
        type = "";
        power = 0;
    }
    /** Создает вертолет с заданными значениями */
    public Helicpoter(String firma, int passengers, String types, int powers) {
        super(firma, passengers);
        type = types;
        power = powers;
    }
    /**
     * Устанавливает значение поля {@link Helicpoter#type}
     * @param types - тип вертолета */
    public void setType(String types) {
        type = types;
    }
    /**
     * Устанавливает значение поля {@link Helicpoter#power}
     * @param powers - мощность вертолета */
    public void setPower(int powers) {
        power = powers;
    }
    /**
     * Возвращает значение поля {@link Helicpoter#type}
     * @return строку с типом вертолета */
    public String getType() {
        return type;
    }
    /**
     * Возвращает значение поля {@link Helicpoter#power}
     * @return строку с мощностью вертолета */
    public int getPower() {
        return power;
    }
    /**
     * Метод заполнения данных о вертолете */
    public void setAllInfo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите фирму вертолета: ");
        String nazv = in.next();
        setFirm(nazv);
        System.out.print("Введите максимум пассажиров в вертолете: ");
        int p = in.nextInt();
        setMaxpassengers(p);
        System.out.print("Введите тип вертолета: ");
        type = in.next();
        System.out.print("Введите мощность вертолета: ");
        power = in.nextInt();
    }
    /** @return строку с данными о вертолете */
    public String toString() {
        return "\n\tВертолет"+"\n\t"+"Фирма: "+getFirm()+"\n\t"+"Максимум пассажиров: "+getMaxpassengers()+"\n\t"+"Тип вертолета: "+getType()+"\n\t"+"Мощность вертолета: "+getPower();
    }
}