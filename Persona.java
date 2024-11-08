import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Persona {
    private String nombre;
    private String dni;
    private String sexo;
    private int edad;

    public Persona() {
        this.nombre = "";
        this.dni = "";
        this.sexo = "";
        this.edad = 0;
    }

    public Persona(String nombre, String dni, String sexo, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo='" + sexo + '\'' +
                ", edad=" + edad +
                '}';
    }

    ///CONVERTIR OBJETO PERSONA EN UN JSON OBJECT
    public JSONObject crearJsonObject() {
        JSONObject json = new JSONObject();
        json.put("nombre", this.nombre);
        json.put("dni", this.dni);
        json.put("sexo", this.sexo);
        json.put("edad", this.edad);
        return json;
    }

    public static File crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        } catch (Exception e) {
            System.out.println("Error al crear el archivo");
        }

        System.out.println("archivo creado correctamente");
        return archivo;
    }

    public JSONArray crearJsonArray() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(this.crearJsonObject());
        return jsonArray;
    }


    public static void guardarJson(JSONArray jsonArray) {
        File archivo = crearArchivo("persona.json");

        try (FileWriter fileWriter = new FileWriter(archivo)) {
            fileWriter.write(jsonArray.toString(2));
            fileWriter.flush();


        } catch (IOException e) {
            e.printStackTrace();

        }


    }


    public static JSONTokener leerArchivo(String archivo) {
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return jsonTokener;
    }


    public static void mostrarContenidoJson(String archivo) {
        JSONTokener jsonTokener = leerArchivo(archivo);
        if (jsonTokener != null) {
            JSONArray jsonArray = new JSONArray(jsonTokener);
            System.out.println(jsonArray.toString(2));
        } else {
            System.out.println("error al leer el archivo JSON.");
        }
    }


    // Método para leer JSON y pasar personas a la lista desde el archivo
    public static List<Persona> crearPersonasDesdeJSON(String archivo) {
        List<Persona> personas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(leerArchivo(archivo));
            personas = crearObjetoJava(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;
    }

    // Método que crea objetos Persona a partir de un JSONArray
    public static List<Persona> crearObjetoJava(JSONArray jsonArray) {
        List<Persona> personas = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Persona persona = Persona.descerializar(objeto);
            personas.add(persona);  // Agregamos cada persona a la lista
        }
        return personas;
    }

///DESCERIALIZAR UN JSONOBJECT A UN OBJETO DE JAVA
    public static Persona descerializar(JSONObject persona) {
        String nombre = persona.getString("nombre");
        String dni = persona.getString("dni");
        String sexo = persona.getString("sexo");
        int edad = persona.getInt("edad");
        return new Persona(nombre, dni, sexo, edad);
    }


}
