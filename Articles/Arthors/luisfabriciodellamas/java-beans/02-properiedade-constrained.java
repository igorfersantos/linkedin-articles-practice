import java.beans.VetoableChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class Main {

	/* 
	 * Aqui utilizaremos uma propriedade "constrained", isso é, uma propriedade
	 * que possui restrições definidas por nós durante o código, para que por exemplo, possamos
	 * controlar que uma propriedade só pode ter seu valor alterado caso cumpra
	 * com as regras de negócio. Caso ela não cumpra, um PropertyVetoException
	 * é lançado, e a alteração não será realizada.
	 * */
	static class Person implements VetoableChangeListener {
	
		private String name;
		private final VetoableChangeSupport vcs = new VetoableChangeSupport(this);

		public Person() {
			this.vcs.addVetoableChangeListener(this);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			String old = this.name;
			try {
				this.vcs.fireVetoableChange("name",old,name);
				this.name = name;
			} catch (PropertyVetoException ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
			if (event.getNewValue().equals("teste22"))
				throw new PropertyVetoException("The new name can't be teste22", event);
			System.out.println(event);
		}
	}

	public static void main(String[] args){
		System.out.println("Hello!");
		Person person = new Person();
		person.setName("teste");
		person.setName("teste22");
		System.out.println(person.getName());
	}
}
