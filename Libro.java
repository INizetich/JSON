import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libro {
    private String titulo;
    private String autor;
    private int publicacion;
    private double precio;


    public Libro(){
        this.titulo = "";
        this.autor = "";
        this.publicacion = 0;
        this.precio = 0.0;
    }

    public Libro(String titulo,String autor,int publicacion,double precio){
        this.titulo = titulo;
        this.autor = autor;
        this.publicacion = publicacion;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", publicacion=" + publicacion +
                ", precio=" + precio +
                '}';
    }



    public JSONObject serializar(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("titulo",this.titulo);
        jsonObject.put("autor",this.autor);
        jsonObject.put("publicacion",this.publicacion);
        jsonObject.put("precio",this.precio);

        return jsonObject;
    }

    public JSONArray crearJsonArray(){
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(this.serializar());
        return jsonArray;
    }


    public  static List<Libro> crearObjetosJava(JSONArray jsonArray){
        List<Libro> listaLibro = new ArrayList<>();
        for (int i = 0; i< jsonArray.length();i++){
            JSONObject objeto = jsonArray.getJSONObject(i);
            Libro libro = Libro.Descerializar(objeto);
            listaLibro.add(libro);

        }
        return listaLibro;
    }



    public static Libro Descerializar(JSONObject jsonObject){
        String titulo = jsonObject.getString("titulo");
        String autor = jsonObject.getString("autor");
        int publicacion = jsonObject.getInt("publicacion");
        double precio = jsonObject.getDouble("precio");

        return new Libro(titulo,autor,publicacion,precio);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return publicacion == libro.publicacion && Double.compare(precio, libro.precio) == 0 && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, publicacion, precio);
    }
}
