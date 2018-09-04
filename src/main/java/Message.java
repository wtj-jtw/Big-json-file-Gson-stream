import java.util.List;

public class Message {
    private int id;
    private String name;
    private String name_zh;
    private int level;
    private String definition;
    private String definition_zh;
    private List<Integer> child_nodes;
    private int parent;
    private List<Expert> experts;
    private List<Publication> publications;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public String getName_zh() {
        return name_zh;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition_zh() {
        return definition_zh;
    }

    public void setDefinition_zh(String definition_zh) {
        this.definition_zh = definition_zh;
    }

    public List<Integer> getChild_nodes() {
        return child_nodes;
    }

    public void setChild_nodes(List<Integer> child_nodes) {
        this.child_nodes = child_nodes;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
