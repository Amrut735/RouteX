package com.routex.app.core.base;

import androidx.lifecycle.ViewModel;
import com.routex.app.core.utils.UiState;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0004JO\u0010\r\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015H\u0004\u00a2\u0006\u0002\u0010\u0018R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lcom/routex/app/core/base/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/routex/app/core/base/ViewModelEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "emitEvent", "", "event", "launchWithState", "T", "stateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/MutableStateFlow;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function1;)V", "app_debug"})
public abstract class BaseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.routex.app.core.base.ViewModelEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.routex.app.core.base.ViewModelEvent> events = null;
    
    public BaseViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.routex.app.core.base.ViewModelEvent> getEvents() {
        return null;
    }
    
    protected final void emitEvent(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.base.ViewModelEvent event) {
    }
    
    /**
     * Convenience to run a suspend block on [dispatcher] and push results into [stateFlow].
     */
    protected final <T extends java.lang.Object>void launchWithState(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<T>> stateFlow, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block) {
    }
}