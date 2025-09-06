import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Main {

	static class Person implements PropertyChangeListener {
	
		private String name;
		private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

		public Person() {
			this.pcs.addPropertyChangeListener(this);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			String old = this.name;
			this.name = name;
			pcs.firePropertyChange("name", old, name);
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			System.out.println(event);
		}
	}

	public static void main(String[] args){
		System.out.println("Hello!");
		Person person = new Person();
		person.setName("teste");
		person.setName("teste22");
	}
}
