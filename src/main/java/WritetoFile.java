import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

public class WritetoFile {
    public void writeMessage(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("id").value(message.getId());
        writer.name("name").value(message.getName());
        writer.name("name_zh").value(message.getName_zh());
        writer.name("level").value(message.getLevel());
        writer.name("definition").value(message.getDefinition());
        writer.name("definition_zh").value(message.getDefinition_zh());
        writer.name("child_nodes");
        writeIntegersArray(writer,message.getChild_nodes());
        writer.name("parent").value(message.getParent());
        writer.name("experts");
        writeExpertsArray(writer,message.getExperts());
        writer.name("publications");
        writePublicationsArray(writer,message.getPublications());
        writer.endObject();
    }

    public void writePublicationsArray(JsonWriter writer, List<Publication> publications) throws IOException{
        writer.beginArray();
        for (Publication publication : publications){
            writePublication(writer,publication);
        }
        writer.endArray();
    }

    public void writePublication(JsonWriter writer, Publication publication) throws IOException{
        writer.beginObject();
        writer.name("id").value(publication.getId());
        writer.name("title").value(publication.getTitle());
        writer.name("authors");
        writeAuthorsArray(writer,publication.getAuthors());
        writer.endObject();
    }

    public void writeAuthorsArray(JsonWriter writer,List<Author> authors) throws IOException{
        writer.beginArray();
        for (Author author : authors){
            writeAuthor(writer,author);
        }
        writer.endArray();
    }

    public void writeAuthor(JsonWriter writer, Author author) throws IOException{
        writer.beginObject();
        writer.name("id").value(author.getId());
        writer.name("name").value(author.getName());
        writer.endObject();
    }

    public void writeExpertsArray(JsonWriter writer, List<Expert> experts) throws IOException{
        writer.beginArray();
        for (Expert expert : experts){
            writeExpert(writer,expert);
        }
        writer.endArray();
    }

    public void writeExpert(JsonWriter writer, Expert expert) throws IOException{
        writer.beginObject();
        writer.name("id").value(expert.getId());
        writer.name("name").value(expert.getName());
        writer.name("name_zh").value(expert.getName_zh());
        writer.name("position").value(expert.getPosition());
        writer.name("aff").value(expert.getAff());
        writer.name("h_index").value(expert.getH_index());
        writer.name("interests");
        writeInterestsArray(writer,expert.getInterests());
        writer.endObject();
    }

    public void writeInterestsArray(JsonWriter writer, List<String> interests) throws IOException{
        writer.beginArray();
        for (String interest : interests){
            writer.value(interest);
        }
        writer.endArray();
    }

    public void writeIntegersArray(JsonWriter writer, List<Integer> integers) throws IOException{
        writer.beginArray();
        for (Integer value: integers){
            writer.value(value);
        }
        writer.endArray();
    }
}
