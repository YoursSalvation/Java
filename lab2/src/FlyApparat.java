/**
 * Класс Летательный аппарат - базовый класс для объектов воздушного транспорта
 * @author Прохоров Д.С.
 */
public class FlyApparat {
    /**Поле для хранения названия фирмы летательного аппарата */
    private String firm;
    /**Поле для хранения максимально вместимого количества пассажиров */
    private int maxpassengers;
    /**
     * Устанавливает значение поля {@link FlyApparat#firm}
     * @param firma - название фирмы летательного аппарата */
    public void setFirm(String firma) {
        firm = firma;
    }
    /**
     * Устанавливает значение поля {@link FlyApparat#maxpassengers}
     * @param passengers - значение максимально вместимого количества пассажиров*/
    public void setMaxpassengers(int passengers) {
        maxpassengers = passengers;
    }
    /**
     * Возвращает значение поля {@link FlyApparat#firm}
     * @return строку с названием фирмы летательного аппарата */
    public String getFirm() {
        return firm;
    }
    /**
     * Возвращает значение поля {@link FlyApparat#maxpassengers}
     * @return строку с количеством максимально вместимого количества пассажиров */
    public int getMaxpassengers() {
        return maxpassengers;
    }
    /**
     * Создает летательный аппарат с фирмой "Неизвестный" и максимальным количеством пассажиров, равным 0*/
    public FlyApparat() {
        firm = "Неизвестный";
        maxpassengers = 0;
    }
    /**
     * Создает автомобиль с заданным значением фирмы и максимальным количеством пассажиров
     * @param firma - название фирмы летательного аппарата
     * @param passengers - количество вместимых пассажиров летательного аппарата*/
    public FlyApparat(String firma, int passengers) {
        firm = firma;
        maxpassengers = passengers;
    }
}