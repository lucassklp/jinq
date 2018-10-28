package JIQ;

public class User{
    private int age;
    private String name;
    private String email;
    private float weight;


    public User(int age, String name, String email, float weight) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}