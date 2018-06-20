package p03_Mankind;

public class Worker extends Human {
    private double weekSalary;
    private double workHoursPerDay;

    public Worker(String firstName, String lastName, double weekSalary, double workHoursPerDay) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkHoursPerDay(workHoursPerDay);
    }

    public double getWeekSalary() {
        return weekSalary;
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary < 10){
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }

        this.weekSalary = weekSalary;
    }

    public double getWorkHoursPerDay() {
        return workHoursPerDay;
    }

    private void setWorkHoursPerDay(double workHoursPerDay) {
        if (1 > workHoursPerDay && workHoursPerDay > 12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }

        this.workHoursPerDay = workHoursPerDay;
    }

    public double moneyPerHour(){
        return this.weekSalary / (7 * this.workHoursPerDay);
    }

    @Override
    protected void setLastName(String lastName){
        if(lastName.length() < 4){
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }

        super.setLastName(lastName);
    }
}
