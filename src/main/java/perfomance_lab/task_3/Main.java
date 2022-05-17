package perfomance_lab.task_3;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Path path1 = null;
        Path path2 = null;

        try {
            path1 = Paths.get(args[0]);
            if (Files.notExists(path1)) {
                throw new FileNotFoundException("Файл 1 не был найден - проверьте путь!");
            }

            path2 = Paths.get(args[1]);
            if (Files.notExists(path2)) {
                throw new FileNotFoundException("Файл 2 не был найден - проверьте путь!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject1 = new JSONObject(Files.readAllLines(path1).stream().collect(Collectors.joining()));
        JSONObject jsonObject2 = new JSONObject(Files.readAllLines(path2).stream().collect(Collectors.joining()));

        if (jsonObject2.has("values")) {
            for (Object itemResult : jsonObject2.getJSONArray("values")) {
                Integer id = ((JSONObject) itemResult).getInt("id");
                String value = ((JSONObject) itemResult).getString("value");
                returnJsonArrayResult(jsonObject1, id, value);
            }
        }


//      Результирующий файл создать здесь
        System.out.println(jsonObject1);
        Files.write(Paths.get("result.json"), jsonObject1.toString().getBytes());
    }

    public static void returnJsonArrayResult(JSONObject jsObj, Integer id, String value) {
        if (jsObj.getInt("id") == id) {
            jsObj.put("value", value);
        } else {
            if (jsObj.has("values")) {
                for (Object itemJson : jsObj.getJSONArray("values")) {
                    JSONObject jsonObject = (JSONObject) itemJson;
                    returnJsonArrayResult((JSONObject) itemJson, id, value);
                }
            }
        }
    }
}
