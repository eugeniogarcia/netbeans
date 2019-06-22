package org.beginningee6.book.chapter04.ex07;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.beginningee6.book.chapter04.ex07.Address07;

@Generated("EclipseLink-2.1.2.v20101206-r8635 @ Sun Jul 07 17:44:12 CEST 2013")
@StaticMetamodel(Customer07.class)
public class Customer07_ { 

    public static volatile SingularAttribute<Customer07, Long> id;
    public static volatile SingularAttribute<Customer07, String> lastName;
    public static volatile SingularAttribute<Customer07, String> email;
    public static volatile SingularAttribute<Customer07, Address07> address;
    public static volatile SingularAttribute<Customer07, String> firstName;

}