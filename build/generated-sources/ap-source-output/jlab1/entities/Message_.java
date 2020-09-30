package jlab1.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T17:48:59")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, String> name;
    public static volatile SingularAttribute<Message, Integer> id;
    public static volatile SingularAttribute<Message, Date> time;
    public static volatile SingularAttribute<Message, String> message;

}