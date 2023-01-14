package com.apilighthouse.apilighthouse.util;

import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
public class Result<T> {

    private String RequestId;

    private List<T> data;

    public Result() {
        Random random = new Random();
        this.RequestId = String.valueOf(random.nextInt());
    }

    public Result(List<T> data) {
        Random random = new Random();
        this.RequestId = String.valueOf(random.nextInt());
        this.data = data;
    }

    public static <T> Result<T> succeed() {
        return new Result();
    }

    public static <T> Result<T> succeed(List<T> list) {
        return new Result(list);
    }

    public static <T> Result<T> succeed(boolean in) {
        return new Result();
    }

    public static <T> Result<T> error() {
        return new Result();
    }


}
