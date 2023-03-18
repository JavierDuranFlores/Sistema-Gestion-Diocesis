package mx.unach.repositorio.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Servicio;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-28T16:16:41")
@StaticMetamodel(Sacerdote.class)
public class Sacerdote_ { 

    public static volatile SingularAttribute<Sacerdote, String> direccion;
    public static volatile SingularAttribute<Sacerdote, Short> id;
    public static volatile SingularAttribute<Sacerdote, String> telefono;
    public static volatile ListAttribute<Sacerdote, Servicio> servicioList;
    public static volatile SingularAttribute<Sacerdote, String> nombre;
    public static volatile SingularAttribute<Sacerdote, Short> tiempoministerio;
    public static volatile SingularAttribute<Sacerdote, String> contra;
    public static volatile ListAttribute<Sacerdote, Misa> misaList;
    public static volatile SingularAttribute<Sacerdote, Date> nacimiento;

}