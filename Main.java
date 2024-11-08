import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int opc = 0;
    ///INSTANCIO UN OBJETO DE LA CLASE PERSONA
    Persona persona = new Persona("ignacio", "45462201", "m", 21);
    Persona persona2 = new Persona("rufino", "42416301", "m", 21);


    ///INSTANCIO UN OBJETO DE LA CLASE CURSO
    Curso curso = new Curso("UTN");

    Biblioteca biblioteca = new Biblioteca("biblioteca UTN");
    Libro libro = new Libro("harry potter", "j.k rowling", 2007, 1500);
    Libro libro2 = new Libro("el principito", "arian shaffer", 2015, 600);
    Libro libro3 = new Libro("50 sombras de grey", "michael grey", 2019, 800);


    do{
      System.out.println("-------------INGRESE UNA OPCION---------------");
      System.out.println("->1.PERSONAS.JSON");
      System.out.println("->2.CURSO.JSON");
      System.out.println("->3.BIBLIOTECA.JSON");
      System.out.println("->4.EMPLEADO.JSON");
      System.out.println("->5.SALIR DEL SISTEMA");
      opc = scanner.nextInt();
      scanner.nextLine();

      switch (opc){

        case 1:
            /*
       ACA SERIALIZO UN OBJETO JAVA A UN JSONOBJECT, PODEMOS LLAMAR ESTE METODO CUANDO YA TENGAMOS UN JSON ARRAY INCIALIZADO Y QUERRAMOS AGREGAR NUEVAS PERSONAS AL JSON ARRAY
       SIN LA NECESIDAD DE CREAR UN JSON ARRAY ALTERNO.
       persona.crearJsonObject();
      persona2.crearJsonObject();*/

          ///CREO UN ARCHIVO .JSON
          File archivo = Persona.crearArchivo("persona.json");
          ///CREO UN JSON ARRAY Y GUARDO EL JSON OBJECT
          JSONArray arrayJSON = persona.crearJsonArray();
          arrayJSON.put(persona2.crearJsonObject());
          Persona.guardarJson(arrayJSON);

          ///LECTURA DEL ARCHIVO
          System.out.println("ARCHIVO: PERSONA.JSON");
          Persona.mostrarContenidoJson("persona.json");


          ///CARGAMOS PERSONAS A UNA LISTA DESDE EL ARCHIVO JSON
          List<Persona> listaPersonas = Persona.crearPersonasDesdeJSON("persona.json");


          ///MOSTRAMOS LAS PERSONAS CARGADAS EN LA LISTA DESDE EL ARCHIVO .JSON
          System.out.println("LISTA CARGADA DESDE EL ARCHIVO .JSON");
          for (Persona personita : listaPersonas) {
            System.out.println(personita);
          }

          break;

        case 2:
          curso.agregarPersona(persona);
          curso.agregarPersona(persona2);
          System.out.println("----------LISTA DEL CURSO DE LA UTN-----------");
          curso.recorrerLista();


          ///CREO EL ARCHIVO "CURSO.JSON"
          File archivo2 = new File("curso.json");
          ///SERIALIZO EL CURSO "UTN"
          JSONObject jsonObject = curso.serializar();
          ///GUARDO LA SERIALIZACION DEL CURSO DENTRO DE UN ARCHIVO
          curso.guardarJsonArchivo(jsonObject);
          System.out.println("ARCHIVO CURSO.JSON");
          ///MUESTRO EL CONTENIDO DEL ARCHIVO
          curso.mostrarContenidoArchivo("curso.json");
          ///DESCERIALIZO EL CURSO ORIGINAL Y CREO UNO NUEVO
          Curso nuevoCurso = Curso.descerializar(jsonObject);
          System.out.println("-------------NUEVO CURSO--------------");
          ///MUESTRO EL NUEVO CURSO DESCERIALIZADO
          System.out.println(nuevoCurso);

          break;

        case 3:
          biblioteca.agregarLibro(libro);
          biblioteca.agregarLibro(libro2);
          biblioteca.agregarLibro(libro3);
          System.out.println("--------------LISTA DE LIBROS DE LA BIBLIOTECA--------------");
          biblioteca.recorrerLista();

          File archivo3 = biblioteca.crearArchivo("biblioteca.json");

          ///SERIALIZO LA BIBLIOTECA
          JSONObject jsonObject1 = biblioteca.serializar();

          ///GUARDO LA SERIALIZACION DENTRO DEL ARCHIVO .JSON
          biblioteca.cargarArchivoJSON(jsonObject1);
          System.out.println("ARCHIVO BIBLIOTECA.JSON");
          biblioteca.mostrarArchivoJSON("biblioteca.json");

          ///CREO UNA NUEVA BIBLIOTECA DESCERIALIZANDO EL JSON OBJECT DE LA BIBLIOTECA ANTERIOR
          Biblioteca nuevaBiblioteca = Biblioteca.descerializar(jsonObject1);

          System.out.println("-----NUEVA BIBLIOTECA------");
          System.out.println(nuevaBiblioteca);







          System.out.println("Antes de eliminar:");
          nuevaBiblioteca.recorrerLista();

          ///ELIMINO UN LIBRO
          nuevaBiblioteca.eliminarLibro(libro2);


          System.out.println("Después de eliminar:");
          nuevaBiblioteca.recorrerLista();
              ///CREO UN JSON OBJECT ASI GUARDO LA SERIALIZACION DE LA NUEVA BIBLIOTECA
          JSONObject jsonObject2 = nuevaBiblioteca.serializar();
          ///CARGO LA SERIALIZACION EN JSON OBJECT AL ARCHIVO .JSON
          nuevaBiblioteca.cargarArchivoJSON(jsonObject2);
          System.out.println("ARCHIVO MODIFICADO .JSON");
          ///MUESTO EL ARCHIVO .JSON
          Biblioteca.mostrarArchivoJSON("biblioteca.json");


          break;

        case 4:
          Empleado empleado = new Empleado(1,"ignacio",15000,"programacion");
          Empleado empleado2 = new Empleado(2,"arian",3491000,"marketing");
          Empleado empleado3 = new Empleado(2,"lauty",1022294,"IT");

          JSONArray jsonArray = new JSONArray();
          jsonArray.put(empleado.serializar());
          jsonArray.put(empleado2.serializar());
          jsonArray.put(empleado3.serializar());


          File archivoEmpleado = Empleado.crearArchivo("empleado.json");
          Empleado.cargarArchivoJSON(jsonArray);
          System.out.println("-----------ARCHIVO EMPLEADOS.JSON");
          Empleado.mostrarArchivoJSON("empleado.json");

          break;

        case 5:
          System.out.println("SALIENDO DEL SISTEMA...");
          System.exit(0);
          break;

        default:
          System.out.println("opcion no valida.");
          break;
      }


    }while(opc < 6);

    }
  }
