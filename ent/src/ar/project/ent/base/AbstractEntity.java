package ar.project.ent.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import ar.project.ent.annotations.ColumnName;

public abstract class AbstractEntity<ID extends Serializable> {

	protected static final String EMPTY = new String();
	
	/**Inject the table name in its correlative field
	 * @param clazz
	 */
	protected static void InjectColumnName(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		String columnName = new String();
		for (Field field : fields) {
			ColumnName annotation = field.getAnnotation(ColumnName.class);
			if (annotation != null) {
				field.setAccessible(Boolean.TRUE);
				Field modifiersField = null;
				try {
					modifiersField = Field.class.getDeclaredField("modifiers");
					modifiersField.setAccessible(Boolean.TRUE);
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for (Method method : methods) {
					if (method.getName().equalsIgnoreCase(
							"get" + field.getName())) {
						Column notation = method.getAnnotation(Column.class);
						if(notation != null){
							columnName = notation.name();
							break;
						}
						JoinColumn jcnotation = method.getAnnotation(JoinColumn.class);
						if (jcnotation != null){
							columnName = jcnotation.name();
						}
					}
				}

				if (columnName.equals(new String())) {
					field.setAccessible(Boolean.FALSE);
					continue;
				}

				try {
					modifiersField.setInt(field, field.getModifiers()
							& ~Modifier.FINAL);
					field.set(null, columnName);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				field.setAccessible(Boolean.FALSE);
				modifiersField.setAccessible(Boolean.FALSE);

			}
		}
	}

}
