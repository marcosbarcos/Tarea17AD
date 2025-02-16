package Tarea16.Ejecutadores;

import Tarea16.Modelo.AlumnoImplementacionBBDD;
import Tarea16.Vista.ImplementacionVistaConsola;
import Tarea16.Vista.InterfazVista;
import Tarea16.Controlador.Controlador;
import Tarea16.Modelo.AlumnoDAO;

public class EjecutadorBBDD {
public static void main(String[] args) {
	
	 AlumnoDAO modelo = new AlumnoImplementacionBBDD();
	 InterfazVista vista = new ImplementacionVistaConsola();
	 Controlador c = new Controlador();
	 c.ejecutar(modelo, vista);
	 
}
}
