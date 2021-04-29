package gorest;

public class User {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
    private String message;

    public User(String name, String email, Gender gender, Status status) {
        this.name = name;
        this.email = email;
        this.gender = gender.getGender();
        this.status = status.getStatus();
    }

    public static User createUser(){
        return new User(
                "Hi Hello8" + System.currentTimeMillis(),
                "Hi Hello8" + System.currentTimeMillis() + "@test.com",
                Gender.MALE,
                Status.ACTIVE
        );
    }

    public String getName() {

        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }
}
