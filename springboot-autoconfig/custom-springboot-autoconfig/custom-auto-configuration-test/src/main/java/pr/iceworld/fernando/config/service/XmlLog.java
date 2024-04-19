package pr.iceworld.fernando.config.service;

import org.springframework.stereotype.Service;
import pr.iceworld.fernando.log.LogApi;

@Service
public class XmlLog implements LogApi {
    @Override
    public void generate(String name) {
        System.out.println("Generating xml log file ..." + name + ".xml");
    }
}