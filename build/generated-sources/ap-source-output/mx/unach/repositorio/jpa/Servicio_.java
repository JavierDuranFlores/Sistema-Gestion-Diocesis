package mx.unach.repositorio.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Finanza;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-28T16:16:41")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> coronombre;
    public static volatile SingularAttribute<Servicio, Sacerdote> sacerdoteid;
    public static volatile SingularAttribute<Servicio, Coro> coroid;
    public static volatile SingularAttribute<Servicio, String> sacerdotenombre;
    public static volatile SingularAttribute<Servicio, Misa> misaid;
    public static volatile ListAttribute<Servicio, Finanza> finanzasList;
    public static volatile SingularAttribute<Servicio, Short> id;
    public static volatile SingularAttribute<Servicio, String> telefono;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile SingularAttribute<Servicio, String> lectura;

}