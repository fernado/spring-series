package pr.iceworld.fernando.log;

public class TxtLog implements LogApi {
    @Override
    public void generate(String name) {
        System.out.println("Generating txt log file ..." + name + ".txt");
    }
}