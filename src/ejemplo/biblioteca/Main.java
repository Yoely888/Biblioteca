package ejemplo.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;
    private boolean disponibilidad;
    private List<String> comentarios; 

    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponibilidad = true; // Por defecto, el libro está disponible
        this.comentarios = new ArrayList<>(); // Inicializar la lista de comentarios
    }

    
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponibilidad;
    }

    public void prestar() {
        disponibilidad = false;
    }

    public void devolver() {
        disponibilidad = true;
    }

    // Método para agregar un comentario
    public void agregarComentario(String comentario) {
        comentarios.add(comentario);
    }

    // Método para imprimir los detalles del libro
    public void imprimirDetalles() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Año de Publicación: " + anioPublicacion + ", Género: " + genero + ", Disponible: " + (disponibilidad ? "Sí" : "No"));
        System.out.println("Comentarios: " + comentarios);
    }
}

// Defino la clase de biblioteca
class Biblioteca {
    private List<Libro> libros;

    // Constructor para inicializar la lista de libros
    public Biblioteca() {
        libros = new ArrayList<>();
    }

    // Método para agregar un nuevo libro a la biblioteca
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Método para buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // Método para buscar libros por autor
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // Método para prestar un libro
    public boolean prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.isDisponible()) {
                libro.prestar();
                return true;
            }
        }
        return false;
    }

    // Método para devolver un libro
    public boolean devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && !libro.isDisponible()) {
                libro.devolver();
                return true;
            }
        }
        return false;
    }

    // Método para mostrar información detallada de todos los libros disponibles
    public void mostrarLibrosDisponibles() {
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                libro.imprimirDetalles();
            }
        }
    }

    // Método para generar estadísticas de géneros
    public void generarEstadisticasGeneros() {
        Map<String, Integer> estadisticas = new HashMap<>();
        for (Libro libro : libros) {
            String genero = libro.getGenero();
            estadisticas.put(genero, estadisticas.getOrDefault(genero, 0) + 1);
        }
        System.out.println("Estadísticas de géneros:");
        for (Map.Entry<String, Integer> entry : estadisticas.entrySet()) {
            System.out.println("Género: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }
}

// Clase principal para ejecutar el programa
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Agregar libros a la biblioteca
        biblioteca.agregarLibro(new Libro("El Gato Ensombrerado", "Dr. Seuss", 1957, "Infantil"));
        biblioteca.agregarLibro(new Libro("Donde Viven los Monstruos", "Maurice Sendak", 1963, "Infantil"));
        biblioteca.agregarLibro(new Libro("Harry Potter y la Piedra Filosofal", "J.K. Rowling", 1997, "Fantasía"));

        // Agregar comentarios a los libros
        Libro libro1 = biblioteca.buscarPorTitulo("El Gato Ensombrerado").get(0);
        libro1.agregarComentario("Un clásico divertido para niños.");
        libro1.agregarComentario("Muy entretenido y educativo.");

        // Buscar libros por título
        System.out.println("Buscar por título 'Harry Potter y la Piedra Filosofal':");
        for (Libro libro : biblioteca.buscarPorTitulo("Harry Potter y la Piedra Filosofal")) {
            libro.imprimirDetalles();
        }

        // Buscar libros por autor
        System.out.println("\nBuscar por autor 'Dr. Seuss':");
        for (Libro libro : biblioteca.buscarPorAutor("Dr. Seuss")) {
            libro.imprimirDetalles();
        }

        // Prestar un libro
        System.out.println("\nPrestar 'El Gato Ensombrerado': " + (biblioteca.prestarLibro("El Gato Ensombrerado") ? "Éxito" : "Falló"));

        // Mostrar libros disponibles
        System.out.println("\nLibros disponibles:");
        biblioteca.mostrarLibrosDisponibles();

        // Devolver un libro
        System.out.println("\nDevolver 'El Gato Ensombrerado': " + (biblioteca.devolverLibro("El Gato Ensombrerado") ? "Éxito" : "Falló"));

        // Mostrar libros disponibles nuevamente
        System.out.println("\nLibros disponibles:");
        biblioteca.mostrarLibrosDisponibles();

        // Generar estadísticas de géneros
        System.out.println("\nGenerar estadísticas de géneros:");
        biblioteca.generarEstadisticasGeneros();
    }
}