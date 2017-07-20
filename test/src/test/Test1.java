package test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class Test1 {
	 public static final String CLASS_CONTENT = "public class HelloWorld{public static void run(){##;}}";  
	  
	    public static void main(String[] args) throws Exception {  
	        Scanner scanner =new Scanner(System.in);  
	        String code = scanner.nextLine();  
	        code = CLASS_CONTENT.replace("##", code);  
	          
	        //获取编译器.注意,运行时需要jdk,单纯的jre没有编译器  
	        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
	        JavaFileObject fileObject = new JavaStringObject("HelloWorld",  
	                code);  
	        //编译过程  
	        CompilationTask task = javaCompiler.getTask(null, null, null,  
	                Arrays.asList("-d", "./bin"), null, Arrays.asList(fileObject));  
	        if (!task.call()) {  
	            System.out.println("编译失败!");  
	        } else {  
	            System.out.println("编译成功！");  
	  
	            //成功以后，就利用发射来执行这个类了  
	            URL[] urls = new URL[] { new URL("file:/" + "./bin/") };  
	            URLClassLoader classLoader = new URLClassLoader(urls);  
	            Class<?> clazz = classLoader.loadClass("HelloWorld");  
	            Method method = clazz.getDeclaredMethod("run");  
	            method.invoke(clazz.newInstance());  
	        }  
	  
	    }  
	  
	    static final class JavaStringObject extends SimpleJavaFileObject {  
	        private String code;  
	  
	        public JavaStringObject(String name, String code) {  
	            super(URI.create(name + ".java"), Kind.SOURCE);  
	            this.code = code;   
	        }  
	  
	        @Override  
	        public CharSequence getCharContent(boolean ignoreEncodingErrors)  
	                throws IOException {  
	            return code;  
	        }  
	    }  
}
