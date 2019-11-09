package ar.project.persist.dynConfigs;


public class ContextHolder {
    private static final ThreadLocal <Object> holder = new ThreadLocal <Object> ();

    public static void setDSType (DataSourceType dstype) {
        holder.set (dstype);
    }

    public static DataSourceType getDSType () {
        return (DataSourceType) holder.get ();
    }

    public static void clearDSType () {
        holder.remove ();
    }

}