package ejercicio6;

import java.io.Serializable;

public class Tractor implements Serializable {

    private static final long serialVersionUID = 96L;

    private String color;
    private String name;
    private int power;

    public Tractor(String color, String name, int power) {
        this.color = color;
        this.name = name;
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Tractor{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
