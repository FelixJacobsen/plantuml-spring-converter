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

    public String convertSVGFromText(String text) throws IOException {
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

    /**
     * Yet to be implemented
     * @return
     * @throws IOException
     */
    public File generatePNGFromFile() throws IOException {
        File theFile = new File("yaml.txt");
        SourceFileReader fileReader = new SourceFileReader(theFile);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        fileReader.setFileFormatOption(getPNGFormat());
        List<GeneratedImage> svgs = fileReader.getGeneratedImages();

        return svgs
            .get(0)
            .getPngFile();
    }

    public boolean checkIfValidYaml(String text) {
        String[] rows = text.split("\n");
        String lastRow = rows[rows.length - 1];
        return !text.startsWith("@startyaml") || !lastRow.startsWith("@endyaml");
    }

    private FileFormatOption getPNGFormat() {
        return new FileFormatOption(FileFormat.PNG);
    }

    private FileFormatOption getSVGFormat() {
        return new FileFormatOption(FileFormat.SVG);
    }
}
