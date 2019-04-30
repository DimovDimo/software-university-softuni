package entities.factories;

import entities.airplanes.LightAirplane;
import entities.airplanes.MediumAirplane;
import entities.airplanes.interfaces.FlyingMachine;
import entities.factories.interfaces.AirplaneFactory;

import java.lang.reflect.Constructor;

public class AirplaneWorkshop implements AirplaneFactory {
    @Override
    public FlyingMachine createAirplane(String type) {
//        FlyingMachine result = null;
//
//        result.getClass().cast(type);
//        try {
//            result.getClass().cast(type);
//        } catch (Exception ignored) {
//            ;
//        }

//        return result.getClass().cast(type);
//        return (FlyingMachine) Activator.class.type);

//        try
//        {
//            return (FlyingMachine)typeof(type).GetConstructor(new Type[] { }).Invoke(new object[] { });
//        }
//        catch
//        {
//            return default(T);
//        }

//        FlyingMachine t = FlyingMachine.class.newInstance(strNamesapace);
//        return  Activator.CreateInstance(t);
//        return FlyingMachine.class.getClass().
//        if (type.equals("LightAirplane")){
//            return new LightAirplane();
//        }
//
//        if (type.equals("MediumAirplane")){
//            return new MediumAirplane();
//        }

//        FlyingMachine instance =
//                (FlyingMachine)System.Reflection.Assembly.GetExecutingAssembly().CreateInstance(
//                        typeName: objectType.FulName, // string including namespace of the type
//                ignoreCase: false,
//                bindingAttr: BindingFlags.Default,
//                binder: null,  // use default binder
//                args: new object[] { args, to, constructor },
//                culture: null, // use CultureInfo from current thread
//                activationAttributes: null
//    );

//        return  (FlyingMachine) Activator.CreateInstance(GetType());

//        Object object = null;
//        try {
//            Class<?> clazz = Class.forName(type);
//            Constructor<?>[] ctor = clazz.getDeclaredConstructors();
//            object = ctor[0].newInstance(new Object[] {});
////            return (FlyingMachine) Class.forName(type).getConstructor(String.class).newInstance();
//        } catch (Exception ignored) {
//            ;
//        }
//
//        return (FlyingMachine) object;

        FlyingMachine flyingMachine = null;
        if (type.equals("LightAirplane")){
            flyingMachine = new LightAirplane();
        } else {
            flyingMachine = new MediumAirplane();
        }

        return flyingMachine;
    }
}
