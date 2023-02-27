package se.vgr.plantumlspringgenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.vgr.plantumlspringgenerator.service.YamlConverterService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/yaml")
@CrossOrigin(origins = "http://localhost:30000")
public class YamlController {

    private final YamlConverterService converter;

    public YamlController(final YamlConverterService inConverter) {
        converter = inConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        System.out.println("Ping");
        return "ok";
    }

    @GetMapping("/file")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<File> generatePNGFromFile() throws IOException {
        File file = converter.generatePNGFromFile();
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/text")
    public ResponseEntity<String> generateSVGFromText(@RequestBody String text) throws IOException {
        String test = converter.convertSVGFromText(text);
        if (test.contains("There was an error parsing to plantuml")) {
            return new ResponseEntity<>(test, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("Handled");
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

}
