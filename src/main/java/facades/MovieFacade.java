package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Retitle Class to a relevant title Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
   
    public List<Movie> getAllMovies(){
        EntityManager em = getEntityManager();
        List<Movie> movies;
        try {
            movies = em.createQuery("SELECT m FROM Movie m").getResultList();
            return movies;
        } finally {
            em.close();
        }
    }
    
    public List getMovieByName(String title) {
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("select c from Customer c where c.lastName=:arg1", Movie.class);
            query.setParameter("arg1", title);
            List<Movie> movies = query.getResultList();
            return movies;          
        } finally {
            em.close();
        }
    }
    
    public String addMovie(Movie movie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return "Successfully added" + movie.getName();
        } finally {
            em.close();
        }
    }
    
    public int deleteMovieByName (String title){
        EntityManager em = getEntityManager();
        try{
            TypedQuery query = em.createQuery("DELETE FROM Movie m WHERE m.title=:arg1", Movie.class);
            int deleted = query.setParameter("arg1", title).executeUpdate(); 
            return deleted;
        } finally {
            em.close();
        }
    }
    
    public int deleteAll () {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            int deleted = em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            return deleted;
        } finally {
            em.close();
        }
    }
}
