/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author sinan.rassam
 */

@Entity
@Table(name = "users")
@IdClass(value = UserPK.class)
public class User implements Serializable {

}
