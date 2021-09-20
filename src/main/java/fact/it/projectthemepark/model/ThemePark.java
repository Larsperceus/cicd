package fact.it.projectthemepark.model;
import java.util.ArrayList;
import java.util.Iterator;

// Lars Kammeijer r0831083
public class ThemePark {
    private String name;
    private int numberVisitors;
    private ArrayList< Attraction> attractions= new ArrayList<> ();
    public ThemePark(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }
    public int getNumberOfAttractions(){
        return this.attractions.size();
    }
    public void addAttraction(Attraction attractions){
        this.attractions.add(attractions);
    }
    public Attraction searchAttractionByName(String name){
        Attraction found = null;
        Iterator var3 = this.attractions.iterator();

        while(var3.hasNext()) {
            Attraction attr = (Attraction)var3.next();
            if (attr.getName().equals(name)) {
                found = attr;
            }
        }
        return found;
    }
    public void registerVisitor(Visitor visitor){
        ++this.numberVisitors;
        visitor.setThemeParkCode(this.numberVisitors);
    }


}
