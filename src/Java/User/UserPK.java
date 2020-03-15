/*
 * Class which represents a composite public key for the Pet entity
 * @see PetEntityServlet.java
 */
package User;

import java.io.Serializable;

public class UserPK implements Serializable {
    // note that fields in public key class must be public

    public String email;
    public String username;

    public UserPK() {
    }

    public UserPK(String email, String username) {
        this.email = email;
        this.username = username;
    }

    @Override
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

    @Override
    public int hashCode() {
        if (username == null || email == null) {
            return 0;
        } else {
            return username.hashCode() ^ email.hashCode();
        }
    }
}