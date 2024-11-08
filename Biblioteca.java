import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biblioteca {
    private String nombreBiblioteca;
    private  List<Libro> listaLibro;

    public Biblioteca(){
        this.nombreBiblioteca = "";
        this.listaLibro = new ArrayList<>();
    }

    public Biblioteca(String nombreBiblioteca){
        this.nombreBiblioteca = nombreBiblioteca;
        this.listaLibro = new ArrayList<>();
    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public List<Libro> getListaLibro() {
        return listaLibro;
    }

    public void setListaLibro(List<Libro> listaLibro) {
        this.listaLibro = listaLibro;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "nombreBiblioteca='" + nombreBiblioteca + '\'' +
                ", listaLibro=" + listaLibro +
                '}';
    }

    public void agregarLibro(Libro libro){
        listaLibro.add(libro);
    }


    public void eliminarLibro(Libro libro) {
        if (listaLibro.isEmpty()) {
            System.out.println("La lista está vacía");
        } else {
            boolean eliminado = listaLibro.remove(libro); // Elimina y devuelve true si se eliminó
            if (!eliminado) {
                System.out.println("El libro no se encontró en la lista.");
            }
        }
    }



    public  void recorrerLista(){
        Iterator<Libro> iterador = listaLibro.listIterator();

        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }
    }


    public JSONObject serializar(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombreBiblioteca", this.nombreBiblioteca);

        JSONArray jsonArray = new JSONArray();
        for (Libro libro : listaLibro){
            jsonArray.put(libro.serializar()); // Aquí llamamos al método serializar de Libro
        }

        jsonObject.put("libros", jsonArray); // Cambié "libros" a este array
        return jsonObject; // Esto retorna un objeto JSON, no un array
    }


    public static Biblioteca descerializar(JSONObject jsonObject){
        List<Libro> listaLibros = new ArrayList<>();

        String nombreBiblioteca = jsonObject.getString("nombreBiblioteca");
         JSONArray jsonArray = jsonObject.getJSONArray("libros");
        for (int i = 0;i< jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            Libro libro = Libro.Descerializar(object);
            listaLibros.add(libro);
        }
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombreBiblioteca(nombreBiblioteca);
        biblioteca.setListaLibro(listaLibros);

        return biblioteca;
    }



    public File crearArchivo(String nombre){
        File archivo = new File(nombre);

        try{
            FileWriter fileWriter = new FileWriter(nombre);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return archivo;
    }



    public void cargarArchivoJSON(JSONObject jsonObject){
        File archivo = crearArchivo("biblioteca.json");

        try (FileWriter fileWriter = new FileWriter(archivo)){
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();

        }

    }

    public static void mostrarArchivoJSON(String archivo){
       JSONTokener jsonTokener = leerArchivo(archivo);

      if(jsonTokener != null){
          JSONObject jsonObject = new JSONObject(jsonTokener);
          JSONArray jsonArray = jsonObject.getJSONArray("libros");
          System.out.printf(jsonArray.toString(2));
      }else {
          System.out.println("no se pudo abrir el archivo.");
      }
    }



    public static JSONTokener leerArchivo(String nombreArchivo){
        JSONTokener jsonTokener = null;

        try{
            jsonTokener = new JSONTokener(new FileReader(nombreArchivo));
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }
        return jsonTokener;
    }


    public static List<Libro> crearLibrosDesdeJSON(String archivo) {
        List<Libro> lista = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(leerArchivo(archivo));///cambiar a JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("libros");//obtiene el array de libros
            lista = Libro.crearObjetosJava(jsonArray); // llamo al metodo de crear libros
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }









}
