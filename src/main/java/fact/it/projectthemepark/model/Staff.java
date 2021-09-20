package fact.it.projectthemepark.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// Lars Kammeijer r0831083
public class Staff extends Person {
    private LocalDate startDate = LocalDate.now();
    private boolean Student;
    private ThemePark employedAt;
    public Staff(String firstName,String surName) {
        super(firstName, surName);
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        if(startDate == null){
            this.startDate = LocalDate.now();
        }
        else{
            this.startDate = startDate;
        }

    }
    public ThemePark getEmployedAt() {
        return this.employedAt;
    }

    public void setEmployedAt(ThemePark employedAt) {
        this.employedAt = employedAt;
    }

    public boolean isStudent() {
        return Student;
    }

    public void setStudent(boolean student) {
        Student = student;
    }
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (Student) {
            return "Staff member " + super.toString() + " (working student) is employed since " + startDate.format(dtf);
        } else {
            return "Staff member " + super.toString() + " is employed since " +startDate.format(dtf);
        }
    }
}
