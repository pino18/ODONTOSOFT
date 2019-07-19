package BaseDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import gestorAplicacion.users.Acompa�ante;
import gestorAplicacion.users.AdminUser;
import gestorAplicacion.users.Paciente;
import gestorAplicacion.users.User;
import uiMain.MenuDeConsola;
import uiMain.OpcionDeMenu;

public class Data {
	public static HashMap<String, User> users = new HashMap<String, User>();
	public static HashMap<String, MenuDeConsola> menus = new HashMap<String, MenuDeConsola>();
	public static HashMap<String, OpcionDeMenu> operations = new HashMap<String, OpcionDeMenu>();
	
	public static void loadData() {
		createFilesAndDirs();
		String ruta = System.getProperty("user.dir")+"\\src\\temp\\";
		loadUsers(ruta);
		loadAdminUsers(ruta);
		loadPaciUsers(ruta);
		loadAcomUsers(ruta);
		loadMenus(ruta);
	}
	
	private static void loadUsers(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"users.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] user = line.split(";");
            		String username = user[0];
            		String identificacion = user[1];
            		String fullname = user[2];
            		String edad = user[3];
            		String telefono = user[4];
            		String sexo = user[5];
            		String email = user[6];
            		String password = user[7];
            		new User(identificacion, fullname, edad, telefono, sexo, username, email, password);
            	}
            }
            br.close();
        }catch(Exception e){
            //Error al leer
        }
	}
	
	private static void loadPaciUsers(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"paciUsers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] user = line.split(";");
            		String username = user[0];
            		String identificacion = user[1];
            		String fullname = user[2];
            		String edad = user[3];
            		String telefono = user[4];
            		String sexo = user[5];
            		String email = user[6];
            		String password = user[7];
            		new Paciente(identificacion, fullname, edad, telefono, sexo, username, email, password);
            	}
            }
            br.close();
        }catch(Exception e){
            //Error al leer
        }
	}
	
	private static void loadAcomUsers(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"acompUsers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] user = line.split(";");
            		String username = user[0];
            		String identificacion = user[1];
            		String fullname = user[2];
            		String edad = user[3];
            		String telefono = user[4];
            		String sexo = user[5];
            		String email = user[6];
            		String password = user[7];
            		String usurpa = user[9];
            		String parentezco = user[8];
            		User.newUser(new Acompa�ante(usurpa, parentezco), identificacion, fullname, edad, telefono, sexo, username, email, password);
            	}
            }
            br.close();
        }catch(Exception e){
            //Error al leer
        }
	}
	
	private static void loadAdminUsers(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"adminUsers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] user = line.split(";");
            		String username = user[0];
            		String identificacion = user[1];
            		String fullname = user[2];
            		String edad = user[3];
            		String telefono = user[4];
            		String sexo = user[5];
            		String email = user[6];
            		String password = user[7];
            		new AdminUser(identificacion, fullname, edad, telefono, sexo, username, email, password);
            	}
            }
            br.close();
        }catch(Exception e){
        	//Error al leer
        }
	}
	
	private static void loadMenus(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"usersMenus.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] menu = line.split(";");
            		User user = User.getUserByUsername(menu[0]);
            		//slice de arrays
            		String[] operations = Arrays.copyOfRange(menu, 1, menu.length);
            		MenuDeConsola.newMenu(user, operations);
            	}
            }
            br.close();
        }catch(Exception e){
        	//Error al leer
        }
	}
	
	public static void saveData() {
		createFilesAndDirs();
		String ruta = System.getProperty("user.dir")+"\\src\\temp\\";
		saveUsers(ruta);
		saveMenus(ruta);
	}
	
	private static void saveUsers(String ruta){
		try {
            FileWriter fw = new FileWriter(ruta+"users.txt");
            FileWriter fwAdmin = new FileWriter(ruta+"adminUsers.txt");
            FileWriter fwAcom = new FileWriter(ruta+"acompUsers.txt");
            FileWriter fwPa = new FileWriter(ruta+"paciUsers.txt");
            PrintWriter pw = new PrintWriter(fw);
            PrintWriter pwAdmin = new PrintWriter(fwAdmin);
            PrintWriter pwAcom = new PrintWriter(fwAcom);
            PrintWriter pwPa = new PrintWriter(fwPa);
    		for (Map.Entry<String, User> user : users.entrySet()) {
    			User userObj = user.getValue();
    			String line = userObj.getUsername()+";";
    			line += userObj.getIdentificacion()+";";
    			line += userObj.getFullname()+";";
    			line += userObj.getEdad()+";";
    			line += userObj.getTelefono()+";";
    			line += userObj.getSexo()+";";
    			line += userObj.getEmail()+";";
    			line += userObj.getPassword();
    			if(userObj instanceof AdminUser) {
    				pwAdmin.println(line);
					
				}
    			else if(userObj instanceof Acompa�ante) {
    				line +=";"+ ((Acompa�ante) userObj).getParentezco()+";";
    				line += ((Acompa�ante) userObj).getPaciente().getUsername();
    				pwAcom.println(line);
    			}
    			else if(userObj instanceof Paciente) {
    				pwPa.println(line);
    			}
    			else {
					pw.println(line);
    			}
    		}
            pw.close();
            pwAdmin.close();
            pwAcom.close();
            pwPa.close();
            
        } catch (IOException ioObj) {
        	//Ocurrio algo al guardar en txt los datos
        }
	}
	
	private static void saveMenus(String ruta){
		try {
            FileWriter fw = new FileWriter(ruta+"usersMenus.txt");
            PrintWriter pw = new PrintWriter(fw);
    		for (Map.Entry<String, MenuDeConsola> menu : menus.entrySet()) {
    			MenuDeConsola menuObj = menu.getValue();
    			String line = menuObj.getUser().getUsername()+";";
    			for (String  opt : menuObj.getOperations()) {
    				line += opt+";";
				}
    			//Correccion por el ; extra
    			pw.println(line.substring(0,(line.length()-1)));
    		}
            pw.close();
            
        } catch (IOException ioObj) {
        	//Ocurrio algo al guardar en txt los datos
        }
	}
	
	private static void createFilesAndDirs() {
		try {
		String ruta = System.getProperty("user.dir")+"\\src\\temp\\";
		File directory = new File(ruta);
	    if (! directory.exists()){
	        directory.mkdir();
	        File usersRegisteredFile = new File(ruta+"users.txt");
			File adminUsersFile = new File(ruta+"adminUsers.txt");
			File acompUsersFile = new File(ruta+"acompUsers.txt");
			File paciUsersFile = new File(ruta+"paciUsers.txt");
			File usersMenus = new File(ruta+"usersMenus.txt");
			usersRegisteredFile.createNewFile();
			adminUsersFile.createNewFile();
			acompUsersFile.createNewFile();
			usersMenus.createNewFile();
			paciUsersFile.createNewFile();
	    }
	    }
		catch(IOException e){
			//Ocurrio algo al crear las carpetas y los archivos
		}
	}
}