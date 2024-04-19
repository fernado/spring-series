package pr.iceworld.fernando.log;

public class HtmlLog implements LogApi {
    @Override
    public void generate(String name) {
        System.out.println("Generating html log file ..." + name + ".html");
    }
}