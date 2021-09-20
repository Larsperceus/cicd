package fact.it.projectthemepark.model;
// Lars Kammeijer r0831083
public class Attraction {
    private String name;
    private int duration;
    private String photo;
    private Staff Responsible;
    public Attraction(){

    }
    public Attraction(String name){
        this.name = name;

    }
    public Attraction(String name,int duration){
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Staff getResponsible() {
        return this.Responsible;
    }

    public void setResponsible(Staff Responsible) {
        this.Responsible = Responsible;
    }
}
