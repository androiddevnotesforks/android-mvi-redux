/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase<in Params>(
    override val executorThread: Scheduler,
    override val postExecutionThread: Scheduler
) : BaseUseCase<Params>() {

    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    override fun execute(params: Params?): Completable =
        buildUseCaseCompletable(params = requireParams(params))
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}