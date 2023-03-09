package se.vgr.plantumlspringgenerator.service;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.SourceStringReader;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PumlConverterService {

    public String convertToSVGFromText(String text) throws IOException {
        // TODO
        // Add some validation if the text is valid puml syntax
        // Else handle the exception

        SourceStringReader stringReader = new SourceStringReader(text);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            stringReader.generateImage(bos, getSVGFormat());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return "There was an error creating diagram, ensure that the input is correct puml data";
        }
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * Yet to be implemented
     */

    public File generatePNGFromFile() throws IOException {
        File theFile = new File("");
        SourceFileReader fileReader = new SourceFileReader(theFile);
        fileReader.setFileFormatOption(getPNGFormat());
        List<GeneratedImage> svg = fileReader.getGeneratedImages();

        return svg
            .get(0)
            .getPngFile();
    }

    private FileFormatOption getPNGFormat() {
        return new FileFormatOption(FileFormat.PNG);
    }

    private FileFormatOption getSVGFormat() {
        return new FileFormatOption(FileFormat.SVG);
    }
}
