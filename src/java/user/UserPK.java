/*
 * Class which represents a composite public key for the Pet entity
 * @see PetEntityServlet.java
 */
package user;

import java.io.Serializable;
import java.util.Date;

public class UserPK implements Serializable {
    // note that fields in public key class must be public

    public String firstName;
    public String lastName;
    public String email;
    public Date dob;
    public int age;
    public String gender;
    public String username;

    public UserPK() {
    }

    public UserPK(String firstName, String lastName, String email, Date dob, int age, String gender, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.username = username;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UserPK)) {
            return false;
        } else {
            UserPK other = (UserPK) obj;
            if (username != null && username.equals(other.username)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int hashCode() {
        if (username == null) {
            return 0;
        } else {
            return username.hashCode();
        }
    }
}
