package com.example.jetpackwithjunit.utils
import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import io.reactivex.*
import io.reactivex.subjects.Subject

fun <T> Observable<T>.toLiveData(
    backpressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST
): LiveData<T> = toFlowable(backpressureStrategy).toLiveData()

fun <T> Single<T>.toLiveData(): LiveData<T> = toFlowable().toLiveData()
fun <T> Maybe<T>.toLiveData(): LiveData<T> = toFlowable().toLiveData()
fun Completable.toLiveData(): LiveData<Unit> = toFlowable<Unit>().toLiveData()

fun <T> Observable<T>.withLoadingSubject(subject: Subject<Boolean>): Observable<T> =
    doOnSubscribe { subject.onNext(true) }.doOnComplete { subject.onNext(false) }

fun <T> Observable<T>.withEnabledSubject(subject: Subject<Boolean>): Observable<T> =
    doOnSubscribe { subject.onNext(true) }.doOnNext { subject.onNext(false) }

fun Observable<Boolean>.filterTrue(): Observable<Boolean> = filter { it }
fun Single<Boolean>.filterTrue(): Maybe<Boolean> = filter { it }
fun Flowable<Boolean>.filterTrue(): Flowable<Boolean> = filter { it }
fun Observable<Boolean>.filterFalse(): Observable<Boolean> = filter { !it }
fun Single<Boolean>.filterFalse(): Maybe<Boolean> = filter { !it }
fun Flowable<Boolean>.filterFalse(): Flowable<Boolean> = filter { !it }

fun Observable<*>.mapUnit(): Observable<Unit> = map { Unit }
fun Single<*>.mapUnit(): Single<Unit> = map { Unit }
fun Flowable<*>.mapUnit(): Flowable<Unit> = map { Unit }
fun Subject<*>.mapUnit(): Observable<Unit> = map { Unit }
fun Subject<Unit>.trigger() = onNext(Unit)
