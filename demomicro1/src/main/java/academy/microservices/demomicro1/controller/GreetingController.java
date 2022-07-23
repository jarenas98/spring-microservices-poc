package academy.microservices.demomicro1.controller;

import academy.microservices.demomicro1.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "greeting")
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    private static final String templateMessage = "Hello %s";

    @GetMapping
    public Greeting greeting(@RequestParam(value= "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(templateMessage, name));
    }
}
