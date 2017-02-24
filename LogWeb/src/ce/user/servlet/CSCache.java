package ce.user.servlet;

public interface CSCache {


    public Object get(Object obj);

    public void put(Object obj, Object obj1);

    public boolean remove(Object obj);

    public void removeAll();
    public String toString();
    public boolean isEnabled();

}
