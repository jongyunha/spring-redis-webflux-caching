package com.jongyun.redisspring.fib.service

import java.util.InputMismatchException
import org.springframework.stereotype.Service

@Service
class FibService {

    fun getFib(index: Int): Int {
        println("calcurating fib for $index")
        return this.fib(index)
    }

    fun fib(number: Int): Int {
        if (number < 2) return number
        return fib(number - 1) + fib(number - 2)
    }
}