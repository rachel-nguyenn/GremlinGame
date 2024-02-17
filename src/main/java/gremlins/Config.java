package gremlins;

import processing.data.JSONArray;
import processing.data.JSONObject;
import java.util.*;

public class Config {

    public JSONObject obj;

    public Config(JSONObject obj) {
        this.obj = obj;
    }

    public HashMap<Integer, ArrayList<Object>> extract() {
        HashMap<Integer, ArrayList<Object>> data = new HashMap<>();

        JSONArray levels = (JSONArray) obj.get("levels");
        JSONObject level_1 = levels.getJSONObject(0);
        JSONObject level_2 = levels.getJSONObject(1);

        String filename_1 = level_1.getString("layout");
        float wizard_cooldown_1 = level_1.getFloat("wizard_cooldown");
        float enemy_cooldown_1 = level_1.getFloat("enemy_cooldown");

        String filename_2 = level_2.getString("layout");
        float wizard_cooldown_2 = level_2.getFloat("wizard_cooldown");
        float enemy_cooldown_2 = level_2.getFloat("enemy_cooldown");

        ArrayList<Object> level1 = new ArrayList<Object>();
        level1.add(filename_1);
        level1.add(wizard_cooldown_1);
        level1.add(enemy_cooldown_1);

        ArrayList<Object> level2 = new ArrayList<Object>();
        level2.add(filename_2);
        level2.add(wizard_cooldown_2);
        level2.add(enemy_cooldown_2);

        data.put(1, level1);
        data.put(2, level2);
        
        return data;
    }
}
