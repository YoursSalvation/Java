import java.util.Scanner;
/**
 * Класс Самолет - класс для данных о самолете
 * @author Прохоров Д.С.
 */
public class Plane extends FlyApparat {
    /** Поле для хранения типа самолета */
    private String type;
    /** Поле для хранения максимальной скорости самолета */
    private int maxspeed;
    /** Создает самолет с базовым значением*/
    public Plane() {
        super();
        type = "";
        maxspeed = 200;
    }
    /** Создает самолет с заданными значениями */
    public Plane(String firma, int passengers, String types, int speed) {
        super(firma, passengers);
        type = types;
        maxspeed = speed;
    }
    /**
     * Устанавливает значение поля {@link Plane#type}
     * @param types - тип самолета */
    public void setType(String types) {
        type = types;
    }
    /**
     * Устанавливает значение поля {@link Plane#maxspeed}
     * @param speed - максимальная скорость самолета */
    public void setMaxspeed(int speed) {
        maxspeed = speed;
    }
    /**
     * Возвращает значение поля {@link Plane#type}
     * @return строку с типом самолета */
    public String getType() {
        return type;
    }
    /**
     * Возвращает значение поля {@link Plane#maxspeed}
     * @return строку с максимальной скоростью самолета */
    public int getMaxspeed() {
        return maxspeed;
    }
    /**
     * Метод заполнения данных о самолете */
    public void setAllInfo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите фирму самолета: ");
        String nazv = in.next();
        setFirm(nazv);
        System.out.print("Введите максимум пассажиров самолета: ");
        int p = in.nextInt();
        setMaxpassengers(p);
        System.out.print("Введите тип самолета: ");
        type = in.next();
        System.out.print("Введите мощность самолета: ");
        maxspeed = in.nextInt();
    }
    /** @return строку с данными о самолете*/
    public String toString() {
        return "\n\tСамолет"+"\n\t"+"Фирма: "+getFirm()+"\n\t"+"Максимум пассажиров: "+getMaxpassengers()+"\n\t"+"Тип самолета: "+getType()+"\n\t"+"Максимальная скорость: "+getMaxspeed();
    }
}