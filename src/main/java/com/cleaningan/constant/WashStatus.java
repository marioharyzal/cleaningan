package com.cleaningan.constant;

public enum WashStatus {
    DONE {
        @Override
        public String toString() {
            return "Selesai";
        }
    },

    NOT_DONE {
        @Override
        public String toString() {
            return "Belum Selesai";
        }
    }
}
