package bank.entities.client;

public class Student extends BaseClient{

    public Student(String name, String ID, double income) {
        super(name, ID, 2, income);
    }

    @Override
    public void increase() {
        int newInterest = getInterest() + 1;
        setInterest(newInterest);
    }
}
