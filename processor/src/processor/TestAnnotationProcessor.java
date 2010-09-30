package processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Name;

@SupportedAnnotationTypes(value = { "*" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TestAnnotationProcessor extends AbstractProcessor {

	private int tally;
	private Trees trees;
	private TreeMaker make;
	private Name.Table names;
	
	public synchronized void init(ProcessingEnvironment env) {
		super.init(env);
		trees = Trees.instance(env);
		Context context = ((JavacProcessingEnvironment) env).getContext();
		make = TreeMaker.instance(context);
		names = Name.Table.instance(context);
		tally = 0;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
				"Annotation Processor started");
		for (TypeElement element : annotations) {
			processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
					element.getQualifiedName());

			Set<? extends Element> set = roundEnv
					.getElementsAnnotatedWith(element);
			for (Element annotatedElement : set) {
				processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
						"\t" + annotatedElement);
				boolean isField = annotatedElement.getKind().equals(
						ElementKind.FIELD);
				if (isField) {
					Element parent = annotatedElement.getEnclosingElement();
				}
			}
		}
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
				"Annotation Processor end");
		return true;
	}
}
