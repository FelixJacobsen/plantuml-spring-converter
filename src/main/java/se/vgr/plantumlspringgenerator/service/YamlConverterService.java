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
public class YamlConverterService {

    public String convertToSVGFromText(String text) throws IOException {
        if(checkIfContainsHyphen(text)){
            text = removeHyphen(text);
        }

        if (checkIfValidYaml(text)) {
            text = addAtSignToText(text);
        }

        SourceStringReader stringReader = new SourceStringReader(text);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            stringReader.generateImage(bos, getSVGFormat());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return "There was an error parsing to plantuml";
        }
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * Yet to be implemented
     */
    public File convertToPngFromFile() throws IOException {
        File theFile = new File("");
        SourceFileReader fileReader = new SourceFileReader(theFile);
        fileReader.setFileFormatOption(getPNGFormat());
        List<GeneratedImage> svg = fileReader.getGeneratedImages();

        return svg
            .get(0)
            .getPngFile();
    }

    private String addAtSignToText(String text) {
        String[] rows = text.split("\n");

        if(!rows[0].startsWith("@startyaml")){
            rows[0] = "@startyaml" + rows[0];
            System.out.println("Added @startyaml to the input");
        }
        if(!rows[rows.length - 1].startsWith("@endyaml") ){
            rows[rows.length - 1] = "@endyaml" + rows[rows.length - 1];
            System.out.println("Added @endyaml to the input");
        }
        StringBuilder sb = new StringBuilder();
        for (String row : rows) {
            sb.append(row).append("\n");
        }
        System.out.println(sb);
        return sb.toString();
    }

    private String removeHyphen(String text){
        if (text.startsWith("---")) {
            System.out.println("Removed hyphen");
            return text.substring(3);
        }
        return text;
    }

    private boolean checkIfValidYaml(String text) {
        String[] rows = text.split("\n");
        String lastRow = rows[rows.length - 1];
        return !text.startsWith("@startyaml") || !lastRow.startsWith("@endyaml");
    }

    private boolean checkIfContainsHyphen(String text){
        return text.startsWith("---");
    }

    private FileFormatOption getPNGFormat() {
        return new FileFormatOption(FileFormat.PNG);
    }

    private FileFormatOption getSVGFormat() {
        return new FileFormatOption(FileFormat.SVG);
    }
}
