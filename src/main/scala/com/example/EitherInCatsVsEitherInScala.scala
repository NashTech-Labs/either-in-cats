package com.example

import cats.syntax.either._

object EitherInCatsVsEitherInScala
{
    def countPositive(nums : List[Int]) =
        nums.foldLeft(Right(0))
        {(accumulator, num) =>
            if(num > 0)
            {
                accumulator.map(_ + 1)
            }
            else
            {
                Left("Negative. Stopping!")
            }
        }

    def countPositive(nums : List[Int]) =
        nums.foldLeft(0.asRight[String])
        {(accumulator, num) =>
            if(num > 0)
            {
                accumulator.map(_ + 1)
            }
            else
            {
                "Negative. Stopping!".asLeft[Int]
            }
        }
}
