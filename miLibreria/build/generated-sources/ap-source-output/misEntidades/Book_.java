package misEntidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-07-29T21:51:04")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Float> price;
    public static volatile SingularAttribute<Book, Boolean> illustrations;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, Integer> nbOfPage;

}