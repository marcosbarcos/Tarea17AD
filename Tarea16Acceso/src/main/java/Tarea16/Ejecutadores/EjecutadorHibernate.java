package Tarea16.Ejecutadores;

import Tarea16.Controlador.Controlador;
import Tarea16.Modelo.AlumnoDAO;
import Tarea16.Modelo.AlumnoImplementacionHiberante;
import Tarea16.Vista.ImplementacionVistaConsola;
import Tarea16.Vista.InterfazVista;

public class EjecutadorHibernate {
public static void main(String[] args) {
	
	 AlumnoDAO modelo = new AlumnoImplementacionHiberante();
	 InterfazVista vista = new ImplementacionVistaConsola();
	 Controlador c = new Controlador();
	 c.ejecutar(modelo, vista);
	 
}
}
