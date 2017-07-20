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
	          
	        //��ȡ������.ע��,����ʱ��Ҫjdk,������jreû�б�����  
	        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
	        JavaFileObject fileObject = new JavaStringObject("HelloWorld",  
	                code);  
	        //�������  
	        CompilationTask task = javaCompiler.getTask(null, null, null,  
	                Arrays.asList("-d", "./bin"), null, Arrays.asList(fileObject));  
	        if (!task.call()) {  
	            System.out.println("����ʧ��!");  
	        } else {  
	            System.out.println("����ɹ���");  
	  
	            //�ɹ��Ժ󣬾����÷�����ִ���������  
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
