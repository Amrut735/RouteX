package com.routex.app.core.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086\b\u00f8\u0001\u0000\u001a>\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000b0\bH\u0086\b\u00f8\u0001\u0000\u001a6\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\r"}, d2 = {"map", "Lcom/routex/app/core/utils/Resource;", "R", "T", "transform", "Lkotlin/Function1;", "onError", "action", "Lkotlin/Function2;", "", "", "", "onSuccess", "app_debug"})
public final class ResourceKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, R extends java.lang.Object>com.routex.app.core.utils.Resource<R> map(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.utils.Resource<? extends T> $this$map, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends R> transform) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>com.routex.app.core.utils.Resource<T> onSuccess(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.utils.Resource<? extends T> $this$onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> action) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>com.routex.app.core.utils.Resource<T> onError(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.utils.Resource<? extends T> $this$onError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Throwable, kotlin.Unit> action) {
        return null;
    }
}