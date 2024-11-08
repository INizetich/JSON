import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empleado {
    private int id;
    private String nombre;
    private double salario;
    private String departamento;




    public Empleado(){
        this.nombre = "";
        this.id = 0;
        this.salario = 0.0;
        this.departamento = "";
    }

    public Empleado(int id, String nombre, double salario, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    public JSONObject serializar(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",this.id);
        jsonObject.put("nombre",this.nombre);
        jsonObject.put("salario",this.salario);
        jsonObject.put("departamento",this.departamento);

        return jsonObject;
    }

    public static Empleado Descerialiazar(JSONObject jsonObject){
        int id = jsonObject.getInt("id");
        String nombre = jsonObject.getString("nombre");
        double salario = jsonObject.getDouble("salario");
        String departamento = jsonObject.getString("departamento");
        return new Empleado(id,nombre,salario,departamento);

    }



    public JSONArray crearJSONArray(){
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(this.serializar());
        return jsonArray;
    }





    public static File crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try{
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return archivo;
    }


    public static void cargarArchivoJSON(JSONArray jsonArray){
        File archivo = crearArchivo("empleado.json");
        try (FileWriter fileWriter = new FileWriter(archivo)){
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    public static JSONTokener leerArchivo(String nombre){
        JSONTokener jsonTokener = null;
        try{
            jsonTokener = new JSONTokener(new FileReader(nombre));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return jsonTokener;

    }


    public static void mostrarArchivoJSON(String archivo){
        JSONTokener jsonTokener = leerArchivo(archivo);

        try{
            if(jsonTokener!=null){
                JSONArray jsonArray = new JSONArray(jsonTokener);
                System.out.println(jsonArray.toString(2));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }






}
