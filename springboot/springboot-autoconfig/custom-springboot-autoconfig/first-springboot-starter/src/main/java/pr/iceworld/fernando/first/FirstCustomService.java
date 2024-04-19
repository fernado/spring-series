package pr.iceworld.fernando.first;

public class FirstCustomService {
    private final String name;
    private final String gender;

    public FirstCustomService(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getCustom() {
        return "name=" + name + ", gender=" + gender;
    }
}
