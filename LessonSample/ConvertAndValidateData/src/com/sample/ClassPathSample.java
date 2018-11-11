package com.sample;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassPathSample {

	public static void main(String[] args) throws IOException {
		
		ClassLoader effectiveClassLoader = ClassPathSample.class.getClassLoader();
		
		System.out.println(effectiveClassLoader);
        URL[] classPath = ((URLClassLoader) effectiveClassLoader).getURLs();
        int amount = 0;
        for(URL url : classPath) {
        	System.out.println(url.getProtocol() + " " + url.getFile() + " " + new File(url.getFile()).isFile());
        	if(url.getPath().endsWith(".jar")) {
        		JarURLConnection jarURLConnection =(JarURLConnection) new URL("jar:" + url + "!/").openConnection();
        		JarFile jarFile = jarURLConnection.getJarFile();
        		Enumeration<JarEntry> jarEntries = jarFile.entries();
        		while(jarEntries.hasMoreElements()) {
        			JarEntry jarEntry = jarEntries.nextElement();
        			//System.out.println(jarEntry.getName() + " " + jarEntry.isDirectory());
        			amount++;
        		}
        	}
        }
        System.out.println(amount);

	}

}
