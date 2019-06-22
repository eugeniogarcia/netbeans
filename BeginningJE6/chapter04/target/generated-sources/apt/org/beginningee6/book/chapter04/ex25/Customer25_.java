package org.beginningee6.book.chapter04.ex25;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.beginningee6.book.chapter04.ex25.Address25;

@Generated("EclipseLink-2.1.2.v20101206-r8635 @ Sun Jul 07 17:44:12 CEST 2013")
@StaticMetamodel(Customer25.class)
public class Customer25_ { 

    public static volatile SingularAttribute<Customer25, Long> id;
    public static volatile SingularAttribute<Customer25, String> lastName;
    public static volatile SingularAttribute<Customer25, String> email;
    public static volatile SingularAttribute<Customer25, Address25> address;
    public static volatile SingularAttribute<Customer25, Integer> age;
    public static volatile SingularAttribute<Customer25, String> firstName;

}