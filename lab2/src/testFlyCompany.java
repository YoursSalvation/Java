import java.util.Scanner;

public class testFlyCompany {
    public static void main(String[] args) {
        FlyCompany myCompany = new FlyCompany();
        Plane myPlane1 = new Plane("AirBus", 200, "Boeing", 400);
        myCompany.addFly(myPlane1);
        myCompany.addFly(new Plane("Jett", 40, "BusinessJett", 400));
        Helicopter myHelicopter1 = new Helicopter("Army", 40, "Army", 100);
        myCompany.addFly(myHelicopter1);

//        Plane myPlane2 = new Plane();
//        Scanner sca = new Scanner(System.in);
//        System.out.println("Введите фирму ");
//        String nazv = sca.next();
//        myPlane2.setFirm(nazv);
//        System.out.println("Введите максимально вместимое количество пассажиров ");
//        int pas = sca.nextInt();
//        myPlane2.setMaxpassengers(pas);
//        System.out.println("Введите тип ");
//        nazv = sca.next();
//        myPlane2.setType(nazv);
//        System.out.println("Введите максимальную скорость");
//        int speed = sca.nextInt();
//        myPlane2.setMaxspeed(speed);
//        myCompany.addFly(myPlane2);
//
//        Helicopter myHelicopter = new Helicopter();
//        System.out.println("Введите фирму ");
//        nazv = sca.next();
//        myHelicopter.setFirm(nazv);
//        System.out.println("Введите максимально вместимое количество пассажиров ");
//        pas = sca.nextInt();
//        myHelicopter.setMaxpassengers(pas);
//        System.out.println("Введите тип ");
//        nazv = sca.next();
//        myHelicopter.setType(nazv);
//        System.out.println("Введите мощность");
//        int power = sca.nextInt();
//        myHelicopter.setPower(power);
//        myCompany.addFly(myHelicopter);

        myCompany.printCompany();
    }
}