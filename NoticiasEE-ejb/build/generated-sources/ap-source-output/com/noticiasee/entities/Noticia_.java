package com.noticiasee.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-22T17:57:26")
@StaticMetamodel(Noticia.class)
public class Noticia_ { 

    public static volatile SingularAttribute<Noticia, String> imgLink;
    public static volatile SingularAttribute<Noticia, String> author;
    public static volatile SingularAttribute<Noticia, String> shortDesc;
    public static volatile SingularAttribute<Noticia, Integer> id;
    public static volatile SingularAttribute<Noticia, String> title;
    public static volatile SingularAttribute<Noticia, String> body;
    public static volatile SingularAttribute<Noticia, Date> posted;

}