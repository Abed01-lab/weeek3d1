package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
@NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movie m"),
@NamedQuery(name = "Movie.getByName", query = "SELECT m FROM Movie m WHERE m.title LIKE :title")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int year;
    private String [] actors;
    private String title;
    
    public Movie() {
    }

    public Movie(String title, int year, String[] actors) {
        this.title = title;
        this.year = year;
        this.actors = actors;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       
}
