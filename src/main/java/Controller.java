import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositories.MeinFluessiggasRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    MeinFluessiggasRepository meinFluessiggasRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void beitraegeAnzeigen(Model model){

        System.out.println(meinFluessiggasRepository.findAll());

    }

}
