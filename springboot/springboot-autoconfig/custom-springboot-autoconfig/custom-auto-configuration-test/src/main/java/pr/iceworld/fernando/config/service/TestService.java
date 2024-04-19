package pr.iceworld.fernando.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pr.iceworld.fernando.log.LogApi;

@Service
public class TestService {

    @Autowired
    //@Qualifier("txtLog")
    @Qualifier("xmlLog")
    LogApi logApi;

    public void doAction() {
        logApi.generate("cccc");
    }
}
