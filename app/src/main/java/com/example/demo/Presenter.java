package com.example.demo;

import android.support.annotation.NonNull;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public abstract class Presenter<T> {

  private T mView;
  private CompositeSubscription mViewSubscriptions;

  public final void takeView(@NonNull T view) {
    if (mView != null) {
      throw new IllegalStateException(
          "Presenter already has the view or the dropview isn't called");
    }

    mView = view;
    mViewSubscriptions = new CompositeSubscription();
    onTakeView();
  }

  protected void onTakeView() {
  }

  public final void dropView() {
    onDropView();
    mView = null;
    mViewSubscriptions.clear();
  }

  protected void onDropView() {
  }

  /**
   * Call this method from within your subscriptions, and this method
   * will always return an object. Outside and it could potentially
   * return null, which you should check. (We do not flag this as @Nullable,
   * since we would have to put ignores everywhere in our lint.)
   *
   * @return view that this presenter is attached to.
   */
  protected final T getView() {
    return mView;
  }

  public final void addViewSubscription(@NonNull Subscription subscription) {
    mViewSubscriptions.add(subscription);
  }

  public final void removeViewSubscription(@NonNull Subscription subscription) {
    mViewSubscriptions.remove(subscription);
  }
}