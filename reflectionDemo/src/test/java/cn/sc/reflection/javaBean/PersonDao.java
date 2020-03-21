package cn.sc.reflection.javaBean;

public class PersonDao extends Dao<Person,String> {

    @Override
    public Person get(String s) {
        return super.get(s);
    }

    @Override
    public void save(Person person) {
        super.save(person);
    }
}
