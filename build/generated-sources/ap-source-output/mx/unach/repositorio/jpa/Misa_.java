package mx.unach.repositorio.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-28T16:16:41")
@StaticMetamodel(Misa.class)
public class Misa_ { 

    public static volatile SingularAttribute<Misa, Sacerdote> sacerdoteid;
    public static volatile SingularAttribute<Misa, Coro> coroid;
    public static volatile SingularAttribute<Misa, String> hora;
    public static volatile SingularAttribute<Misa, Short> id;
    public static volatile ListAttribute<Misa, Servicio> servicioList;
    public static volatile SingularAttribute<Misa, Date> dia;

}