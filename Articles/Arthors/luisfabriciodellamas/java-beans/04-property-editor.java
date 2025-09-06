import java.beans.*;

public class Main {

	/*
	 Um {@link PropertyEditor} serve para converter valores
	 de propriedades entre objetos e representações
	 em texto. Isso é muito útil em ferramentas de
	 design que trabalham com valores textuais.

	 A forma mais prática é estender
	 PropertyEditorSupport e sobrescrever os
	 métodos setAsText e getAsText. Depois, o editor
	 pode ser registrado no PropertyEditorManager.
	 Esse mecanismo facilita a edição de propriedades
	 complexas em ambientes que só manipulam
	 strings.

	 Um PropertyEditor é útil quando queremos
	 transformar texto em objeto automaticamente.
	 Esse exemplo demonstra esse caso de uso com inteiros.
	 */

	static class IntEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) {
			setValue(Integer.parseInt(text));
		}
	}

	public static void main(String[] args) {
		PropertyEditor ed = new IntEditor();
		ed.setAsText(args[0]);
		System.out.println(ed.getValue());
	}
}
