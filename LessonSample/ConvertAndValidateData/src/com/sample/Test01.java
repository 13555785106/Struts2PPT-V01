package com.sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.Map;

public class Test01 {

	public static void main(String[] args) throws IOException {
		URL url = ClassLoader.getSystemResource("com/sample");
		for(String str : new File(url.getFile()).list()) {
			System.out.println(str);
		}
		for(Map.Entry<String, String> entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("");
		while(urls.hasMoreElements()) {
			System.out.println(urls.nextElement());
		}
		ProtectionDomain pd = Test01.class.getProtectionDomain();
		  CodeSource cs = pd.getCodeSource();
		  System.out.println(cs.getLocation()+"FF");
	}

}
