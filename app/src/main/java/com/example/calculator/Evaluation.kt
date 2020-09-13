package com.example.calculator

/**
 * @param left 符號左側完整的待運算數字
 * 運算子物件，用來存儲歷史運算符以做最後的結果運算
 */
abstract class Operator(val left: Double) {
    abstract fun evaluate(operator: Operator): Operator
    /**
     * 同[evaluate]說明, 實作計算方法, 左值 +-/..右值, 並輸出新值的運算子物件 Operator
     */
    abstract fun returnResult(left: Double):Operator
    /**
     * 同[returnResult]說明, 實回傳當前運算符物件, 以於實作計算方法時回傳新值的運算符
     */
    abstract fun formula(): String
    /**
     * 同[formula]說明, 回傳當前運算符物件的string, 如 Addition(123), 則回傳: 123+
     * */
}

class Addition(left: Double) : Operator(left) { //1+ 2*
    override fun evaluate(operator: Operator): Operator {
        val tempResult = this.left + operator.left //1+2
        return operator.returnResult(tempResult) //3x
    }

    override fun returnResult(left: Double): Operator {
        return Addition(left) //1+
    }

    override fun formula(): String {
        return "$left+"
    }
}

class Subtraction(left: Double) : Operator(left){
    override fun evaluate(operator: Operator): Operator {
        val tempResult = this.left - operator.left
        return operator.returnResult(tempResult)
    }

    override fun returnResult(left: Double): Operator {
        return Subtraction(left)
    }

    override fun formula(): String {
        return "$left-"
    }
}

class Multiplication(left: Double) : Operator(left){
    override fun evaluate(operator: Operator): Operator {
        val tempResult = this.left * operator.left
        return operator.returnResult(tempResult)
    }

    override fun returnResult(left: Double): Operator {
        return Multiplication(left)
    }

    override fun formula(): String {
        return "$left*"
    }
}

class Division(left: Double) : Operator(left){
    override fun evaluate(operator: Operator): Operator {
        val tempResult = this.left / operator.left
        return operator.returnResult(tempResult)
    }

    override fun returnResult(left: Double): Operator {
        return Division(left)
    }

    override fun formula(): String {
        return "$left/"
    }
}

class Equal(left: Double) : Operator(left){
    override fun evaluate(operator: Operator): Operator {
        return operator.returnResult(operator.left)
    }

    override fun returnResult(left: Double): Operator {
        return Equal(left)
    }

    override fun formula(): String {
        return "$left="
    }
}
