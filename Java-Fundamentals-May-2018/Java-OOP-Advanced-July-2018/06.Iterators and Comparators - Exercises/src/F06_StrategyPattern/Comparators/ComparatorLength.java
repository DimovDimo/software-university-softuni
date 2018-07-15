package F06_StrategyPattern.Comparators;

import F06_StrategyPattern.Person;

import java.util.Comparator;

public class ComparatorLength implements Comparator<Person> {
    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        if (firstPerson.getName().length() == secondPerson.getName().length()){
            String firstPersonFirstLetter = String.valueOf(firstPerson
                    .getName()
                    .charAt(0));
            String secondPersonFirstLetter = String.valueOf(secondPerson
                    .getName()
                    .charAt(0));
            return firstPersonFirstLetter.compareToIgnoreCase(secondPersonFirstLetter);
        } else {
            return firstPerson.getName().length() - secondPerson.getName().length();
        }
    }
}
