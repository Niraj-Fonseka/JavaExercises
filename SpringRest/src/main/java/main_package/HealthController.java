package main_package;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @RequestMapping("/")
    public String health() {
        return "Health Check !";

}
}
