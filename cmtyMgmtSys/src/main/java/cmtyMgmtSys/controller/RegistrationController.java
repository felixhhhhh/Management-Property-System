package cmtyMgmtSys.controller;

import cmtyMgmtSys.entity.Resident;
import cmtyMgmtSys.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegistrationController {

    @Autowired
    private ResidentService residentService;

    @RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        return new ModelAndView("register", "customer", new Resident());
    }

    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    public ModelAndView registerCustomer(@ModelAttribute Resident resident) {
        ModelAndView modelAndView = new ModelAndView();
        residentService.addCustomer(resident);
        modelAndView.setViewName("login");
        modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and passowrd");
        return modelAndView;
    }
}