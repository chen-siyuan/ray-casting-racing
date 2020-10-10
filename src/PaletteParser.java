import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaletteParser {

    // TODO: 10/9/20 add more color options 
    // TODO: 10/9/20 add more customizable area

    private final String name;
    private String dateCreated;
    private final int[] vision;
    private final int[] map;
    private final int[] control;

    public PaletteParser(String _name) {
        name = _name;
        vision = new int[3];
        map = new int[3];
        control = new int[3];
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public int[] getVision() {
       return vision;
    }

    public int[] getMap() {
        return map;
    }

    public int[] getControl() {
        return control;
    }

    public void parsePalette() throws IOException {

        JSONObject palette = new JSONObject(new BufferedReader(new FileReader(String.format("palettes/%s.json", name))).readLine());

        dateCreated = (String)palette.get("dateCreated");

        vision[0] = (int)((JSONObject)palette.get("vision")).get("r");
        vision[1] = (int)((JSONObject)palette.get("vision")).get("g");
        vision[2] = (int)((JSONObject)palette.get("vision")).get("b");

        map[0] = (int)((JSONObject)palette.get("map")).get("r");
        map[1] = (int)((JSONObject)palette.get("map")).get("g");
        map[2] = (int)((JSONObject)palette.get("map")).get("b");

        control[0] = (int)((JSONObject)palette.get("control")).get("r");
        control[1] = (int)((JSONObject)palette.get("control")).get("g");
        control[2] = (int)((JSONObject)palette.get("control")).get("b");

    }

}
