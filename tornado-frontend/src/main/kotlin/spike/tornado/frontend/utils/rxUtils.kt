package spike.tornado.frontend.utils

import io.reactivex.Observable

fun <S, T> Observable<List<S>>.innerMap(mapper: (S) -> T): Observable<List<T>> = this.map {
    it.map(mapper)
}
