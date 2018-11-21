import java.awt.*;
import java.util.List;

public class CarFerry implements IMovable, ILoadable {

    private final CarTransport parent;

    public CarFerry(Color color, int enginePower, String modelName, Car.Direction dir, int x, int y, int maxCars) {
        parent = new CarTransport(color, enginePower, 0, modelName, dir, x, y, maxCars);
    }

    public CarFerry() {
        this(Color.YELLOW, 50, "CarFerry", Car.Direction.NORTH, 100, 100, 20);
    }


    @Override
    public void extendFlak() {
        parent.extendFlak();
    }

    @Override
    public void retractFlak() {
        parent.retractFlak();
    }

    @Override
    public boolean loadCar(Car c) {
        if (!parent.isMoving() && parent.isFlakExtended() && parent.isClose(c) && parent.isRoomForCar() && c.getSize() == Car.Size.SMALL) {
            parent.getLoadedCars().add(c);
            c.setX(parent.getX());
            c.setY(parent.getY());
            return true;
        }
        return false;
    }

    /**
     * Unloads car according to first in - first out principle.
     *
     * @return The unloaded car.
     */
    @Override
    public Car unloadCar() {
        if (!parent.isMoving() && parent.isFlakExtended()) {
            Car c = parent.getLoadedCars().get(0);
            c.setX(parent.getX() + 10);
            c.setY(parent.getY() + 10);
            parent.getLoadedCars().remove(0);
            return c;
        }
        return null;
    }

    public void unloadAll() {
        if (!parent.isMoving() && parent.isFlakExtended()) {
            for (Car c: parent.getLoadedCars()) {
                this.unloadCar();
            }
        }

    }

    public List<Car> getLoadedCars() {
        return parent.getLoadedCars();
    }

    @Override
    public void move() {
        parent.move();

    }

    @Override
    public void turnLeft() {
        parent.move();

    }


    @Override
    public void turnRight() {

        parent.move();
    }

    @Override
    public double speedFactor() {
        return parent.speedFactor();
    }

    @Override
    public double getCurrentSpeed() {
        return parent.getCurrentSpeed();
    }

    @Override
    public void setCurrentSpeed(double speed) {
        parent.setCurrentSpeed(speed);

    }

    @Override
    public void setDirection(Car.Direction direction) {
        parent.setDirection(direction);

    }

    @Override
    public boolean isMoving() {
        return parent.isMoving();
    }

    @Override
    public double getEnginePower() {
        return parent.getEnginePower();
    }

    @Override
    public void gas(double amount) {
        parent.gas(amount);
    }

    @Override
    public void brake(double amount) {
        parent.brake(amount);
    }


}
