package org.beginningee6.book.chapter04.ex30;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.beginningee6.book.chapter04.ex30.Address30;

@Generated("EclipseLink-2.1.2.v20101206-r8635 @ Sun Jul 07 17:44:12 CEST 2013")
@StaticMetamodel(Customer30.class)
public class Customer30_ { 

    public static volatile SingularAttribute<Customer30, Long> id;
    public static volatile SingularAttribute<Customer30, String> lastName;
    public static volatile SingularAttribute<Customer30, String> email;
    public static volatile SingularAttribute<Customer30, Address30> address;
    public static volatile SingularAttribute<Customer30, Integer> age;
    public static volatile SingularAttribute<Customer30, String> firstName;

}