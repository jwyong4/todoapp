package com.soloproject.todoapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Dto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private int order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private int id;
        private String title;
        private int order;
        private boolean completed;

        public void setId(int id) {
            this.id = id;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class response {
        private int id;
        private String title;
        private int order;
        private boolean completed;
    }
}
