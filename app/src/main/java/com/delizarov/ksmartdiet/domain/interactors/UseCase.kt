package com.delizarov.ksmartdiet.domain.interactors

import io.reactivex.Observable

abstract class UseCase<TResult, TParams> {

    protected constructor()

    /**
     * Создание нового экземпляра класса {@link Observable <TResult>} для указанных параметров
     *
     * @param params Параметры для создания {@link Observable<TResult>}
     * @return Новый объект {@link Observable<TResult>}
     */
    abstract fun createObservable(params: TParams?): Observable<TResult>

    /**
     * {@link Observable<TResult>}, который исполняет логику данного Use Case для указанных параметров.
     *
     * @param params Входные параметры для создания {@link Observable<TResult>}
     * @return Объект {@link Observable<TResult>}, который исполняет логику данного Use Case для указанных параметров.
     */
    fun observable(params: TParams?): Observable<TResult> =
            createObservable(params)
                    .doOnError {

                    }

    /**
     * {@link Observable<TResult>} с параметрами null
     */
    fun observable() = observable(null)

}