package com.app.controller;

        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HelloController {

    @GetMapping(value = "/welcome", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String HelloMethod() {
        return "Welcome to spring boot application";
    }

}


