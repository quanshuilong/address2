package com.tscloud.common.main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


public class StartUp {
	
	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		System.out.println("======================================");
		System.out.println("starting......");
		System.out.println("======================================");
		String excp = System.getProperty("paas.classpath");
		String classPath=System.getenv("classpath");
		String[] pathes = excp.split(";");
		List<URL> urls = new ArrayList<URL>();

		for (String cp : pathes) {
			File cpdir = new File(cp);
			if (!cpdir.exists() || !cpdir.isDirectory()) {
				System.err
				.println("Please set system property \"paas.classpath\" properly, pointed to local directories, seperated by ';'!");
			} else {
				getJars(urls, cpdir);
			}
		}
		URL[] urlArray = new URL[urls.size()];
		for (int i = 0; i < urlArray.length; i++) {
			urlArray[i] = urls.get(i);
		}
		
		URLClassLoader cl = new URLClassLoader(urlArray);
		Thread.currentThread().setContextClassLoader(cl);

        //通用 启动jetty
        Class clz = Class.forName("com.tscloud.common.main.StartJetty", false, cl);
        Method method =  clz.getDeclaredMethod("getInstance");
        method.setAccessible(true);
        Object instance = method.invoke(clz);
        Method m = clz.getDeclaredMethod("startJetty");
        m.invoke(instance);

	}
	
	private static void getJars(List<URL> urls, File cpdir)throws MalformedURLException{
	   System.out.println("Loading JAR remote from " + cpdir);
	   
	   for (File f : cpdir.listFiles()) {
	       if(f.isDirectory())
	       {
	           getJars(urls, f);
	       }
	   	if (f.getName().endsWith(".jar") && f.getName().indexOf("tuscany-sca-all-")==-1 ) {
	   		URL url = f.toURI().toURL();
	   		urls.add(url);
				System.out.println("\t-->Append " + f.getName());
	   	}
	   }
	}
}
