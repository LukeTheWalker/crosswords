import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Crossword extends Subject{
    public List<String> orizzontali = new ArrayList<>();
    public List<String> verticali = new ArrayList<>();
    public Integer width;
    public Integer height;
    public List<Coords> blacks = new ArrayList<>();
    public List<Number> numbers = new ArrayList<>();
    public Yaml yaml = new Yaml();
    public String document = "physical_composition.yaml";

    Crossword(String orizzontali_filename, String verticali_filename){
        orizzontali = Utils.getSuggestions(orizzontali_filename);
        verticali = Utils.getSuggestions(verticali_filename);
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(document);
        Map<String, Object> obj = yaml.load(inputStream);
        System.out.println(obj);
    }
}
