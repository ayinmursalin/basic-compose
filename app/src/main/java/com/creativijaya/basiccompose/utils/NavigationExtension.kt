package com.creativijaya.jetpackcomposeskeleton.utils

fun String.setRouteArguments(vararg args: String): String {
    val builder = StringBuilder(this)

    args.forEachIndexed { index, arg ->
        val prefix = if (index == 0) "?" else "&"

        builder.append(prefix)
            .append(arg)
            .append("=")
            .append("{$arg}")
    }

    return builder.toString()
}

fun String.setRouteArgumentValues(vararg argValues: Pair<String, Any>): String {
    var newString = this

    argValues.forEach { value ->
        if (this.contains(value.first)) {
            newString = newString.replace("{${value.first}}", value.second.toString())
        }
    }

    return newString
}
