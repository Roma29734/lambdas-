package lambdas

typealias DoubleConversion = (Double) -> Double

fun convert(
    x: Double,
    converted: DoubleConversion
): Double {
    val result = converted(x)
    println("$x is converted to $result")
    return result
}

fun getConversion(str: String): DoubleConversion {
    when (str) {
        "CentigradeToFahrenheit" -> {
            return { it * 1.8 + 32 }
        }
        "KgsToPounds" -> {
            return { it * 2.204623 }
        }
        "PoundsToUSTons" -> {
            return { it / 2000.0 }
        }
        else -> {
            return { it }
        }
    }
}

fun combine(
    lambda1: DoubleConversion,
    lambda2: DoubleConversion
): DoubleConversion {
    return { x: Double -> lambda2(lambda1(x)) }
}


fun main() {
    println("Convert 2.5kg to Pounds: ${getConversion("KgsToPounds")(2.5)}")

    val kgsToPoundsLambda = getConversion("KgsToPounds")
    val poundsToUSTonsLambda = getConversion("PoundsToUSTons")

    val kgsToUSTonsLambda = combine(kgsToPoundsLambda, poundsToUSTonsLambda)

    val value = 17.4
    println("$value kgs is ${convert(value, kgsToUSTonsLambda)} US tons")
}