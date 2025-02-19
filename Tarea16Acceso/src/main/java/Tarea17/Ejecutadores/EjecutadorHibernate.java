package Tarea17.Ejecutadores;

import Tarea17.Controlador.Controlador;
import Tarea17.Modelo.AlumnoDAO;
import Tarea17.Modelo.AlumnoImplementacionHiberante;
import Tarea17.Vista.ImplementacionVistaConsola;
import Tarea17.Vista.InterfazVista;

public class EjecutadorHibernate {
public static void main(String[] args) {
	
	 AlumnoDAO modelo = new AlumnoImplementacionHiberante();
	 InterfazVista vista = new ImplementacionVistaConsola();
	 Controlador c = new Controlador();
	 c.ejecutar(modelo, vista);
	 
}
}
