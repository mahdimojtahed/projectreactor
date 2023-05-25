package com.dotin.projectreactor.service;

import com.dotin.projectreactor.domain.Movie;
import com.dotin.projectreactor.domain.Review;
import com.dotin.projectreactor.exception.MovieException;
import com.dotin.projectreactor.exception.NetworkException;
import com.dotin.projectreactor.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MovieReactiveService {

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;
    private final RevenueService revenueService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService, RevenueService revenueService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
        this.revenueService = revenueService;
    }

    public Flux<Movie> getAllMovies() {
        // Error Behavior - Throw a MovieException anytime one of these calls fail
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    throw new MovieException(ex.getMessage());
                })
                .log();
    }

    public Flux<Movie> getAllMovies_restClient() {
        // Error Behavior - Throw a MovieException anytime one of these calls fail
        var moviesInfoFlux = movieInfoService.retrieveAllMovieInfo_RestClient();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux_RestClient(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    throw new MovieException(ex.getMessage());
                })
                .log();
    }

    public Flux<Movie> getAllMovies_retry() {
        // Error Behavior - Throw a MovieException anytime one of these calls fail
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    throw new MovieException(ex.getMessage());
                })
                .retry(3)
                .log();
    }

    public Flux<Movie> getAllMovies_retryWhen() {
        // Error Behavior - Throw a MovieException anytime one of these calls fail

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    if (ex instanceof NetworkException)
                        throw new MovieException(ex.getMessage());
                    else
                        throw new ServiceException(ex.getMessage());
                })
                .retryWhen(getRetryBackoffSpec())
                .log();
    }


    public Flux<Movie> getAllMovies_repeat() {
        // Error Behavior - Throw a MovieException anytime one of these calls fail
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    if (ex instanceof NetworkException)
                        throw new MovieException(ex.getMessage());
                    else
                        throw new ServiceException(ex.getMessage());
                })
                .retryWhen(getRetryBackoffSpec())
                .repeat()
                .log();
    }

    public Flux<Movie> getAllMovies_repeat_n(long n) {
        // Error Behavior - Throw a MovieException anytime one of these calls fail

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    if (ex instanceof NetworkException)
                        throw new MovieException(ex.getMessage());
                    else
                        throw new ServiceException(ex.getMessage());
                })
                .retryWhen(getRetryBackoffSpec())
                .repeat(n)
                .log();
    }

    public Flux<Movie> getAllMovies_repeat_When(int n) {
        // Error Behavior - Throw a MovieException anytime one of these calls fail

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .onErrorMap((ex) -> {
                    log.error("Exception is : ", ex);
                    if (ex instanceof NetworkException)
                        throw new MovieException(ex.getMessage());
                    else
                        throw new ServiceException(ex.getMessage());
                })
                .retryWhen(getRetryBackoffSpec())
                .repeatWhen((flux) -> Flux.range(0, n)
                        .delayElements(Duration.ofMillis(1000)))
                .log();
    }

    private RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.fixedDelay(3, Duration.ofMillis(500))
                .filter(ex -> ex instanceof MovieException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())));
    }

    public Mono<Movie> getMovieById(long movieId) {

        var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        var reviewsFlux = reviewService.retrieveReviewsFlux(movieId)
                .collectList();

        return movieInfoMono.zipWith(reviewsFlux, (movieInfo, reviews) -> new Movie(movieInfo, reviews));
    }

    public Mono<Movie> getMovieById_RestClient(long movieId) {

        var movieInfoMono = movieInfoService.retrieveMovieInfoById_RestClient(movieId);
        var reviewsFlux = reviewService.retrieveReviewsFlux_RestClient(movieId)
                .collectList();

        return movieInfoMono.zipWith(reviewsFlux, (movieInfo, reviews) -> new Movie(movieInfo, reviews))
                .log();
    }

    public Mono<Movie> getMovieById_withRevenue(long movieId) {

        var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        var reviewsFlux = reviewService.retrieveReviewsFlux(movieId)
                .collectList();

        var revenueMono = Mono.fromCallable(()->revenueService.getRevenue(movieId))
        .subscribeOn(Schedulers.boundedElastic());

        return movieInfoMono.
                zipWith(reviewsFlux, (movieInfo, reviews) -> new Movie(movieInfo, reviews))
                .zipWith(revenueMono, (movie,revenue) ->{
                    movie.setRevenue(revenue);
                    return movie;
                });
    }

}
