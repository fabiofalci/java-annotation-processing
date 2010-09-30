package app;

import java.util.List;

import javax.annotation.PreDestroy;

/**
 * @author Fabio Falci
 *
 */
@SuppressWarnings("rawtypes")
public class AnnotatedJavaFile {

	@SuppressWarnings("unused")
	private List list;
	
	@PreDestroy
	public void foo() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("executing...");
	}
}
