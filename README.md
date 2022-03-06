99 Bottles of beer
-----

An Kotlin implementation of the 99 Bottles of beer song.

The purpose was to get some experience with coroutines while keeping them synchronized.

The working principle of the application is that 100 coroutines are launched at once, one for each line. The numbers 
are shuffled first to make sure the coroutines need to synchronize. Each coroutine is responsible for a specific 
numbered line, but it has to wait its turn before it is allowed to run.