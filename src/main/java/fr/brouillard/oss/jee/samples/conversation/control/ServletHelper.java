package fr.brouillard.oss.jee.samples.conversation.control;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.util.Collection;

@ApplicationScoped
public class ServletHelper {
	@Inject
	private ServletContext context;

	public String getServletURL(Class<?> servletClass) {
		Collection<String> mappings = context.getServletRegistration(servletClass.getCanonicalName())
				.getMappings();
		assert (mappings.size() == 1);
		final String urlMapping = mappings.iterator().next();
		assert (urlMapping.charAt(0) == '/');
		return urlMapping.substring(1);
	}

}
