package zoli.reflection;

import java.lang.annotation.Annotation;

/**
 * Created by Zoli on 12/09/2016.
 */
public class StartApp {

    public static void main(String ... args){

         Class<Book> obj = Book.class;
        Class<Book> obj1 = Book.class;
        Class<Book> obj2 = Book.class;

        Class<StartApp> sa = StartApp.class;
        Class<StartApp> sa2 = StartApp.class;

        Annotation annotation = obj.getAnnotation(Author.class);
        Author a = (Author) annotation;

        System.out.printf("%nFirst :%s", a.first());
        System.out.printf("%nLast :%s", a.last());


    }
}
