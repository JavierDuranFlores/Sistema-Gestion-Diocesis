package mx.unach.repositorio.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Servicio;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-28T16:16:41")
@StaticMetamodel(Coro.class)
public class Coro_ { 

    public static volatile SingularAttribute<Coro, String> pianistaprincipal;
    public static volatile SingularAttribute<Coro, Date> creado;
    public static volatile SingularAttribute<Coro, Short> id;
    public static volatile ListAttribute<Coro, Servicio> servicioList;
    public static volatile SingularAttribute<Coro, String> nombre;
    public static volatile SingularAttribute<Coro, String> conductor;
    public static volatile SingularAttribute<Coro, Short> miembros;
    public static volatile ListAttribute<Coro, Misa> misaList;

}