package ejercicio19;

public class Dept {

    private int num;
    private String name;
    private String loc;

    public Dept(int num, String name, String loc) {
        this.num = num;
        this.name = name;
        this.loc = loc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
