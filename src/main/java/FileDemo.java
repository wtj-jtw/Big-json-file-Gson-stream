import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import groovy.json.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileDemo {
    public static List<Message> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in));
        List<Message> messages = new ArrayList<>();
        reader.beginArray();
        Gson gson = new Gson();
        while (reader.hasNext()){
            Message message = gson.fromJson(reader,Message.class);
            messages.add(message);
            break;
        }
        reader.endArray();
        reader.close();
        return messages;
    }
    public static void main(String[] args) {
        String path = "E:\\SciKG_min_1.0\\SciKG_min_1.0\\SciKG_min_1.0_1.json";
        List<Message> messages = null;
        try {
            FileInputStream inputStream =new FileInputStream(path);
            messages = readJsonStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(messages.get(0));
    }
}
