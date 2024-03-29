package uiMain.operations;

import java.util.Scanner;

import BaseDatos.Data;
import gestorAplicacion.users.AdminUser;
import gestorAplicacion.users.User;
import uiMain.OpcionDeMenu;

public class NewAdmin extends OpcionDeMenu {//Crea un nuevo usuario de tipo administrador, hace validación de las credenciales y accede al metodo NewAdmin de la clase AminUSer
	
	public NewAdmin(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {//Hace pedido de las credenciales necesarias para la creación de un usuario de tipo Administrador, se hace una validación de la información que posteriormente será almacenada en la base de datos
		Scanner leer = new Scanner(System.in);
		String username = "";
		String password = "";
		while(true) {
			System.out.print("Ingrese su nombre de usuario: ");
			username = leer.next();
			if (User.getUserByUsername(username)!= null) {
				System.out.println("Usuario ya existente");
				continue;
			}
			System.out.print("Ingrese su contraseña: ");
			password = leer.next();
			System.out.print("Verifique su contraseña: ");
			if (!password.equals(leer.next())) {
				System.out.println("Las contraseñas no coinciden");
				continue;
			}
			break;
		}
		System.out.print("Ingrese su nombre y apellido: ");
		String fullname = leer.next();
		System.out.print("Ingrese su identificación: ");
		String identificacion = leer.next();
		System.out.print("Ingrese su edad: ");
		String edad = leer.next();
		System.out.print("Ingrese su telefono: ");
		String telefono = leer.next();
		System.out.print("Ingrese su sexo: ");
		String sexo = leer.next();
		System.out.print("Ingrese su email: ");
		String email = leer.next();
		System.out.println(AdminUser.newAdminUser(identificacion, fullname, edad, telefono, sexo, username, email, password));
	}

	@Override
	public String toString() {
		return "Crear nuevo usuario admin";
	}

}
