/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Post.PostPK;

/**
 *
 * @author sinan.rassam
 */
public class ForumPK {
    // note that fields in public key class must be public

    public Integer id;

    public ForumPK() {
    }

    public ForumPK(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PostPK)) {
            return false;
        } else {
            PostPK other = (PostPK) obj;
            return id.equals(other.id);
        }
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        } else {
            return id.hashCode();
        }
    }

}
