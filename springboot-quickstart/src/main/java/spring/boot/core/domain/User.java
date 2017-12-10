package spring.boot.core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 */
@Table(name="boot_user")
@Entity
public class User implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 名称
     */
    @Column(nullable = false, name="username")
    private String name;

    /**
     * 年龄
     */
    @Column(nullable = false, name="userage")
    private Integer age;

    /**
     * 出生时间
     */
    @Column(name="userbirthday")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
