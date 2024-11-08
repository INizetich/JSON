import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
public class Curso {
    private String nombreCurso;
    private UUID codigo;
   private List<Persona> listaPersonas;

    public Curso(){
        this.nombreCurso = null;
        this.codigo = null;
        this.listaPersonas = null;
    }

    public Curso(String nombreCurso){
        this.nombreCurso = nombreCurso;
        this.codigo = UUID.randomUUID();
        this.listaPersonas = new ArrayList<>();
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nombreCurso='" + nombreCurso + '\'' +
                ", codigo=" + codigo +
                ", listaPersonas=" + listaPersonas +
                '}';
    }

    public void agregarPersona(Persona persona){
        listaPersonas.add(persona);
    }

    public void eliminarPersona(Persona persona){
        if(listaPersonas.isEmpty()){
            System.out.println("la lista esta vacia.");
        }else if (!listaPersonas.isEmpty()){
            listaPersonas.remove(persona);
        }
    }

    public void recorrerLista(){
        Iterator<Persona> iterador = listaPersonas.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }
    }

    public JSONObject serializar(){
        JSONObject objeto = new JSONObject();
        objeto.put("nombreCurso", nombreCurso);
        objeto.put("codigo", codigo.toString());
        objeto.put("listaPersonas", listaPersonas);

        JSONArray jsonArray = new JSONArray();

        for (Persona persona : listaPersonas) {
            jsonArray.put(persona.crearJsonArray());
        }
        objeto.put("personas", jsonArray);
        return objeto;

    }

  public static File crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try{
            FileWriter fileWriter = new FileWriter(archivo);
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
      return archivo;
  }

  public void guardarJsonArchivo(JSONObject objeto){
        File archivo = crearArchivo("curso.json");
        try (FileWriter fileWriter = new FileWriter(archivo)){
            fileWriter.write(objeto.toString());
            fileWriter.flush();

        }catch (IOException e){
            e.printStackTrace();

        }

      System.out.println("archivo cargado con exito.");


  }


  public static JSONTokener leerArchivo(String nombreArchivo){
        JSONTokener jsonTokener = null;

        try{
            jsonTokener = new JSONTokener(new FileReader(nombreArchivo));

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return jsonTokener;
  }


  public void mostrarContenidoArchivo(String nombreArchivo){
       JSONTokener jsonTokener = leerArchivo(nombreArchivo);

       if(jsonTokener!=null){
           JSONObject object = new JSONObject(jsonTokener);
           System.out.println(object.toString(2));
       }else {
           System.out.println("Archivo no encontrado.");
       }
  }

  public static Curso descerializar(JSONObject objeto){
      List<Persona> listaPersonas = new ArrayList<>();
        String nombreCurso = objeto.getString("nombreCurso");
        UUID codigo = UUID.fromString(objeto.getString("codigo"));
        JSONArray jsonArray = objeto.getJSONArray("listaPersonas");

        for (int i = 0;i<jsonArray.length();i++){
            JSONObject persona = jsonArray.getJSONObject(i);
            Persona persona4 = Persona.descerializar(persona);
            listaPersonas.add(persona4);
        }
        Curso curso = new Curso(nombreCurso);
        curso.setCodigo(codigo);
        curso.setListaPersonas(listaPersonas);
return curso;
  }

}
