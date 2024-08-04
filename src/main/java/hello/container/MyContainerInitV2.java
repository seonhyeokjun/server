package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 set = " + set);
        System.out.println("MyContainerInitV2 servletContext = " + servletContext);

        if (set != null) {
            for (Class<?> aClass : set) {
                try {
                    AppInit appInit = (AppInit) aClass.getDeclaredConstructor().newInstance();
                    appInit.onStartup(servletContext);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
            }
        }
    }
}
