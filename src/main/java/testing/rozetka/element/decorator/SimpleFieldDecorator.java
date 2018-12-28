package testing.rozetka.element.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import testing.rozetka.element.Element;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public final class SimpleFieldDecorator extends DefaultFieldDecorator {
    public SimpleFieldDecorator(SearchContext context) {
        super(new DefaultElementLocatorFactory(context));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<? extends Element> listArgument = null;
        if (!Element.class.isAssignableFrom(field.getType())
                && (listArgument = getListArgument(field)) == null) {
            return super.decorate(loader, field);
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        try {
            if (listArgument == null) {
                Element ret = (Element) field.getType().newInstance();
                ret.setBase(proxyForLocator(loader, locator));
                return ret;
            } else {
                InvocationHandler handler = new SimpleLocatingElementListHandler(locator, listArgument);
                return Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Class<? extends Element> getListArgument(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return null;
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }

        if (!field.isAnnotationPresent(FindBy.class)
                && !field.isAnnotationPresent(FindBys.class)
                && !field.isAnnotationPresent(FindAll.class)) {
            return null;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        if (Element.class.isAssignableFrom((Class<?>) listType)) {
            //noinspection unchecked
            return (Class<? extends Element>) listType;
        }

        return null;
    }

    private static class SimpleLocatingElementListHandler implements InvocationHandler {
        private final ElementLocator locator;
        private final Class<? extends Element> clazz;

        SimpleLocatingElementListHandler(ElementLocator locator, Class<? extends Element> clazz) {
            this.locator = locator;
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            List<Element> elements = new ArrayList<>();
            for (WebElement webElement : locator.findElements()) {
                Element ret = clazz.newInstance();
                ret.setBase(webElement);
                elements.add(ret);
            }

            try {
                return method.invoke(elements, objects);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }
}
