package se.vgr.plantumlspringgenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.vgr.plantumlspringgenerator.service.PumlConverterService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/puml")
@CrossOrigin(origins = "http://localhost:30000")
public class PumlController {

    private final PumlConverterService converter;

    public PumlController(final PumlConverterService inConverter) {
        converter = inConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "ping", method = RequestMethod.GET)
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

    @GetMapping("/text")
    public ResponseEntity<String> generateSVGFromText(@RequestBody String text) throws IOException {
        String test = converter.convertSVGFromText(text);
        if (test.contains("There was an error creating diagram, ensure that the input is correct puml data")) {
            return new ResponseEntity<>(test, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
