public class testFlyCompany {
    public static void main(String[] args) {
        FlyCompany myCompany = new FlyCompany();
        Plane myPlane1 = new Plane("AirBus", 200, "Boeing", 400);
        myCompany.addFly(myPlane1);
        myCompany.addFly(new Plane("Jett", 40, "BusinessJett", 400));
        Helicpoter myHelicopter1 = new Helicpoter("Army", 40, "Army", 100);
        myCompany.addFly(myHelicopter1);
        myCompany.printCompany();
    }
}