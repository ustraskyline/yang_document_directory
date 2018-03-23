import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		getMethodAnnotation();
	}
	
	public static void getConstructorAnnotation() {
		Record record = new Record(0107, "yangjia");
		Constructor[] declaredConstructors = record.getClass().getDeclaredConstructors();
		for(int i = 0; i < declaredConstructors.length; i++) {
			 Constructor constructor = declaredConstructors[i];
			 if(constructor.isAnnotationPresent(Constructor_Annotation.class)) {
				 Constructor_Annotation ca = (Constructor_Annotation) constructor.getAnnotation(Constructor_Annotation.class);
				 System.out.println(ca.value());
			 }
			 
			 //一个参数可能有多个Annotation, 将该参数所有Annotation放到数组中，再将参数列表中所有参数的Annotation数组
			 //组织到一个数组中, 即得到二维数组
			 Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
			 
			 System.out.println("parameterArrayLength=" + parameterAnnotations.length);
			 for(int j = 0; j < parameterAnnotations.length; j++) {
				 int length = parameterAnnotations[j].length; 
				 System.out.println("parameterlength=" + length);
				 if(length == 0) {
					 System.out.println("===>未添加Annotation参数");
				 }else {
					 for(int k = 0; k < length; k++) { 
						//第(j+1)个参数的Annotation数组中的第(k+1)个Annotation
						 Field_Method_Parameter_Annotation pa = (Field_Method_Parameter_Annotation) parameterAnnotations[j][k]; 
						 System.out.println("   "  +  pa.describe());
						 System.out.println("   "  +  pa.type());
					 }
				 }
			 }
			 
			 System.out.println("=====================================================");
		}
	}
	
	public static void getFieldAnnotation() {
		Record record = new Record(0107, "yangjia");
		Field[] declaredFields = record.getClass().getDeclaredFields();
		 
		for(int i = 0; i < declaredFields.length; i++) {
			Field field = declaredFields[i];
			
			if(field.isAnnotationPresent(Field_Method_Parameter_Annotation.class)) {
				Field_Method_Parameter_Annotation fa = field.getAnnotation(Field_Method_Parameter_Annotation.class);
				System.out.println(" ==> " + fa.describe());
				System.out.println("    " + fa.type());
			}
		}
	}
	
	public static void getMethodAnnotation() {
		Record record = new Record(0107, "yangjia");
		Method[] declaredMethods = record.getClass().getDeclaredMethods();
		 
		for(int i = 0; i < declaredMethods.length; i++) {
			Method method = declaredMethods[i];
			
			if(method.isAnnotationPresent(Field_Method_Parameter_Annotation.class)) {
				Field_Method_Parameter_Annotation fa = method.getAnnotation(Field_Method_Parameter_Annotation.class);
				System.out.println(" ==> " + fa.describe());
				System.out.println("    " + fa.type());
			}
			
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for(int j = 0; j < parameterAnnotations.length; j++) {
				int length = parameterAnnotations[j].length;
				if(length == 0 ) {
					System.out.println("第" + (j+1) + "个参数未添加Annotation");
				}else {
					for(int k = 0; k < length; k++) {
						//获得第(j+1)个参数的Annotation数组中第(k+1)个Annotation
						Field_Method_Parameter_Annotation fa2 = (Field_Method_Parameter_Annotation) parameterAnnotations[j][k];
						System.out.println("***>" + fa2.describe());
						System.out.println("###>" + fa2.type());
					}
				}
			}
		}
	}
}
