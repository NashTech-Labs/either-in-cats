package com.example

import cats.syntax.either._

object Examples extends App
{
    val value = 0
    //either example
    val either : Either[String, Int] = if(value != 0)
    {
        Right(value)
    }
    else
    {
        Left("It's zero")
    }

    //either using smart constructors
    val rightValueWithSmartConstructor : Either[String, String] = "I am right value".asRight[String]
    val leftValueWithSmartConstructor : Either[String, String] = "I am left value".asLeft[String]

    for
        {
        rightBiased <- either
    } yield rightBiased

    //either using Right.apply and Left.apply
    val rightValue : Right[Nothing, String] = Right("I am right")
    val leftValue : Left[String, Nothing] = Left("I am left")

    //catchOnly example
    val catchOnly = Either.catchOnly[ArithmeticException]
        {
            45 / 0
        }
    val noIssueInCatchOnly = Either.catchOnly[ArithmeticException]
        {
            45 / 8
        }

    //getOrElse example
    val getOrElseWithLeft = "Return default".asLeft.getOrElse("Default Value")
    val getOrElseWithRight = "Extract Right Value".asRight.getOrElse("Default Value")

    //ensure example
    val ensureLeft = 0.asRight.ensure("It's zero")(number => number != 0)
    val ensureRight = 10.asRight.ensure("It's zero")(number => number != 0)

    //recover example
    val recoverOnLeft = "error".asLeft.recoverWith
    {
        case _ : String => Right(-1)
    }
    val recoverOnRight = 100.asRight[String].recover
    {
        case _ : String => Right(-1)
    }

    //leftMap example
    val leftMap = "Let's map left".asLeft.leftMap(_.toUpperCase)

    //biMap example
    val biMapOnRight = "Let's Map everything".asRight[String].bimap(_.toUpperCase, _.toLowerCase)
    val biMapOnLeft = "Let's Map everything".asLeft[String].bimap(_.toUpperCase, _.toLowerCase)

    //toOption example
    val convertToOptionFromRight = "Convert to Option".asRight[String].toOption
    val convertToOptionFromLeft = "Convert to Option".asLeft[String].toOption

    //fromOption example
    val convertFromOptionToRight = Either.fromOption(Some("I am Right"), "Left it is")
    val convertFromOptionToLeft = Either.fromOption(None, "Left it is")
}
