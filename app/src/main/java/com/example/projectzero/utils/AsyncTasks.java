package com.example.projectzero.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AsyncTasks {
    private AsyncTasks() {
    }

    public interface IDaoCallWrapperParam<T> {
        T op() throws IOException;
    }

    public static <T> CompletableFuture<T> daoCallWrapper(IDaoCallWrapperParam<T> method) throws CompletionException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return method.op();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CompletionException(e);
            }
        });
    }
}
