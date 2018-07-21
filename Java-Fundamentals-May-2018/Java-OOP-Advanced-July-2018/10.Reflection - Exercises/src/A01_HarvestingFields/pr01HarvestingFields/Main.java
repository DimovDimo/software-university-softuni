package A01_HarvestingFields.pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Field> fields = Arrays.asList(RichSoilLand.class.getDeclaredFields());
		while (true){
		    String line = reader.readLine();
		    if ("HARVEST".equals(line)){
		        break;
		    }
		    
		    switch (line){
                case "private":
                    printAllPrivateFields(fields);
                    break;
                case "protected":
                    printAllProtectedFields(fields);
                    break;
                case "public":
                    printAllPublicFields(fields);
                    break;
                case "all":
                    printAllFields(fields);
                    break;
            }
		}
	}

    private static void printAllPublicFields(List<Field> fields) {
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers())){
                printField(field);
            }
        }
    }

    private static void printAllProtectedFields(List<Field> fields) {
        for (Field field : fields) {
            if (Modifier.isProtected(field.getModifiers())){
                printField(field);
            }
        }
    }

    private static void printAllPrivateFields(List<Field> fields) {
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())){
                printField(field);
            }
        }
    }

    private static void printAllFields(List<Field> fields) {
        for (Field field : fields) {
            printField(field);
        }
    }

    private static void printField(Field field) {
        System.out.println(String.format("%s %s %s",
                getFieldAccessModifier(field),
                getFieldType(field),
                getFieldName(field)));
    }

    public static String getFieldName(Field field){
	    return field.getName();
    }

    public static String getFieldType(Field field){
        return field.getType().getSimpleName();
    }

    public static String getFieldAccessModifier(Field field){
        int modifier = field.getModifiers();
        if (Modifier.isPrivate(modifier)){
            return "private";
        } else if (Modifier.isProtected(modifier)){
            return "protected";
        } else if (Modifier.isPublic(modifier)){
            return "public";
        }

        return "Other Access Modifier";
    }
}
