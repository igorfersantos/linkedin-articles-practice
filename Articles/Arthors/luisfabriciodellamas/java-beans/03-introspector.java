import java.beans.*;

/*
 * A ideia desse arquivo é demonstrar o uso do Introspector para obter infor
 * mações de um JavaBean, com um adicional de também podem limitar o quão
 * profundo o Instropector deve ir na herança, nesse caso, indo até a classe
 * Object e assim não obtendo suas informações junto das outras classes da
 * herança.
 * */

public class Main {

  class MyBean {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
    }

    private String value;
    private String anotherValue;

    public String getValue() {
      return this.value;
    }

    public void setValue(String newValue) {
      String oldValue = this.value;
      this.value = newValue;
      this.pcs.firePropertyChange("value", oldValue, newValue);
    }

    public String getAnotherValue() {
      return this.anotherValue;
    }

    public void setAnotherValue(String newValue) {
      this.anotherValue = newValue;
    }
  }

  public static void main(String[] args) throws IntrospectionException {
    BeanInfo info = Introspector.getBeanInfo(MyBean.class, Object.class);

    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
      System.out.println(pd);
    }
  }
}
