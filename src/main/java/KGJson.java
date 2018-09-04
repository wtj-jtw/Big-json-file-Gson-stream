import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KGJson {
    public List<Message> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<Message> readMessagesArray(JsonReader reader) throws IOException {
        List<Message> messages = new ArrayList<Message>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
            //break;//先只看一个message
        }
        reader.endArray();
        return messages;
    }

    public Message readMessage(JsonReader reader) throws IOException {
        int id = -1;
        String name = null;
        String name_zh = null;
        int level = -1;
        String definition = null;
        String definition_zh = null;
        List<Integer> child_nodes = null;
        int parent = -1;
        List<Expert> experts = null;
        List<Publication> publications = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String s = reader.nextName();
            if (s.equals("id")) {
                id = reader.nextInt();
            } else if (s.equals("name")) {
                name = reader.nextString();
            } else if (s.equals("name_zh")) {
                name_zh = reader.nextString();
            } else if (s.equals("level")) {
                level = reader.nextInt();
            } else if (s.equals("definition")){
                definition = reader.nextString();
            } else if (s.equals("definition_zh")){
                definition_zh = reader.nextString();
            }else if (s.equals("child_nodes")){
                child_nodes = readIntegersArray(reader);
            }else if (s.equals("parent")){
                parent = reader.nextInt();
            }else if (s.equals("experts")){
                experts = readExpertsArray(reader);
            }else if(s.equals("publications")){
                publications = readPublicationsArray(reader);
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Message message = new Message();
        message.setId(id);
        message.setName(name);
        message.setName_zh(name_zh);
        message.setLevel(level);
        message.setDefinition(definition);
        message.setDefinition_zh(definition_zh);
        message.setChild_nodes(child_nodes);
        message.setParent(parent);
        message.setExperts(experts);
        message.setPublications(publications);
        return message;
    }

    public List<Integer> readIntegersArray(JsonReader reader) throws IOException {
        List<Integer> integers = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            integers.add(reader.nextInt());
        }
        reader.endArray();
        return integers;
    }
    public List<Expert> readExpertsArray(JsonReader reader) throws IOException{
        List<Expert> experts = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            experts.add(readExpert(reader));
        }
        reader.endArray();
        return experts;
    }
    public Expert readExpert(JsonReader reader) throws IOException{
        String id = null;
        String name = null;
        String name_zh = null;
        String position = null;
        String aff = null;
        int h_index = -1;
        List<String> interests = null;

        reader.beginObject();
        while (reader.hasNext()){
            String s = reader.nextName();
            if (s.equals("id")){
                id = reader.nextString();
            }else if (s.equals("name")){
                name = reader.nextString();
            }else if (s.equals("name_zh")){
                name_zh = reader.nextString();
            }else if (s.equals("position")){
                position = reader.nextString();
            }else if (s.equals("aff")){
                aff = reader.nextString();
            }else if (s.equals("h_index")){
                h_index = reader.nextInt();
            }else if (s.equals("interests")){
                interests = readInterestsArray(reader);
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Expert expert = new Expert();
        expert.setId(id);
        expert.setName(name);
        expert.setName_zh(name_zh);
        expert.setPosition(position);
        expert.setAff(aff);
        expert.setH_index(h_index);
        expert.setInterests(interests);
        return expert;
    }
    public List<String> readInterestsArray(JsonReader reader) throws IOException{
        List<String> strings = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            strings.add(reader.nextString());
        }
        reader.endArray();
        return strings;
    }
    public List<Publication> readPublicationsArray(JsonReader reader) throws IOException{
        List<Publication> publications = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            publications.add(readPublication(reader));
        }
        reader.endArray();
        return publications;
    }
    public Publication readPublication(JsonReader reader) throws IOException{
        String id = null;
        String title = null;
        List<Author> authors = null;
        reader.beginObject();
        while (reader.hasNext()){
            String s = reader.nextName();
            if (s.equals("id")) {
                id = reader.nextString();
            }else if(s.equals("title")){
                title = reader.nextString();
            }else if(s.equals("authors")){
                authors = readAuthorsArray(reader);
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Publication publication = new Publication();
        publication.setId(id);
        publication.setTitle(title);
        publication.setAuthors(authors);
        return publication;
    }
    public List<Author> readAuthorsArray(JsonReader reader) throws IOException{
        List<Author> authors = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()){
            authors.add(readAuthor(reader));
        }
        reader.endArray();
        return authors;
    }
    public Author readAuthor(JsonReader reader) throws IOException{
        String id = null;
        String name = null;

        reader.beginObject();
        while (reader.hasNext()){
            String s = reader.nextName();
            if (s.equals("id")){
                id = reader.nextString();
            }else if (s.equals("name")){
                name = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }

    public static void main(String[] args) {
        KGJson kgJson = new KGJson();
        String path = "E:\\SciKG_min_1.0\\SciKG_min_1.0\\SciKG_min_1.0_1.json";
        String outPath = "E:\\outjsons\\";
        Message message = null;
        int i = 0;
        try {
            FileInputStream in =new FileInputStream(path);
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()){
                i++;
                message = kgJson.readMessage(reader);
                //FileOutputStream out = new FileOutputStream(outPath + i + ".json");
                //JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
                JsonWriter writer = new JsonWriter(new FileWriter(outPath + i + ".json"));
                WritetoFile writetoFile = new WritetoFile();
                writetoFile.writeMessage(writer,message);
                writer.flush();
                //out.flush();
                //out.close();
            }
            reader.endArray();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
