package com.ronin.learn.mode.builder;

/**
 * Created by Administrator on 2017/3/17.
 */

public class PersonInfo {

    private final String name;
    private final int age;
    private final String country;

    private PersonInfo(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.country = builder.country;
    }

    public static class Builder {
        private String name;
        private int age;
        String country = "China";

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PersonInfo build() {
            PersonInfo personInfo = new PersonInfo(this);
            return personInfo;
        }

    }


}

