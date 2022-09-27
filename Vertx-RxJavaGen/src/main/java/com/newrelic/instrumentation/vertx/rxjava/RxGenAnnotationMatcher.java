package com.newrelic.instrumentation.vertx.rxjava;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import com.newrelic.agent.deps.org.objectweb.asm.ClassReader;
import com.newrelic.agent.deps.org.objectweb.asm.tree.AnnotationNode;
import com.newrelic.agent.deps.org.objectweb.asm.tree.ClassNode;
import com.newrelic.agent.instrumentation.classmatchers.ClassMatcher;
import com.newrelic.api.agent.NewRelic;

public class RxGenAnnotationMatcher extends ClassMatcher {
	
	private static final String rxGenAnnotation = "io.vertx.lang.rx.RxGen".replace('.', '/');

	@Override
	public boolean isMatch(ClassLoader loader, ClassReader cr) {
		ClassNode node = new ClassNode();
		cr.accept(node, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_FRAMES);
		List<AnnotationNode> annotations = node.visibleAnnotations;
		if (annotations != null) {
			for (AnnotationNode annotationNode : annotations) {
				String desc = annotationNode.desc;
				NewRelic.getAgent().getLogger().log(Level.FINE, "Check annotation {0} for class {1}",desc,cr.getClassName());
				
				if (desc.contains(rxGenAnnotation)) {
					NewRelic.getAgent().getLogger().log(Level.FINE, "Found RxGen Annotation for class {0}",
							cr.getClassName());
					return true;
				}
			} 
			NewRelic.getAgent().getLogger().log(Level.FINE, "Did not find RxGen Annotation in visible for class {0}",cr.getClassName());
		}

		annotations = node.invisibleAnnotations;
		if (annotations != null) {
			for (AnnotationNode annotationNode : annotations) {
				String desc = annotationNode.desc;
				if (desc.contains(rxGenAnnotation)) {
					NewRelic.getAgent().getLogger().log(Level.FINE, "Found RxGen Annotation for class {0}",
							cr.getClassName());
					return true;
				}
			} 
			NewRelic.getAgent().getLogger().log(Level.FINE, "Did not find RxGen Annotation in invisible for class {0}",cr.getClassName());
		}
		
		return false;
	}

	@Override
	public boolean isMatch(Class<?> clazz) {
		if (clazz != null) {
			Annotation[] clazzAnnotations = clazz.getAnnotations();
			if (clazzAnnotations.length > 0) {
				for (Annotation annotation : clazzAnnotations) {
					Class<? extends Annotation> aType = annotation.annotationType();
					if (aType.getName().equals(rxGenAnnotation)) {
						return true;
					}
				}

			} 
		}
		return false;
	}

	@Override
	public Collection<String> getClassNames() {
		return Collections.emptyList();
	}

}
