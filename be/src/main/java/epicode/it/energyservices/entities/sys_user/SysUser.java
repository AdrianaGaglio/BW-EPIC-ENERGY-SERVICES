package epicode.it.energyservices.entities.sys_user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="sys_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

}