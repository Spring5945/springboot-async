package com.zhengyu.infrastructure;

public class User {
    String name;
    Long userId;
    int age;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static final class UserBuilder {
        String name;
        Long userId;
        int age;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.age = this.age;
            user.userId = this.userId;
            return user;
        }
    }
}
